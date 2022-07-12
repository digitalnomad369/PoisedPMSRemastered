/** The CaptureProjects class is used to capture and write details 
  * about new projects and people to the PoisePMS database
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 01-07-2022 */

import java.sql.*;

public class CaptureProjects extends PoisedPMS {
	
	/* Initiate static String variables to access in different methods of class */
	private static String seID;
	private static String pmID;
	private static String arcID;
	private static String cusID;
	private static String conID;
	private static String custName;
	
	/** Method that captures information about new projects 
	 * 
	 *  @param statement */
	static void captureProject(Statement statement) {
		while (true) {
			
			/* add try and catch blocks to catch potential SQLExceptions and 
			 * to ensure the user enters the correct integer/float formats where requested */
			try {
				/* Enter project number */
				System.out.println("PROJECT DETAILS");
				System.out.println("Please enter the project ID: ");
				String projectID = userInput.next();
				userInput.nextLine();
				
				/* Enter name of the project */
				System.out.println("Please enter the name of the project: ");
				String projectName = userInput.nextLine();

				/* Enter the project type (house, apartment, etc) */
				System.out.println("Please enter the project type: ");
				String projectType = userInput.next();
				userInput.nextLine();
				
				/* Enter the physical address of the project */
				System.out.println("Please enter the physical address of the project: ");
				String projectAddress = userInput.nextLine();
					
				/* Enter the ERF number */
				System.out.println("Please enter the ERF number: ");
				int erfNumber = userInput.nextInt();
				
				/* Enter the total fee payable */
				System.out.println("Please enter the total fee for the project: ");
				float totalFee = userInput.nextFloat();
				
				/* Enter the amount paid to date */
				System.out.println("Please enter the fee paid to date: ");
				float paidAmount = userInput.nextFloat();
				
				/* Enter deadline date */
				System.out.println("Please enter the deadline date for the project in the following format - "
						+ "(yyyy-MM-DD): ");
				String projectDeadline = userInput.next();
				userInput.nextLine();
				
				/* Enter structural engineer's ID */
				System.out.println("Please enter the structural engineer's ID: ");
				seID = userInput.next();
				userInput.nextLine();
				
				/* Enter project manager's ID */
				System.out.println("Please enter the project manager's ID: ");
				pmID = userInput.nextLine();
				
				/* Enter architect's ID */
				System.out.println("Please enter the architect's ID: ");
				arcID = userInput.nextLine();
				
				/* Enter customer's ID */
				System.out.println("Please enter the customer's ID: ");
				cusID = userInput.nextLine();
				
				/* Enter contractor's ID */
				System.out.println("Please enter the contractor's ID: ");
				conID = userInput.next();
				userInput.nextLine();
				
				/* Create SQL Query to insert captured values into Project table 
				 * of the PoisePMS */
				sqlQuery = "INSERT INTO Project VALUES(" + "'" + projectID 
						+ "', " + "'" + projectName + "', " +  "'" + projectType + "'," 
						+ "'" +  projectAddress + "'," +  "'" +  erfNumber + "'," 
						+  "'" + totalFee + "'," + "'" + paidAmount + "'," 
						+  "'" + projectDeadline + "'," +  "'" + "No" +  "'," 
						+  "'" +  "0000-00-00"  +  "'," +  "'"  + seID + "'," 
						+  "'" + pmID +  "'," +  "'" + arcID +  "'," +  "'" + cusID + "'," 
						+  "'" +  conID + "')";	
				
				/* Count the number of records added */
				affectedRows = statement.executeUpdate(sqlQuery);
				/* Execute and update query and display number of added records */
				System.out.println (queryComplete +  affectedRows + " records inserted.\n" );
				
				/* Call the captureCustomer() method to capture details about the customer */
				captureCustomer(statement);
				
				/* If the project name is empty, set it equal to the project type
				 * + the customer's last name  */
				String [] custSplitName = custName.split(" ");
				String custSName = custSplitName[1];
				String projName = projectType + " " + custSName;
				String sqlQuery1 = "UPDATE Project SET Proj_Name = '" + projName
						+ "' WHERE Proj_Name =  ''";
				affectedRows = statement.executeUpdate(sqlQuery1);
				System.out.println (queryComplete +  affectedRows + " records inserted.\n");
				/* display updated projects including newly captured project */
				PrintAll.displayAllProjects(statement);
				
				/* Call static methods to capture information about individuals
				 * involved in the project  */
				captureEngineer(statement);
				captureManager(statement);
				captureArchitect(statement);
				captureContractor(statement);
				
				/* Display updated information about all engineers, project managers,
				 * architects, customers and contractors on PoisePMS db. */
				ReadProjects.readPeople(statement);
				break;
				
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
	    	} 
		}
	}
	
