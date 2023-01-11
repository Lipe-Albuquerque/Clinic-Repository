# 👩🏻‍⚕️ Clinic 👨🏻‍⚕️

To use the program, simply register, informing common data such as name, CPF (for the health professional, the CRM number for example), Patients will be able to search for which doctor will be available, they will be able to see their own appointments, change their dates and times, change professionals or even cancel an appointment.


## Project Versioning 🧩
### Technologies : ![Git](https://img.shields.io/badge/-Git-black?style=flat&logo=git)&nbsp; ![GitHub](https://img.shields.io/badge/-GitHub-black?style=flat&logo=github)&nbsp;

<br>
<br>
<br>


The packages were structured following the MVC (Model, View and Controller) Standard. We created and deployed a REST (REpresentational State Transfer) API using SpringBoot and MySQL, applying JPA (Java Persistence API) and Hibernate (an object/relational mapping tool for Java environments).

We applied data validation and exception handling and properly use the HTTP protocol. 

The REST layer -  where we implemented our business logic - and the service layer,  has the power to access the data layer and the repositories. The services and repositories are the ones that communicate with the application's domain classe. In the classes, we are using the DTO (Data Transfer Object) Standard. 
<br>
<br>
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
4. To install Spring Tools Suite
    - Download Spring Tools Suite [here](https://spring.io/tools)
    - Double click on the downloaded file to unzip.
    - Wait for unpacking.
    - A folder called ``sts-x.xx.x.RELEASE`` will be created in the unzip location.
    - Move the folder to a location of your choice.
    -Select the ``SpringToolSuite4`` application from inside the folder and create a shortcut on the desktop for easy access.
5. Make a project copy to your machine:

     `git clone https://github.com/Lipe-Albuquerque/Clinic-Repository.git`
6. Import the project into your Spring Tools Suite.
    - File -> Import -> Maven -> Existing Maven Projects -> Next -> Browse -> Select Downloaded Project -> Finish
7. A change to the application.properties file is required.
    - It is necessary to define your bank address, username and password.
    - The file will look like this:
    ```` 
    spring.datasource.url=jdbc:mysql://localhost:3306/clinic
    spring.datasource.username=root
    spring.datasource.password=root
    ````

## **MySql**
>  To run the project, a database called `clinic` should be created. Spring is responsible for creating the tables.

1.  To create the database, open MySql Workbench. If you don't have it, install [here.](https://dev.mysql.com/downloads/workbench/)
2.  To create a schema, find a button with the database icon.
3.  Enter the bank name in the ``clinic``` case. Pay attention to the spelling, otherwise, Spring won't recognize the new schema.
4.  Finally click apply.

<br>

## **Testing the requests**
> Postman was the software we choose to test the application requests. It can be accessed on the browser or be downloaded [here.](https://www.postman.com/downloads/).

[here](https://github.com/Lipe-Albuquerque/Clinic-Repository/tree/master/requestsClinic)´postman requests.


<table align="center">
  <tr>
  <h1 align="center">
    <td align="center"> 
      <a href="https://github.com/Lipe-Albuquerque">
        <img src="https://avatars.githubusercontent.com/u/98127981?s=400&u=ef1069ab56dbce000d7bbd088b4bfa14910750dd&v=4"
     alt="Filipe Albuquerque" width="250px;" height="250px"/>
      <br />
      <a href="https://github.com/oliota">Rubem Oliota</a>
    </td>     
   </tr>
 </table>
 <br>
 <br>

