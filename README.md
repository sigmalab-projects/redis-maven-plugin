[ ![Download](https://api.bintray.com/packages/sigmalab/maven-releases/redis-maven-plugin/images/download.svg) ](https://bintray.com/sigmalab/maven-releases/redis-maven-plugin/_latestVersion)


###How to use

Add the following section into the plugins-section of your pom.xml. The goals 'start' and 'stop' are bound to 'pre-integration-test' and 'post-integration-test'.

````
<plugins>
    ...
    <plugin>
        <groupId>de.sigmalab.maven.plugins</groupId>
        <artifactId>redis-maven-plugin</artifactId>
        <version>0.9.3</version>
        <executions>
            <execution>
                <goals>
                    <goal>start</goal>
                    <goal>stop</goal>
                </goals>
            </execution>
        </executions>
    </plugin>
    ...
</plugins>
````

Then you should see something like this on command line:

````
[INFO]
[INFO] --- redis-maven-plugin:0.9.3:start (default) @ project ---
[INFO] Redis-Server started on port : 6379
[INFO]
[INFO] --- maven-failsafe-plugin:2.19.1:integration-test (default) @ project ---

-------------------------------------------------------
 T E S T S
-------------------------------------------------------
...


...


Results :

Tests run: 1, Failures: 0, Errors: 0, Skipped: 0

[INFO]
[INFO] --- redis-maven-plugin:0.9.3:stop (default) @ project ---
[INFO] Shutting down Redis server ...
[INFO] Redis server stopped.

...

````


