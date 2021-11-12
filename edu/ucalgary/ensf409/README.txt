This is a README file for the ENSF 409 Project for group 43.
Group Contributors:
  Ryan Sommerville
  Noel Thomas
  Renno Eric
 
 Finished: 04-14-2021
 Last Editted: 04-14-2021
 
 The purpose of the project is to make an interface that can
 take inputs on desired furniture and produce an order with 
 the most cost efficient way to produce that furniture.
 
 The program consists of the following files and directories:
 
 edu
	ucalgary
		ensf409
			Manufacturer.java
			Furniture.java
			FurnitureList.java
			FurnitureOrder.java
			DatabaseIO.java
			OrderFormIO.java
			Runner.java
			Tester.java
			FurnitureTypeNotFoundException.java
			OrderCannotBeCompletedException.java
lib
	hamcrest-core-1.3.jar
	inventory.sql
	junit-4.13.2.jar
	mysql-connector-java-8.0.23.jar
	
For the program to work, the running computer must
have MySQL downloaded and have a database called inventory in it
which contains the tables: chair, desk, lamp, filing, and manufacturer.

To run the Runner (For Windows):
	In the command line go to the directory before the edu and lib folders.
	Type in the following command:
	
		javac -cp .;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Runner.java
	
	And once the program compiles:
	
		java -cp .;lib/mysql-connector-java-8.0.23.jar edu.ucalgary.ensf409.Runner
	
	And follow the commands on the terminal. The output file will be created in the
	directory you are currently in, and if there is a file with the same name in that
	directory then it will be overwritten.

To run the Tester (For Windows):
	In the command line go to the directory before the edu and lib folders.
	Type in the following command:
	
		javac -cp .;lib/junit-4.13.2.jar;lib/hamcrest-core-1.3.jar;lib/mysql-connector-java-8.0.23.jar edu/ucalgary/ensf409/Tester.java
	
	And once the program compiles:
	
		java -cp .:lib/junit-4.13.2.jar:lib/hamcrest-core-1.3.jar:lib/mysql-connector-java-8.0.23.jar org.junit.runner.JUnitCore edu.ucalgary.ensf409.Tester