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

__3. CaptureProjects.java:__

The CaptureProjects class is used to capture and write details about new projects and people to the PoisePMS database.
It contains the following 6 static methods:

- captureProject() method:
  This method enables users to enter and capture information relevant to new projects, such as the project fee, 
  project name and unique project ID and writes these details to the Project table in the PoisePMS database. 
  
The Project table in the database also contains unique IDs for each individual involved in a particular
project which links the individual to its respective table in the database. 
				
- captureEngineer() method: 
  This method enables users to capture all personal details, such as the name, contact details and physical address 
  of the structural engineer involved in the new project and writes these captured details to the Structural_Engineer 
  table of the PoisePMS database.
				  
- captureManager() method: 
  This method enables users to capture all personal details, such as the name, contact details and physical address 
  of the project manager involved in the new project and writes these captured details to the Project_Manager table of 
  the PoisePMS database.
				
- captureCustomer() method: 
   This method enables users to capture all personal details, such as the name, contact details and physical address of the 
   client involved in the new project and writes these captured details to the Customer table of the PoisePMS database.
   
- captureArchitect() method: 
  This method enables users to capture all personal details, such as the name, contact details and physical address of the 
  architect involved in the new project and writes these captured details to the Architect table of the PoisePMS database.
				
- captureContractor() method: 
  This method enables users to capture all personal details, such as the name, contact details and physical address of the 
  contractor involved in the new project and writes these captured details to the Contractor table of the PoisePMS database.
				  
Once a new project and relevant particulars involved in project is successfully captured and added to the database, an updated
list of existing projects and people will be displayed from the database.
	
__4. ProjectUpdates.java:__

The ProjectUpdates class allow users to execute update queries to amend particular details of existing projects or people. 
It encompasses 3 static methods - each used to update select information about existing projects:

-  updateDueDate() method: 
   This method allows users to amend the deadline of existing and incomplete projects. If a project's completion 
   status equals to "Finalized", no updates will be made to the requested project's due date as the project has 
   been finalized. An updated list of projects will be displayed to the user, reflecting the updated due date. 
   
- updatePaidFee() method: 
  This method enables users to update the paid amount to date of existing and incomplete projects if a client has 
  made payment. As with the updateDueDate() method, the query will only be successful if the project completion status 
  equals to "No." The paid project fee will not be updated for finalized projects. An updated list of projects will be 
  displayed to the user, reflecting the updated paid fee of the project. An invoice will also be shown, reflecting the 
  total amount paid and the owed balance owed by the client. 
				  
- updateContractorDetails() method: 
  This method allows a user to amend the contact details (contact number and email address) of contractors involved in
  existing and incomplete projects. Once the relevant contact details of the contractor has been succesfully updated
  and written to the database, an updated list of all contractors will be printed, reflecting the updated contact information.
	  
__5. PrintAll.java:__

The PrintAll class contains code to display all details about projects and people (e.g. customers, project managers, contractors) 
involved in projects. This class contains the following static methods:

-  displayAllProjects() method: 
   This method runs a SELECT statement on the Project table to return all results of existing projects and relevant project
   details from the database.
   
-  displayAllEngineers() method: 
   This method runs a SELECT statement on the Structural_Engineer table to return all results of existing structural engineers
   from the database.
				
-  displayAllManagers() method: 
   This method runs a SELECT statement on the Project_Manager table to return all results of existing project managers
   from the database.
   
-  displayAllArchitects() method: 
   This method runs a SELECT statement on the Architect table to return all results and information of all existing architects 
   from the database.
				
-  displayAllCustomers() method: 
   This method runs a SELECT statement on the Customer table to return all results of existing customers from the database.
   
-  displayAllContractors() method: 
   This method runs a SELECT statement on the Contractor table to return all results and information of existing contractors
   from the database.

__6. ReadProjects.java:__

This code calls relevant static methods from the PrintAll class to display and read information about existing projects and 
people involved in projects
It contains the following 2 static methods: 
			   
- readProjects(): 
  This method calls the displayAllProjects() method from the PrintAll class to return information about projects on 
  the PoisePMS database.
			
- readPeople(): 
  This method calls the displayAllEngineers(), displayAllManagers(), displayAllArchitects(), displayAllCustomers() 
  and displayAllContractors() methods to print these details in succession.
			   
The ReadProjects.readProjects() and ReadProjects.readPeople() methods are called at the start of the program to display 
existing projects and people contained in the database.

__7. ViewProjects.java:__

This class enables users to view projects based on completion status or unique project IDs.
It comprises the following 4 static methods:
				
- viewAProject(): This method enables users to view a specific project by entering its unique project ID. 
				
- viewCompletedProjects(): This method enables users to view all complete projects from the database - that is projects 
  with a completion status equalled to "Finalized".
  
- viewIncompleteProjects(): This method enables users to view all incomplete projects from the database - that is projects
  with a completion status equalled to "No".
				
- viewOverdueProjects(): This method allow users to view all overdue projects from the database. Overdue projects qualify as projects 
  with a completion status equalled to "No" and  a deadline smaller than the current date.

__8. FinalizeProjects.java:__

This code enables users to finalize projects or displays an invoice to the customer if there is an outstanding project fee 
still payable. It consists of the following 2 static methods:
			   
- finalizeProj(): This method executes a query to update an existing project's completion status to finalized on 3 where conditions: 
  1. project ID must correspond with that which is entered by the user,
  2. the project completion must be equal to "No", 
  3. and the project's fee must equal the paid project fee 
     (I.e. there must be no outstanding fees owed on the project)
     
- viewInvoice(): This method displays invoice and customer details. If a user wishes to finalize a project and there 
  is still outstanding fees payable, the viewInvoice() method is called and displays an invoice with customer details.
  In this case, the project will not be finalized. 
  
# Sample output

![output1_poisedpms](https://user-images.githubusercontent.com/102178512/178512964-d57efb05-f9ab-4565-ba18-3650a76f2352.jpg)

![output_2_poisedpms](https://user-images.githubusercontent.com/102178512/178512526-5238f209-e783-4036-b731-ad5d63afbab3.jpg)

![output3_poisedpms](https://user-images.githubusercontent.com/102178512/178512598-7cf9308c-5dd8-4ec9-a3e0-56f858f24112.jpg)

![output4_poisedpms](https://user-images.githubusercontent.com/102178512/178512645-e3260bb1-e627-4ec7-b208-ef9cb742993b.jpg)

![output5_poisedpms](https://user-images.githubusercontent.com/102178512/178512675-0a13d988-1adc-401d-b550-b5f1c8644261.jpg)

![output6_poisedpms](https://user-images.githubusercontent.com/102178512/178512719-9c1aba82-242f-49f3-85ae-c713bfc43ad4.jpg)

# Contributors

Tammy-Lee Bastian, HyperionDev
 
tammyleebastian@gmail.com

# License and Copyright

  © Tammy-Lee Bastian
  
  Licensed under the [MIT License](MIT_LICENSE_FILE)
