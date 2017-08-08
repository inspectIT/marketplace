[![license](https://img.shields.io/badge/License-Apache%20v2-brightgreen.svg)](https://github.com/inspectIT/marketplace/blob/master/LICENSE)

[![Run Status](https://api.shippable.com/projects/57bfdf1d2345db10004fe95a/badge?branch=master)](https://app.shippable.com/projects/57bfdf1d2345db10004fe95a)  [![Coverage Badge](https://api.shippable.com/projects/57bfdf1d2345db10004fe95a/coverageBadge?branch=master)](https://app.shippable.com/projects/57bfdf1d2345db10004fe95a) 

[![Documentation Badge](http://inch-ci.org/github/inspectIT/marketplace.svg?branch=master)](http://inch-ci.org/github/inspectIT/marketplace/history?branch=master) [![Marketplace version](https://img.shields.io/github/release/inspectIT/marketplace.svg)](https://github.com/inspectIT/marketplace) [![SE number of downloads](https://img.shields.io/github/downloads/inspectIT/marketplace/total.svg)](https://github.com/inspectIT/marketplace)

# marketplace
This ist the repository for the Open-Source [Marketplace](http://marketplace.inspectit.rocks) Application  for the Open Source Application-Performance-Management Tool: [inspecIT](http://www.inspectit.rocks)

The current Version is just a prototype, so don't it to be bug free and work flawless.

## Getting Started
Clone/Fork your Copy of the project for enhancement or independent development of the Marketplace.

The technologies used to build the marketplace are
* Spring-Boot Version 1.4.3 with the starters Actuator, Data-Jpa, Web, Security and Test to simple setup the Backend, 
* Angular 2 with Angular-Cli Version RC 1 for the Frontend,
* Gradle Version 3.1 as the Build-Tool and
* [Shippable](https://www.shippable.com), a Software as a Service, for the Continuous Integration/Delivery
In addition the following tools and frameworks were used:
* [FindBugs](http://findbugs.sourceforge.net), [PMD](https://pmd.github.io) and [CPD](http://pmd.sourceforge.net/pmd-4.3.0/cpd.html) as a static analysis tool to look for bugs, technical debts, empty blocks, and so on
* [CheckStyle](http://checkstyle.sourceforge.net) to adhere a coding standard and
* [H2-Database](http://www.h2database.com/html/main.html) as an In-Memory solution for the prototype

### Prerequisites
The build and run the application you have to install NodeJS and NPM. You can find NodeJS at [NodeJS.org](https://nodejs.org/en/). NodeJS is a JavaScript runtime, which we need to install the Angular-CLI.
After you have successfully installed NodeJS, you have to install Angular-CLI. 
We are going to use Angular-CLI to build and the run the Frontend. Please read the [Quickstart at Angular.io](https://angular.io/guide/quickstart) for information about Angular.

After you have installed NodeJS and Angular-CLI you can build and run the application. Use <em>build_sources.bat</em> (or <em>build_sources.sh</em> if you are on Linux) to simply build and start the main application.
The <em>build_source</em> file builds the Angular Frontend, copies the generated HTML & JS files to src/main/resources/static/ folder and starts the Spring-Boot Backend application with the h2-Profile using the embedded Database.

To just start the Frontend you can use Angulr-CLI in the folder <main>/frontend/marketplace using the command 
````
ng server
````

Accordingly, to start the Backend you can use Gradle-Wrapper in the main-folder using the command
````
gradlew.bat [clean] [build] bootRun [-Dspring.profiles.active=dev,h2]
````

Read more on [Main Build Information](#main-build-information)

# Main Build Information
## Gradle Wrapper
To execute a task without a local Gradle installation, you can use the Gradle wrapper. To execute a task use following command in your Command Window (Windows) or Terminal (Unix and Mac)
- ``` ./gradlew [task] ``` (on Unix-like platforms such as Linux and Mac OS X)
- ``` gradlew.bat [task] ``` (on Windows using the gradlew.bat batch file)
To list all possible gradle tasks replace \[task\] with ``` tasks ``` 

For more information read [Gradle docs: Gradle Wrapper](https://docs.gradle.org/3.0/userguide/gradle_wrapper.html)

## Build
Execute command ``` gradlew.bat build ``` in Windows command prompt (Strg + R -> cmd) will fail with ``` Unable to process incoming event 'ProgressComplete ' (ProgressCompleteEvent) ``` Exception. This may be caused by Issue [GRADLE-3527](https://issues.gradle.org/browse/GRADLE-3527). For further Information check following discussion [Discuss Gradle](https://discuss.gradle.org/t/build-fails-with-unable-to-process-incoming-event-progresscomplete-progresscompleteevent/18434/17) 
Due to this Bug, they build don't work as excepted and will exit with an error. The current "hack" is to build the angular 2 frontend application first and then copy the created JS, CSS and HTML files to the backend.
 ````
 1. build angular 2 app:     ng build -prod 
 2. build and copy backend:  gradlew build -Dspring.profiles.active=prod,h2,copy
 3. commit
 ````

Since Resource Filtering demands building the application first and running application with ``` gradlew.bat bootRun [-Dspring.profiles.active=dev,h2]``` (you don't have to specify a profile, since dev and h2 profile will be set by default) we're using application-dev.properties with hard coded values, thus we can start the application by simple running the main Method within your IDE. In addition it's easier to run the test-cases within your IDE. The embedded database is configured in application-h2.properties. Keep in mind that the H2 Database is a simple embedded database, which will be replaced in future releases.

## Additional Properties
We're processing all project properties. To add properties and using it within the application, you could
1. override an existing project property like version, group, etc. 
2. extend project properties with following code ``` project.ext.[your_property] = [value] ```

To use the properties you have add it to the application-[profile].properties: ``` application.property.new.placeholder=${project.ext.[your_property]} ```
If you're already using Spring Placeholder Properties, like java.io.tmpdir, you have to escape them with a backslash like ``` application.property.tmpdir=\${java.io.tmpdir} ``` or the will collide with the gradle Properties, which would cause an error.

## Docker
To create a docker container you have add ``` buildDocker ``` task to the build command. We avoid using ``` depends on [task] ```, since this may result in complicated builds.
Full command: ``` gradlew.bat clean build buildDocker -Dspring.profiles.active=prod,h2 ```

Don't forget to add profile when executing a task separately.

## Docker
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

Remove ALL local Images:
````
docker rmi $(docker images -q)
````

##TODO
* Services
* add swagger to spring
* document REST
* implement rating entity;
* create relationship between rating and product
* create tag and keyword entity
* create a relationship between tag/keywords and product
* write and test CRUD Functions for entities


## Ports
Changing base port to avoid collision with other applications.
Ports used by this application:
* default Tomcat Port: 80
* h2 Database Web Port: 9071
* h2 Database Tcp Port: 9043

## Profiles
Don't forget to that the dockerfile also contains spring.profiles

### h2
During development, you should use h2 Profile to create and populate an embedded Database. This database can be accessed by <a href="localhost:8080/console">H2 Console</a>. Since we didn't change the default setting, you don't have to modify anything and can proceed. <a href="http://www.h2database.com">Further information about H2 Database</a>.

### h2_db
Use a different datasource for unit tests; therefore we use a different profile

## Logging
Find the base LogBack logging configuration at https://github.com/spring-projects/spring-boot/blob/master/spring-boot/src/main/resources/org/springframework/boot/logging/logback/base.xml

## Swagger
You can check API Documentation on localhost under [Swagger UI](http://localhost:8080/swagger-ui.html)

# Authors
* [Patrice Bouillet](https://github.com/pbouillet)
* [Nikita Kolytschew](https://github.com/nkolytschew)

# Licence
This project is licensed under the Apache License Version 2.0 - see the [LICENSE](LICENSE) file for details.