	/** This method captures information about the structural engineer 
	 *  involved in the new project
	 *  
	 * 	@param statement */
	static void captureEngineer(Statement statement) {
		while(true) {
			try {
				/* Enter the structural engineer's personal details */
				System.out.println("STRUCTURAL ENGINEER DETAILS");
				System.out.println("\nPlease enter the structural engineer's name: ");
				String seName = userInput.nextLine();
				
				System.out.println("Please enter the engineer's contact number: ");
				String sePhoneNo = userInput.next();
				userInput.nextLine();
		
				System.out.println("Please enter the engineer's e-mail address: ");
				String seEmail = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the engineer's residential address: ");
				String seAdd = userInput.nextLine();
				
				/* Create and execute SQL Query to insert captured values into Structural_Engineer
				 * table of PoisePMS */
				sqlQuery = "INSERT INTO Structural_Engineer VALUES("
						+ "'" + seID + "', " + "'" + seName + "', " + "'" + sePhoneNo + "', " 
						+ "'" + seEmail +  "', " + "'" + seAdd + "')";
				
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println (queryComplete +  affectedRows + " records inserted.\n" );
				break;
				
			/* catch potential exceptions and display customized error message */		
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
	    	}
		}
	}
	
	/** This method captures information about the person involved
	 *  in managing the project
	 *  
	 * 	@param statement */
	static void captureManager(Statement statement) {
		while(true) {
			try {
				/* user is prompted to enter the project manager's personal details */
				System.out.println("PROJECT MANAGER DETAILS");
				System.out.println("Please enter the project manager's name: ");
				String pmName = userInput.nextLine();
				
				System.out.println("Please enter the project manager's contact number: ");
				String pmPhoneNo = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the e-mail address of the project manager: ");
				String pmEmail = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the project manager's residential address: ");
				String pmAdd = userInput.nextLine();
				
				/* Create String SQL Query to insert captured values into Project_Manager
				 * table of the PoisePMS db */
				sqlQuery = "INSERT INTO Project_Manager VALUES("
						+ "'"+ pmID + "', " + "'" + pmName + "', " + "'" + pmPhoneNo + "', " 
						+ "'" + pmEmail +  "', " + "'" + pmAdd + "')";
		
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println (queryComplete + affectedRows + " record/s inserted.\n");
				break;
				
			/* try and catch blocks handles potential exceptions */	
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
			}
		}
	}
	
	/** This method captures information about the architect
	 * 
	 * 	@param statement */		
	static void captureArchitect(Statement statement) {
		while(true) {
			try {
				/* Enter the architect's personal details */
				System.out.println("ARCHITECT DETAILS");
				System.out.println("Please enter the architect's name: ");
				String archName = userInput.nextLine();
				
				System.out.println("Please enter the architect's contact number: ");
				String  archPhoneNo = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the architect's e-mail address: ");
				String  archEmail = userInput.next();
				userInput.nextLine();
		
				System.out.println("Please enter the architect's residential address: ");
				String archAdd = userInput.nextLine();
				
				/* Execute Query to insert captured values into Architect table of database */
				sqlQuery = "INSERT INTO Architect VALUES("
						+ "'" + arcID + "', " + "'" +  archName + "', " 
						+ "'" + archPhoneNo + "', " + "'" + archEmail +  "', " 
						+ "'" +  archAdd + "')";
				
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println (queryComplete +  affectedRows + " record/s inserted.\n" );
				break;
				
			/* try and catch blocks handles potential exceptions */
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
			}
		}
	}
			
	/** This method captures information about the customer
	 * 	@param statement */				
	static void captureCustomer(Statement statement) {
		while(true) {
			try {
				/* user is prompted to enter the customer's personal details */
				System.out.println("CUSTOMER DETAILS");
				System.out.println("Please enter the customer's name: ");
				custName = userInput.nextLine();
				
				System.out.println("Please enter the customer's contact number: ");
				String  custPhoneNo = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the customer's e-mail address: ");
				String  custEmail = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the customer's residential address: ");
				String custAdd = userInput.nextLine();
				
				/* Create and execute SQL Query to insert captured values into Customer
				 * table of PoisePMS db */
				sqlQuery= "INSERT INTO Customer VALUES("
						+ "'"+ cusID + "', " + "'" +  custName + "', " 
						+ "'" + custPhoneNo + "', " + "'" + custEmail +  "', " 
						+ "'" + custAdd + "')";
				
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println (queryComplete +  affectedRows + " record/s inserted.\n" );
				break;
				
			/* catch potential exceptions and display customized error message */	
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
			}
		}
	}
	
	/** This method captures information about the contractor 
	 *  involved in the project
	 *  
	 * 	@param statement */		
	static void captureContractor(Statement statement) {
		while(true) {
			try {
				/* user is prompted to enter the contractor's personal details */
				System.out.println("CONTRACTOR DETAILS");
				System.out.println("Please enter the contractor's name: ");
				String contName = userInput.nextLine();
				
				System.out.println("Please enter the contractor's contact number: ");
				String contPhoneNo = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the contractor's e-mail address: ");
				String contEmail = userInput.next();
				userInput.nextLine();
				
				System.out.println("Please enter the contractor's residential address: ");
				String contAdd = userInput.nextLine();
				
				/* Execute Query to insert captured values into Contractor table of database */
				sqlQuery = "INSERT INTO Contractor VALUES("
						+ "'"+ conID + "', " + "'" + contName + "', " 
						+ "'" + contPhoneNo + "', " + "'" + contEmail +  "', " 
						+ "'" + contAdd + "')";
				
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println (queryComplete +  affectedRows + " record/s inserted.\n" );
				break;
			
			/* catch potential exceptions and display customized error message */
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
			}
		}
	}
}