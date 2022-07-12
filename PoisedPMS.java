
/** Poised Project Management System
 *  <p>
 *  
 * The Poised Project Management System creates, tracks and updates details of building projects 
 * and all individuals involved in Poised, the engineering firm's projects from the PoisePMS database.
 * <p>
 * This is the main class of the program interacts with all other classes 
 * in this project and an external database PoisePMS, based on the user's unique selections. 
 * It then analyzes and implements the user's selections by calling relevant methods 
 * from other external classes. 
 * Each of these methods are used to create and launch user-specific queries to interact with
 * the database.
 *
 * <p>
 * @author Tammy-Lee Bastian
 * @version 1.0
 * @since 01-07-2022 */

import java.sql.*;
import java.util.*;
import java.util.Date;

public class PoisedPMS {
	
	/* Initialize and define static and final variables accessible to other classes */
	static ResultSet res;
	static int affectedRows;
	final static Scanner userInput = new Scanner(System.in);
	protected static String queryComplete = "\nQuery has successfully been completed. ";
	static String sqlQuery;
	final static String SQL_ERROR_MESSAGE = "\nAn unexpected SQLException has occurred. \nPlease check"
			+ " your syntax."; 
	protected static Date today = new Date();
	final static String INDEX_ERROR = "Invalid input. Project number does not exist.";
	final static String FORMAT_ERROR = "Please enter the correct integer format.";
	final static String STR1 = "\nID: ";
	final static String STR2 = "\nName: ";
	final static String STR3 = "\nContact number: ";
	final static String STR4 = "\nE-mail address: ";
	final static String STR5 = "\nResidential address: ";
	static String projID;
	
	/* main method of the program */
	public static void main(String[] args) throws Exception {
		
		/* Call getConnection() method to connect and interact with the PoisePMS database */
		Connection.getConnection();
		Statement statement = Connection.getStatement();
		
		/* read and view existing project details and various people involved in projects
		 * from the PoisePMS database */
		ReadProjects.readProjects(statement);
		ReadProjects.readPeople(statement);
		
		while (true) {
			try {
				/* Display Welcome screen and menu to user.
				 * Prompt user to input their option */
				System.out.println(""" 
						   \n***** Welcome to the Poised Project Management System! *****
	                       \nPlease select one of the following options:\n
							1.  Create a new project
							2.  Update the due date of an existing project
							3.  Update the total fee paid to date 
							4.  Update the contractor's contact details
							5.  View a project
							6.  View completed projects
							7.  View incomplete projects
							8.  View overdue projects
							9.  Finalize an existing project 
							0.  Exit Program """);
				
				int userChoice = userInput.nextInt();
				
				/* Use switch statements to analyze user's options */
				switch(userChoice) {
				
				/* Call the exitProgram() method to end the program if user selects 0 */
				case 0:
					PoisedPMS exit = new PoisedPMS();
				    System.out.println(exit.exitProgram());
				    break;
				
				/* Capture information about new projects and add it to the db */
				case 1:
					CaptureProjects.captureProject(statement);
					break;
				
				/* Update the due date of projects and write it to the database */
				case 2:
					ProjectUpdates.updateDueDate(statement);
					break;
					
				/* Update the paid fees of projects and write it to the database */	
				case 3:
					ProjectUpdates.updatePaidFee(statement);
					break;
				
				/* Update the contact details(phone number and email) of contractors on the db */
				case 4:
					ProjectUpdates.updateContractorDetails(statement);
					break;
				
				/* View a selected project from the database by ID */
				case 5:
					ViewProjects.viewAProject(statement);
					break;
					
				/* View finalized projects */
				case 6:
					ViewProjects.viewCompletedProjects(statement);
					break;
				
				/* View incomplete projects */
				case 7:
					ViewProjects.viewIncompleteProjects(statement);
					break;
					
				/* View overdue projects */	
				case 8:
					ViewProjects.viewOverdueProjects(statement);
					break;
				
				/* Finalize projects */	
				case 9:
					FinalizeProjects.finalizeProj(statement);
					break;
				
				/* Display appropriate error message for invalid number entered */
				default:
					System.out.println("\nYou have made an invalid selection. Please try again.");
				}
				
				/* Close all connections once user enters 0 to exit program
				 * Loop breaks */
				if(userChoice == 0) {
					res.close();
					statement.close();
					userInput.close();
				    break;
				    
				} else {
				    continue;
				}
				
			/* Display appropriate message if user does not enter valid integer */	
			} catch (Exception e) {
				System.out.println("Invalid user input. Please enter an integer value to make a valid selection.");
			}
		}
	}
	
	/** Method that allow users to exit the program 
	 * @return returns "Goodbye" and exits the program */
	public String exitProgram()	{
		return "Goodbye!";
	}	
}