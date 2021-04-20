import json

# def myMethod(s):
#     print(s)
#     #myStr =
#     loginPswd = [(k, v) for k, v in json.loads(s).items()]
#     print(loginPswd[0][0])
#     print(loginPswd[0][1])
#
# myMethod('{"vadym.vasiuk@deloitte2.com": "oiGRI6vLlFE52hj2vwAa"}')

input = {'a': """{"s3_bucket": "wdap-bucket","date": "20210312"}"""}
v = input['a']
print(v)
# s1 = '{\"s3_bucket\": \"wdap-bucket\",\"date\": \"20210312\"}'
d = json.loads(v)
date = d['date']
# print(date)


payload = """{
    "id":"01",
    "language": "JSON",
    "edition": "first",
    "author": "Derrick Mwiti"
}
"""
payloadStr = json.dumps(payload)
payloadBytesArr = bytes(payloadStr, encoding='utf8')
print(payloadStr)
print(payloadBytesArr)