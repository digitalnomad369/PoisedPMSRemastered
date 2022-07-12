# PoisedPMSRedesigned

# TABLE OF CONTENTS # 

[System Objectives and Description](#System-Objectives-and-Description)

[System Requirements and Installation](#System-Requirements-and-Installation)

[Java Packages](#Java-Packages)

[Application components](#Application-components)

[Sample output](#Sample-output)

[Contributors](#Contributors)

[License and Copyright](#License-and-Copyright)

# System Objectives and Description
		
PoisePMS is a project management software system that aims to capture, track and update information relevant to various 
building projects and the respective individuals (e.g. customers, structural engineers) involved in each of Poised's (small structural engineering company) 
projects.

The program also interacts with an external database called PoisePMS used for the capturing, tracking and storing all relevant project details. 
The system must able to achieve the following:
		   
1. Read details about existing projects and people from the PoisePMS database;
2. Capture information about new projects and individuals involved in the projects and write 
	 these details to the database;
3. Update the due date of incomplete proojects and write these to the database;
4. Update and write amended contact details of respective contractors to the database;
5. Update the project fee paid to date and write the updated amount to the PoisePMS database;
6. Enable users to view incomplete, overdue and completed projects from the database; 
7. Finalize existing projects once all outstanding project fees have been paid in full and
	 generate invoices to customers for outstanding balances; 

# System Requirements and Installation 

This system is written in Java and is connected to the mySQL server via the MySQL Connector/J which is the official JDBC driver for MySQL. 

In order to run and test this code, and interact with the database, the following software is required:

__- The Java Development Kit v17 or higher:__
To download the JDK, click on the following link: *https://www.oracle.com/java/technologies/downloads/* 

__- Eclipse IDE:__
    - To download Eclipse, click on the following: *https://www.eclipse.org/downloads/*
    
Alternatively, you can download the IntelliJ IDEA which also supports compatibility for Java code

    - To download the IntelliJ IDEA click on the following link: *https://www.jetbrains.com/idea/download/*

You are also required to download the MySQL Community Server in order to create and access databases and to download the Connector/J JDBC driver
- To download MySQL access the following link: *https://dev.mysql.com/downloads/mysql/*

# Java Packages

The following built-in Java packages are imported to the below .java files:

__-CaptureProjects.java__
	- java.sql Package 
			
__-Connection.java__
	- java.sql Package 
			
__-FinalizeProjects.java__
	- java.sql Package 
	- java.text.SimpleDateFormat Package
			
__-PoisedPMS.java___
	- java.sql Package 
	- java.util Package
			
__-PrintAll.java__
	- java.sql Package 
			
__-ProjectUpdates.java__
	- java.sql Package 
			
__-ReadProjects.java__
	- java.sql Package 
			
__-ViewProjects.java__
	- java.sql Package 
	- java.text.SimpleDateFormat Package

#  Application Components

This application is comprised of 8 .java source files with the objective of improving the general 
readability of the code and separating code blocks into select methods in relevant classes. 
			
The system contains the following files:

__1. Connection.java:__

The Connection class is used to establish a connection to the target database. 
It consists of two getMethods: the getConnection() method is used to connect to a Java database and
the getStatement() method used to access a sql statement. Both methods are accessible to other methods outside the target class. 

__2. PoisePMS.java:__

This is the main source file that interacts with all other classes in this application based on the user's input and selected options. 
It then analyzes and implements the user's selections by calling relevant methods from other external classes. Each of these methods 
are used to create and launch user-specific queries to interact with the database.

__2. CaptureProjects.java:__

The CaptureProjects class is used to capture and write details about new projects and people to the PoisePMS database.
It contains the following 6 static methods:

	- captureProject() method:
	  This method enables users to enter and capture information 
	  relevant to new projects, such as the project fee, project name and unique project ID 
	  and writes these details to the Project table in the PoisePMS database. 
				  
	  The Project table in the database also contains unique IDs for each individual involved in a particular
	  project which links the individual to its respective table in the database. 
				
	- captureEngineer() method: 
	  This method enables users to capture all personal details, such as the name, contact details and physical address 
	  of the structural engineer involved in the new project and writes these captured details to the Structural_Engineer 
	  table of the PoisePMS database.
				  
	- captureManager() method: 
	  This method enables users to capture all personal details, such as the name, contact details 
	  and physical address of the project manager involved in the new project and writes these captured details to the 
	  Project_Manager table of the PoisePMS database.
				
	- captureCustomer() method: 
          This method enables users to capture all personal details, such as the name, contact details and physical address of 
	  the client involved in the new project and writes these captured details to the Customer table of the PoisePMS database.
				
	- captureArchitect() method: 
	  This method enables users to capture all personal details, such as the name, contact details and physical address of the 
	  architect involved in the new project and writes these captured details to the Architect table of the PoisePMS database.
				
	- captureContractor() method: 
	  This method enables users to capture all personal details, such as the name, contact details and physical address of the 
	  contractor involved in the new project and writes these captured details to the Contractor table of the PoisePMS database.
				  
	Once a new project and relevant particulars involved in project is successfully captured and added to the database, an 
	updated list of existing projects and people will be displayed from the database.

			
			
				




				  
