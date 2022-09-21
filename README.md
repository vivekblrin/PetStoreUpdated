The project uses test NG and rest template to test some of endpoint in swagger api  and generate  test coverage output as html reports

===========================================================================

swagger api to test :- https://petstore.swagger.io/

============================================================================

Install Java 1.8.0_331
Intall apache-maven-3.8.6
install Git and Git bash
JAVA_HOME= D:\Java\jdk1.8.0_331
adding following to PATH SYSTEM variable
PATH=D:\apache-maven-3.8.6\bin;C:\Program Files\Git\cmd;C:\Program Files\Git\bin
Intall apache-maven-3.8.6
install Git an Git bash


============================================================================

Generate Report via eclipse IDE
import the project as maven project to Eclipse IDE and TestNG plugin to eclipse IDE.
in Elipse Package Explorer view run the src/test/java as TestNg which
will generate test-output/emailable-report.html
and test-output/index.html

or 

============================================================================

Generate Report via command line
got to the project  root folder example:- D:\PetStoreUpdated\
via command line run
mvn clean install 
will generate 

test-output/emailable-report.html

test-output/index.html

============================================================================ 

Generate Report via scripts
alternatively we can run
 
PetStoreUpdated_build.bat or PetStoreUpdated_build.sh
