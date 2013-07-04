# Spring Batch Demo Application

This is a sample demo applicaiton demonstrating parallel job exection using 
Spring Batch framework remote partitioning feature. There is also a presentation showing important Spring Batch 2.2.0 features and road map for future versions.

## The use case
  The application performs following part in parallel
  * downloads day trading history from Google service
  * reads the downloaded csv files into the MySQL
  * builds a simple Jasper report basing on the imported data

## Building from Source

There are two requirements before building the projects. Because of the BATCH-2034 issue which prevented remote flow step from being started. The fix for the issue has been accepted after Spring Batch 2.2.0.RELEASE was out. Therefore, the demo references the Spring Batch 3.0.0.BUILD-SNAPSHOT version. Another snapshot dependency is the 1.3.0.BUILD-SNAPSHOT version of the spring-batch-admin project, which is needed for the web admin application part to be compatible with the Spring Batch 2.2.0.

Thus, before building the project, first build  [Spring Batch master](https://github.com/SpringSource/spring-batch.git) and [Spring Batch Admin](https://github.com/SpringSource/spring-batch-admin) projects from sources and install artifacts to the local maven repository:

    $ git clone git://github.com/SpringSource/spring-batch.git
    $ cd spring-batch
    $ mvn clean install

    $ git clone git://github.com/SpringSource/spring-batch-admin.git
    $ cd spring-batch-admin
    $ mvn clean install

Now we are ready to build the demo project. Clone the git repository using the URL on the Github home page:

    $ git clone git://github.com/sshcherbakov/spring-batch-talk.git
    $ ./gradlew clean war

Assuming you have RabbitMQ and MySQL services locally you can run application from the command line

    $ ./gradlew tomcatRunWar

## Starting a job

The application is Java web application packaged as war file. It leverages the Spring Batch Admin project to present the UI for running Spring Batch jobs and following their status.

After the application has been started you can navigate to its home page usually like:

  > http://localhost:8080/spring-batch-talk

Afterwards go to the Jobs tab and enter following into the Launch edit box:

  > symbolsFile=NYSE.txt,outputFile=sample.pdf

The first parameter 'symbolsFile' is the name of the file containing the list of company symbols known. It represents the initial taks description for the demo job. The path is relative to the application classpath. The sample NYSE.txt file is packaged into the war file istelf.
The second parameter 'outputFile' is the name of the Jasper report file being generated if the job is successful. The path is relative to the application current execution folder.