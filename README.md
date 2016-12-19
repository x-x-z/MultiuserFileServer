## SETTING UP WORKSPACES:
1. Install [JDK] (http://www.oracle.com/technetwork/java/javase/downloads/2133151)
2. [Download] (http://maven.apache.org/download.cgi) and [install] (http://maven.apache.org/install.html) MAVEN 
3. Install your favorite IDE (IntelliJ IDEA, Eclipse, etc.)
4. Install [PostgreSQL] (https://www.postgresql.org/download/) and create local database with name *mfs*

## HOW TO MAKE CHANGES IN THE PROJECT:
1. [Clone] (https://github.com/x-x-z/MultiuserFileServer.git) or 
[Download] (https://github.com/x-x-z/MultiuserFileServer/archive/master.zip) the project
2. Open project in IDE and try to run some tests from *src/test/java/* folder. In ‘IDEA’ you can open test class (eg. ‘SharedFileTest’) and click on the ‘Run’ button. Make sure that the tests will be passed.
3. *Make your changes in the project ...*
4. Open console in the root project folder and execute the commands: *mvn spring-boot:run*
5. Make sure that the project has been compiled, and there are no errors in the console
6. Your project will be started on port 8080. You can check this if you open the browser at *localhost:8080/*
7. Commit your changes in to your GitHub repository if all is fine. Send a request to the author to add your changes to the main project

## HOW IT WORKS:
The database contains four tables that store users and a list of shared files:
![database structure](https://github.com/x-x-z/Files/blob/master/MultiuserFileServer/images/fileserver_db.png)

The project has the following structure:
![project structure](https://github.com/x-x-z/Files/blob/master/MultiuserFileServer/images/diagram.png)

### Available requests:

1.Sign up:  
*http://localhost:8080/signup*
  
> Headers  
```
Content-Type: application/json
```
> Payload  
```
{
  	"login":"kostin",
    "password" : "123",
    "firstName":"Denis",
    "middleName":"Vladimirovich",
    "lastName":"Kostin"  
}
```
  
2.File explorer:  
*/file?path=*  
  
> *path* - path to folder or file. The file will be downloaded automatically