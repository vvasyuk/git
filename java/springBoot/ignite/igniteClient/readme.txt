### Start locally ###
Main class:	com.ignite.IgniteApplication
# -verbose:class to show which classes loaded
VM options: -XX:+UseG1GC -XX:+DisableExplicitGC -Xmx256m -verbose:class
Program arguments:	--spring.profiles.include="standalone"
					or
					--spring.profiles.include="regular"
ENV:		GARFILE=file://d:\work\tryout\java\igniteService\build\libs\