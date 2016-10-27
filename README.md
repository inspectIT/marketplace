[![license](https://img.shields.io/badge/License-Apache%20v2-brightgreen.svg)](https://github.com/inspectIT/marketplace/blob/master/LICENSE)

[![Run Status](https://api.shippable.com/projects/57bfdf1d2345db10004fe95a/badge?branch=master)](https://app.shippable.com/projects/57bfdf1d2345db10004fe95a)  [![Coverage Badge](https://api.shippable.com/projects/57bfdf1d2345db10004fe95a/coverageBadge?branch=master)](https://app.shippable.com/projects/57bfdf1d2345db10004fe95a) 

[![Documentation Badge](http://inch-ci.org/github/inspectIT/marketplace.svg?branch=master)](http://inch-ci.org/github/inspectIT/marketplace/history?branch=master) [![GitHub version](https://img.shields.io/github/release/inspectIT/marketplace.svg)](https://github.com/inspectIT/marketplace) ![SE number of downloads](https://img.shields.io/github/downloads/inspectIT/marketplace/latest/total.svg)

# marketplace
The marketplace website of inspectIT

# Licence
Apache License Version 2.0

# Main Build Information
## Gradle Wrapper
To execute a task without a local Gradle installation, you can use the Gradle wrapper. To execute a task use following command in your Command Window (Windows) or Termin (Unix and Mac)
- ``` ./gradlew [task] ``` (on Unix-like platforms such as Linux and Mac OS X)
- ``` gradlew.bat [task] ``` (on Windows using the gradlew.bat batch file)
To list all possible gradle tasks replace \[task\] with ``` tasks ``` 

For more information read [Gradle docs: Gradle Wrapper](https://docs.gradle.org/3.0/userguide/gradle_wrapper.html)

## Build
Execute command ``` gradlew.bat build ``` in Windows command prompt (Strg + R -> cmd) will fail with ``` Unable to process incoming event 'ProgressComplete ' (ProgressCompleteEvent) ``` Exception. This may be caused by Issue [GRADLE-3527](https://issues.gradle.org/browse/GRADLE-3527). For further Information check following discussion [Discuss Gradle](https://discuss.gradle.org/t/build-fails-with-unable-to-process-incoming-event-progresscomplete-progresscompleteevent/18434/17) 
To avoid this error use your IDE to build.
In IntelliJ IDEA 2016.2.4 build completes successfully. Tested on different Laptops with Windows 10, Ubuntu 16.04, Ubuntu 14.10 and Linux Mint 18 (KDE). 

Since Resource Filtering demands building the application first and running application with ``` gradlew.bat bootRun [-Pprod]``` (you don't have to specify a profile, since dev is default) we're using application-dev.properties with hard coded values, thus we can start the application by simple running the main Method within your IDE. In addition it's easier to run the test-cases within your IDE.

## Additional Properties
We're processing all project properties. To add properties and using it within the application, you could
1. override an existing project property like version, group, etc. 
2. extend project properties with following code ``` project.ext.[your_property] = [value] ```

To use the properties you have add it to the application-[profile].properties: ``` application.property.new.placeholder=${project.ext.[your_property]} ```
If you're already using Spring Placeholder Properties, like java.io.tmpdir, you have to escape them with a backslash like ``` application.property.tmpdir=\${java.io.tmpdir} ``` or the will collide with the gradle Properties, which would cause an error.

## Docker
To create a docker container you have add ``` buildDocker ``` task to the build command. We avoid using ``` depends on [task] ```, since this may result in complicated builds.
Full command: ``` gradlew.bat clean build buildDocker -Pprod ```

Don't forget to add profile when executing a task separately.

# Frameworks and Plugins - fixme
Currently used:
- Gradle 3.0
- Spring Boot 1.4.1
- Angular 2
- CheckStyle
- CPD - 1.0
- PMD
- FindBugs
- Node Plugin - 0.13

# Extend - fixme
Front-End - Angular: [baseDir]/src/main/resources/frontend/angular2/app
Back-End - Spring/Java: [baseDir]/src/main/java


# Docker - fixme
start local container 
````
docker run -i -p 8080:8080 inspectit/marketplace:null.null
````
access application [Marketplace - Who Am I](http://192.168.99.100:8080/marketplace/whoami) You have to change URL IP to your local docker-ip or localhost on linux.

stop and remove ALL local Container:
````
docker stop $(docker ps -a -q)
docker rm $(docker ps -a -q)
````
