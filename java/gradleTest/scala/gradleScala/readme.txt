java -cp .\gradleScala-1.0-SNAPSHOT.jar Main
Exception in thread "main" java.lang.NoClassDefFoundError: scala/Predef$

java -cp scala-library.jar -jar gradleScala-1.0-SNAPSHOT.jar

java -jar .\gradleScala-1.0-SNAPSHOT.jar
Exception in thread "main" java.lang.NoClassDefFoundError: scala/Predef$

java -cp d:\work\installed\scala-2.12.8\lib\;. -jar gradleScala-1.0-SNAPSHOT.jar
Exception in thread "main" java.lang.NoClassDefFoundError: scala/Predef$

//needed to add:
jar {
    manifest {
        attributes(
                'Class-Path': configurations.testRuntime.collect { project.uri(it) }.join(' '),
                'Main-Class': 'Main'
        )
    }
}

java -jar .\gradleScala-1.0-SNAPSHOT.jar