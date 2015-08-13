Building
========

Run the following

`gradle build`

Running Tests
=============

Run this
`gradle clean build test`

Running Application
===================

Run
`gradle run`

Alternatively, run 
`gradle clean fatJar`
and run scalc-all.jar from build/libs with 
`java -jar scalc-all.jar`

Known Issues
============
 * Running from gradle floods the readLine calls with null strings. No idea why.
 
If you find bugs, let me know. This is a small hobby project to learn more about Scala pattern matching, so don't expect anything amazing

