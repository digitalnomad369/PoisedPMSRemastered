# PoisedPMSRedesigned

# TABLE OF CONTENTS # 

[System Objectives and Description](#System-Objectives-and-Description)

[System Requirements and Installation](#System-Requirements-and-Installation)

[Java Packages](#Java-Packages)

[Application components](#Application-components)

[Sample output](#Sample-output)

[Contributors](#Contributors)

[License and Copyright](#License and Copyright)

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

				  
