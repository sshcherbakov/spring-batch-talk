# Spring Batch Demo Application

This is a sample demo applicaiton demonstrating parallel job exection using 
Spring Batch framework remote partitioning feature. There is also a presentation showing important Spring Batch 2.2.0 features and road map for future versions.

## The use case
  The application performs following part in parallel
  * downloads day trading history from Google service
  * reads the downloaded csv files into the MySQL
  * builds a simple Jasper report basing on the imported data

## Building from Source

Clone the git repository using the URL on the Github home page:

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