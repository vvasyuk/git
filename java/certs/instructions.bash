#!/bin/sh
#
# Licensed to the Apache Software Foundation (ASF) under one or more
# contributor license agreements.  See the NOTICE file distributed with
# this work for additional information regarding copyright ownership.
# The ASF licenses this file to You under the Apache License, Version 2.0
# (the "License"); you may not use this file except in compliance with
# the License.  You may obtain a copy of the License at
#
# http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#
# Initialize openssl & CA.
#
# Instruction: https://access.redhat.com/documentation/en-US/Red_Hat_JBoss_Fuse/6.0/html/Web_Services_Security_Guide/files/i305191.html
#

# Path to CA certificate.
ca_cert=/usr/ssl/ca/ca.pem

#
# Create artifacts for specified name: key pair-> cert request -> ca-signed certificate.
# Save private key and CA-signed certificate into key storages: PEM, JKS, PFX (PKCS12).
#
# param $1 Artifact name.
# param $2 Password for all keys and storages.
#
function createStore {
	artifact=$1
	pwd=$2

	echo
	echo Clean up all old artifacts: test.*
	rm -f test.*

	echo
	echo Generate a certificate and private key pair for test.
	#D:\work\installed\java\bin
	#D:\work\installed\java\bin\keytool -list -v -keystore test.jks
	keytool -genkey -keyalg RSA -keysize 1024 -dname "emailAddress=test@ignite.com, CN=test, OU=Dev, O=Ignite, L=SPb, ST=SPb, C=RU" -validity 7305 -alias test -keypass password -keystore test.jks -storepass password

	echo
	echo Create a certificate signing request for test.
	D:\work\installed\java\bin\keytool -certreq -alias test -file test.csr -keypass password -keystore test.jks -storepass password

	echo
	echo "Sign the CSR using CA (default SSL configuration)."
	# install openssl, run CA.sh to gen demoCA folder
	openssl ca -days 7305 -in test.csr -out test.pem

	echo
	echo Convert to PEM format.
	#in config string_mask = pkix
	openssl x509 -in test.pem -out test.pem -outform PEM

	echo
	echo Concatenate the CA certificate file and test.pem certificate file into certificates chain.
	cat test.pem ${ca_cert} > test.chain

	echo
	echo Update the keystore, test.jks, by importing the CA certificate.
	keytool -import -alias ca          -file ${ca_cert} -keypass password -noprompt -trustcacerts -keystore test.jks -storepass password

	echo
	echo Update the keystore, test.jks, by importing the full certificate chain for the test.
	keytool -import -alias test -file test.chain -keypass password -noprompt -trustcacerts -keystore test.jks -storepass password

	echo
	echo Generate PKCS12 storage for the private key and certificate chain.
	keytool -importkeystore \
		-srcstoretype JKS -deststoretype PKCS12 \
		-srckeystore test.jks -destkeystore test.pfx \
		-srcstorepass password -deststorepass password \
		-srcalias test -destalias test \
		-srckeypass password -destkeypass password \
		-noprompt

	echo
	echo Generate PEM storage for the private key and certificate chain.
	openssl pkcs12 \
		-in test.pfx -out test.pem \
		-passin pass:password -passout pass:password

	rm -f test.chain test.csr
}

pwd="123456"

createStore "client" password
createStore "server" password

echo
echo Update trust store with certificates: CA, client, server.
keytool -import -alias ca -file ${ca_cert} -keypass password -noprompt -trustcacerts -keystore trust.jks -storepass password
#keytool -importkeystore -srckeystore client.jks -destkeystore trust.jks -srcstorepass password -deststorepass password -alias client -noprompt
#keytool -importkeystore -srckeystore server.jks -destkeystore trust.jks -srcstorepass password -deststorepass password -alias server -noprompt
keytool -export -alias client -keystore client.jks -storepass password | keytool -importcert -alias client -noprompt -keystore trust.jks -storepass password
keytool -export -alias server -keystore server.jks -storepass password | keytool -importcert -alias server -noprompt -keystore trust.jks -storepass password