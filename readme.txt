Task Management Application version 1.0 18/07/2015

--------------------------------------------------------------------------------------------
Description
--------------------------------------------------------------------------------------------

Simple task management application which allows you to create employees, tasks and projects;
update and delete any created entry, appoint employees and projects to tasks.


--------------------------------------------------------------------------------------------
Requirements
--------------------------------------------------------------------------------------------
This application runs on Apache Tomcat server and uses Apache ANT for build process. 
It is also written in Java. It is PREREQUISITE to have JDK, Apache Tomcat and Apache ANT
installed.

Before use, check following environmental variables to be set:
%CATALINA_HOME% - to Tomcat root directory
%ANT_HOME% - to ANT root directory
%JAVA_HOME% - to JDK root directory

You need a web browser to use application as well.

--------------------------------------------------------------------------------------------
Installation Process
--------------------------------------------------------------------------------------------
Use Apache ANT to build and deploy apllication. Use build.xml in root directory to build.

ANT commands: 	

ant - builds and deploys application, opens default web browser on application page
				
ant stop-server - stops the server, copies database from Tomcat webapp directory to 
resource folder, deletes .war file.

WARNING: You need to use ant stop-server command to properly close connections and 
copy database files back to application directory. That was made for portability purposes only.

--------------------------------------------------------------------------------------------
Usage Notes
--------------------------------------------------------------------------------------------

In com.qulix.babichyr.trainingtask.web\src\main\resources\sql directory are located
SQL scripts:

create_tables.sql - Creates Employee, Task, Project tables with identities and references
drop_tables.sql - Deletes Employee, Task, Project tables from database
add_test_info.sql - Adds test information into each table.

You can use it for your own purposes.
Pre-installed database comes with created tables and test information.

