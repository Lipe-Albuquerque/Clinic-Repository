# 👩🏻‍⚕️ Clinic 👨🏻‍⚕️

To use the program, simply register, informing common data such as name, CPF (for the health professional, the CRM number for example), they will be able to see their own appointments, change their dates and times, change professionals or even cancel an appointment.


## Project Versioning 🧩
<br>

### Technologies : ![Git](https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white)&nbsp; ![GitLab](https://img.shields.io/badge/GitLab-330F63?style=for-the-badge&logo=gitlab&logoColor=white)&nbsp;

<br>
 

The packages were structured following the MVC (Model, View and Controller) Standard. i created and deployed a REST (REpresentational State Transfer) API using SpringBoot and Postgres, applying JPA (Java Persistence API) and Hibernate (an object/relational mapping tool for Java environments).


The REST layer -  where i implemented business logic - and the service layer,  has the power to access the data layer and the repositories. The services and repositories are the ones that communicate with the application's domain classe.
<br>



## Project installation requirements

## **Spring**

1. To run the project properly, some development tools must be installed. Java JDK, Spring tools Suite and Maven must be installed and the environment variable defined.
2. To install Java JDK
    - Download JDK [here](https://www.oracle.com/java/technologies/downloads/#jdk19-windows)
    - Install the JDK exe File
    - Check the Directory ``C:\Program Files\Java\jdk-xxx`` if exists folder jdk;
3. To install Maven (it will compile the application)
    - Download Maven [here](https://maven.apache.org/download.cgi) ;
    - Unzip file; 
    - Create a folder named maven in ``C:\`` ; 
    - Press the Windows key on the keyboard and type: "edit system variables". Click on the option that appears.
    - In the "Environment Variables" screen that will open, click the "New" button just below the part of the screen that says "environment Variables".
    - In the variable name and value fields, fill in: ``MAVEN_HOME`` and ``C:\maven\bin``, respectively. Press "Confirm" button.
    - Check if it worked by opening a new prompt and typing ``mvn -version``. If it worked, an error will appears.
4. To install Eclipse
    - Download Eclipse [here](https://www.eclipse.org/downloads/)
5. Make a project copy to your machine:
      first:
     `git clone git@git.senior.com.br:treinamento/go-dev-2023-spring.git`
      after cloning repository, enter the project folder and change for my branch in gitbash or cmd:
     `git pull origin filipe-albuquerque`
6. Import the project into your Eclipse.
    - File -> Import -> Maven -> Existing Maven Projects -> Next -> Browse -> Select Past of Project -> Finish
7. A change to the application.properties file is required.
    - It is necessary to define your bank address, username and password.
    - The file will look like this:
    ```` 
    spring.datasource.url=jdbc:postgresql://localhost:5432/clinic
    spring.datasource.username=postgres
    spring.datasource.password=root
    ````

## **Postgres**
>  To run the project, a database called `clinic` should be created. Spring is responsible for creating the tables.

1.  To create the database, open postgres. If you don't have it, install [here.](https://www.postgresql.org/download/)
2.  To create a schema.
3.  Create a schema ``clinic``. if you type wrong spring will not recognize the database.

<br>

## **Testing the requests**
> Postman was the software we choose to test the application requests. It can be accessed on the browser or be downloaded [here.](https://www.postman.com/downloads/).

[here](https://git.senior.com.br/treinamento/go-dev-2023-spring/-/tree/filipe-albuquerque/requestsClinic) postman requests.

to import the postman requests, come:
import -> upload -> select the `requestsClinic` file that is in the `requestsClinic` folder in the project.

## **Unitary tests**
> Unit tests performed using the Mockito java library, all CRUD following the unit test pattern.

Tests are located at:

(src / test / java).

can be used with the JUnit library.

<table align="center">
  <tr>
  <h1 align="center">
    <td align="center"> 
      <a href="https://github.com/Lipe-Albuquerque">
        <img src="https://avatars.githubusercontent.com/u/98127981?s=400&u=ef1069ab56dbce000d7bbd088b4bfa14910750dd&v=4"
     alt="Filipe Albuquerque" width="250px;" height="250px"/>
      <br />
      <a href="https://github.com/Lipe-Albuquerque">Filipe Albuquerque</a>
    </td>     
   </tr>
 </table>
 <br>
 <br>
