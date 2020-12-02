## The project requires the following jars.

### Add the Javafx lib folder to the project select -> Project Structure -> Libraries -> add java and point to the downloaded javaFx sdk lib folder.

### The Following Libraries are downloaded from Maven
* mysql:mysql-connector-java:8.0.21
* sqlite-jdbc-3.32.3.2
* com.github.nbaars:pwnedpasswords4j-client:1.1.0
* org.testng:testng:6.14.3

### Add the VM Options to the Run Edit configuration
--module-path "C:\Program Files\Java\javafx-sdk-15\lib" --add-modules javafx.controls,javafx.fxml


modify if the javafx-sdk is located in a different directory.

### If you need to modify the sql database schema modify the exportdb.sql script to reflect the required changes.

### Login with the following credentials 
* user password
* super password
* admin password
