/** The ProjectUpdates class enables users to execute update queries 
  * to amend select details of existing projects or people
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 02-07-2022 */

import java.sql.*;

public class ProjectUpdates extends PoisedPMS {

	/** This method allows users to update the deadline of existing projects
	 * 		
	 *  @param statement */
	static void updateDueDate(Statement statement) {
		while (true) {
			
			/* The try and catch blocks are used to handle potential exceptions */
			try {
				/* User is prompted to enter valid project ID */
				System.out.println("Please enter the ID of the project of which you would like to update the deadline: ");
				projID = userInput.next();
				userInput.nextLine();
				
				/* User is asked to enter updated due date in requested format */
				System.out.println("Please enter the updated due date of the project in the following"
						+ " format (yyyy-MM-dd): ");
				String date = userInput.next();
				userInput.nextLine();
				
				/* Execute query to update Proj_Deadline if Proj_ID matches
				 * user input and project is incomplete */
				sqlQuery= "UPDATE Project SET Proj_Deadline = '" + date
						+ "' WHERE Proj_ID = " + projID + " AND Proj_Completion = '" + "No" + "'";
				
				/* Count updated records */
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println(queryComplete + affectedRows + " records updated.\n");
				
				/* Display customized error message if project is finalized 
				 * Else, display all projects including updated project */
				if(affectedRows == 0) {
					System.out.println("The due date of the requested project cannot be updated "
							+ "as this project has already been finalized.");
				} else {
					PrintAll.displayAllProjects(statement);
				}
				break; 
				
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
				 System.out.println(INDEX_ERROR);
				 return; 
			}
		}
	}
	

	/** This method allows users to update the paid project fee
	 *  when a client makes payment
	 * 		
	 *  @param statement */
	static void updatePaidFee(Statement statement) {
		
		while (true) {
			
			/* add try and catch blocks to catch potential SQLExceptions and 
			 * to ensure the user enters the correct integer/float formats where requested */
			try {
				/* Prompt user for a valid project ID  */
				System.out.println("Please enter the ID of the project of which you would like to update the project fee: ");
				projID = userInput.next();
				userInput.nextLine();
				
				/* ask user to enter the updated paid project fee */
				System.out.println("Please enter the updated amount paid to date: ");
				float updatedFee = userInput.nextFloat();
				
				/* Execute query to update Proj_Paid_Fee if Proj_ID matches
				 * user input and project is incomplete */
				sqlQuery= "UPDATE Project SET Proj_Paid_Fee = " + updatedFee
						+ " WHERE Proj_ID =" + projID + " AND Proj_Completion = 'No'";
				
				/* count number of records updated */
				affectedRows = statement.executeUpdate(sqlQuery);
				
				/* Display customized error message if project is finalized 
				 * Else, call the viewInvoice() method to display invoice to customer
				 * and display all projects */
				if(affectedRows == 0) {
					System.out.println("The paid fee of the requested project cannot be updated "
							+ "as this project has already been finalized.");
				} else {
					System.out.println(queryComplete + affectedRows + " records updated."
							+ "\nYour project paid fee has succesffully been updated.");
					FinalizeProjects.viewInvoice(statement);
					PrintAll.displayAllProjects(statement);
				}	
				break;
				
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				return;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
	    	} 
		}
	}
	
	/** This method allows users to update the contact details
	 *  of contractors from the PoisePMS db
	 * 		
	 *  @param statement */
	static void updateContractorDetails(Statement statement) {
		while (true) {
			try {
				/* Prompt user to enter valid ID for contractor */
				System.out.println("Please enter the ID of the contractor whose contact details you wish to update: ");
				String contID = userInput.next();
				userInput.nextLine();
				
				/* user is prompted to enter an updated phone number for the contractor */
				System.out.println("Please enter the contractor's updated contact number: ");
				String updatedContNum = userInput.next();
				userInput.nextLine();
				
				/* user is prompted to enter an updated email address for the contractor */
				System.out.println("Please enter the contractor's updated e-mail address: ");
				String updatedContEmail = userInput.next();
				userInput.nextLine();
				
				/* Execute query to update contractor details in Contractor table and join 
				 * Project table to ensure contractor details are only updated if project is still
				 * incomplete */
				sqlQuery= "UPDATE Contractor INNER JOIN Project ON "
						+ "Contractor.cont_id = Project.cont_id SET Cont_Phone_Num = '" 
						+ updatedContNum + "', Cont_Email = '" + updatedContEmail
						+ "' WHERE Contractor.cont_id  = '" + contID + "' AND Project.proj_completion ="
						+ " 'No' ";
				
				/* count number of records updated */
				affectedRows = statement.executeUpdate(sqlQuery);
				
				/* Display customized error message if project is finalized 
				 * Else, call the viewInvoice() method to display invoice to customer
				 * and display information about all existing contractors on database */
				if(affectedRows == 0) {
					System.out.println("The requested contractor's contact details could not be "
							+ "updated as this project \nmay have already been finalized or the "
							+ "contractor ID does not exist on the PoisePMS database.");
				} else {
					System.out.println(queryComplete + affectedRows + " records updated."
							+ "\nThe requested contractor's contact details has succesffully been updated.");
					PrintAll.displayAllContractors(statement);
				}	
				break;
				
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