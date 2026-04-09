#Spongebob Squarepants Database

The Spongebob Squarepants Database designed to be accessed through a CLI made in Java to store information about episodes from the show Spongebob Squarepants. 

##Database File Location
File Name: spongebob.db
Location: Inside the SponegbobDatabase source folder (SponegbobDatabase/src)
Sqlite3 command line tool: 
1. cd SponegbobDatabase/src
2. sqlite3 spongebob.db

##How to Run
1. Compile the program using the following command
   -  javac -cp ".:sqlite-jdbc-3.51.3.0.jar" Interface.java
  
2. Run the program using the following command
   - java -cp ".:sqlite-jdbc-3.51.3.0.jar" Interface


##Notes
- There is a spelling mistake in the java file, be sure to run with "SponegbobDatabase" and not "SpongebobDatabase"
- In order to access the database using java the program uses a JBDR jar file
