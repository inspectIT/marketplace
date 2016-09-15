# marketplace
The marketplace website of inspectIT

# Licence
Apache License Version 2.0

# Gradle Wrapper
Run Project without Gradle with:
- ./gradlew build (on Unix-like platforms such as Linux and Mac OS X)
- gradlew.bat build (on Windows using the gradlew.bat batch file)

For more information read (Gradle docs: Gradle Wrapper) [https://docs.gradle.org/3.0/userguide/gradle_wrapper.html]

# Build
Execute command ``` gradlew.bat build ``` in Windows command prompt (Strg + R -> cmd) will fail with ``` Unable to process incoming event 'ProgressComplete ' (ProgressCompleteEvent) ``` Exception. This may be caused by Issue (GRADLE-3527) [https://issues.gradle.org/browse/GRADLE-3527]. For further Information check following discussion (Discuss Gradle) [https://discuss.gradle.org/t/build-fails-with-unable-to-process-incoming-event-progresscomplete-progresscompleteevent/18434/17] 
To avoid this error use your IDE to build. 
In IntelliJ IDEA 2016.2.4 build completes successfully. Tested on different Laptops with Windows 10, Ubuntu 16.04, Ubuntu 14.10 and Linux Mint 18 (KDE). 

# Frameworks and Plugins
Currently used:
- Gradle 3.0
- Spring Boot 1.4.0
- Angular 2 - RC 7
- CheckStyle
- CPD - 1.0
- PMD
- FindBugs
- Node Plugin - 0.13

# Extend
Front-End - Angular: [baseDir]/src/main/resources/frontend/angular2/app
Back-End - Spring/Java: [baseDir]/src/main/java