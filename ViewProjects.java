/** This class enables users to view projects based on completion status 
  *  or unique project IDs
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 02-07-2022 */

import java.sql.*;
import java.text.SimpleDateFormat;

public class ViewProjects extends PoisedPMS {
	
	/** The viewAProject() method allows users to view a project by entering its unique
	 * ID 
	 * 		
	 *  @param statement */
	static void viewAProject(Statement statement) {
		while(true) {
			
			/* Add a try and catch blocks to handle potential Exceptions */
			try {
				
				/* Prompt user to enter valid Project ID */
				System.out.println("Please enter the ID of the project that you wish to view: ");
				String projView = userInput.next();
				userInput.nextLine();
				
				/* Runs a SELECT statement and returns the results of selected project. */
				res = statement.executeQuery("SELECT * FROM Project WHERE Proj_ID = " + projView);
				
				while (res.next()) {
					System.out.println("\nProject ID: " + res.getString("Proj_ID") 
						+ "\nProject name: " + res.getString("Proj_Name") 
						+ "\nProject type: " + res.getString("Proj_Type") 
						+ "\nAddress: " + res.getString("Proj_Address") 
						+ "\nERF number: " + res.getString("Proj_ERF_Num") 
						+ "\nTotal fee: " + res.getFloat("Proj_Fee") 
						+ "\nPaid fee: " + res.getFloat("Proj_Paid_Fee") 
						+ "\nProject deadline: " + res.getDate("Proj_Deadline") 
						+ "\nProject status: " + res.getString("Proj_Completion") 
						+ "\nProject completion date: " + res.getString("Proj_Completion_Date") 
						+ "\nStructural Engineer ID: " + res.getString("Struct_Eng_ID") 
						+ "\nProject Manager ID: " + res.getString("Proj_Man_ID") 
						+ "\nArchitect ID: " + res.getString("Arch_ID") 
						+ "\nCustomer ID: " + res.getString("Cust_ID")
						+ "\nContractor ID: " + res.getString("Cont_ID"));
				}
				res.close();
				break;
				
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				break;
			} catch (Exception e) {
	    		System.out.println(FORMAT_ERROR);
	    		userInput.nextLine();
			}
		}
	}
	
	/** This method allows users to view all completed projects from the database
	 * 		
	 *  @param statement */
	static void viewCompletedProjects(Statement statement) {
		while(true) {
			
			/* Add try and catch block to handle a possible SQLException 
			 * while executing query */
			try {
				/* Run a SELECT query on all projects where Proj_Completion = "Finalized" */
				res = statement.executeQuery("SELECT * FROM Project WHERE Proj_Completion = 'Finalized'");
				
				/* Display all completed projects from the db */
				System.out.println("COMPLETED PROJECTS: ");
				while (res.next()) {
					System.out.println("\nProject ID: " + res.getString("Proj_ID") 
						+ "\nProject name: " + res.getString("Proj_Name") 
						+ "\nProject type: " + res.getString("Proj_Type") 
						+ "\nAddress: " + res.getString("Proj_Address") 
						+ "\nERF number: " + res.getString("Proj_ERF_Num") 
						+ "\nTotal fee: " + res.getFloat("Proj_Fee") 
						+ "\nPaid fee: " + res.getFloat("Proj_Paid_Fee") 
						+ "\nProject deadline: " + res.getDate("Proj_Deadline") 
						+ "\nProject status: " + res.getString("Proj_Completion") 
						+ "\nProject completion date: " + res.getString("Proj_Completion_Date") 
						+ "\nStructural Engineer ID: " + res.getString("Struct_Eng_ID") 
						+ "\nProject Manager ID: " + res.getString("Proj_Man_ID") 
						+ "\nArchitect ID: " + res.getString("Arch_ID") 
						+ "\nCustomer ID: " + res.getString("Cust_ID") 
						+ "\nContractor ID: " + res.getString("Cont_ID"));
				}
				res.close();
				break;	
				
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				break;
			}
		}
	}
	
	/** This method allows users to view all incomplete projects from the database
	 * 		
	 *  @param statement */
	static void viewIncompleteProjects(Statement statement) {
		while(true) {
			try {
				/* Run a SELECT query on all projects where Proj_Completion = "No" */
				res = statement.executeQuery("SELECT * FROM Project WHERE Proj_Completion = 'No'");
				
				/* Display all incomplete projects from the db */
				System.out.println("INCOMPLETE PROJECTS: ");
				while (res.next()) {
					System.out.println("\nProject ID: " + res.getString("Proj_ID") 
						+ "\nProject name: " + res.getString("Proj_Name") 
						+ "\nProject type: " + res.getString("Proj_Type") 
						+ "\nAddress: " + res.getString("Proj_Address") 
						+ "\nERF number: " + res.getString("Proj_ERF_Num") 
						+ "\nTotal fee: " + res.getFloat("Proj_Fee") 
						+ "\nPaid fee: " + res.getFloat("Proj_Paid_Fee") 
						+ "\nProject deadline: " + res.getDate("Proj_Deadline") 
						+ "\nProject status: " + res.getString("Proj_Completion") 
						+ "\nProject completion date: " + res.getString("Proj_Completion_Date") 
						+ "\nStructural Engineer ID: " + res.getString("Struct_Eng_ID") 
						+ "\nProject Manager ID: " + res.getString("Proj_Man_ID") 
						+ "\nArchitect ID: " + res.getString("Arch_ID") 
						+ "\nCustomer ID: "+ res.getString("Cust_ID") 
						+ "\nContractor ID: " + res.getString("Cont_ID"));
				}
				res.close();
				break;	
				
			/* Display appropriate error message to handle SQLExceptions */
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				break;
			}	
		}
	}
	
	/** This method allows users to view all overdue projects from the database
	 * 	<p>
	 * Overdue projects are projects that are incomplete and past its expected deadline	
	 *  @param statement */
	static void viewOverdueProjects(Statement statement) {
		
		/* Convert present date to String format */
		SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
		String stringDate = date.format(today);
		
		while(true) {
			try {
				/* Run a SELECT query on all projects where Proj_Completion = "No" and Proj_Deadline 
				 * is less than the present date */
				res = statement.executeQuery("SELECT * FROM Project WHERE Proj_Completion = 'No' AND "
						+ "Proj_Deadline < '" + stringDate + "'");
				
				/* Display all overdue projects from the db */
				System.out.println("OVERDUE PROJECTS: ");
				while (res.next()) {
					System.out.println("\nProject ID: " + res.getString("Proj_ID")
						+ "\nProject name: " + res.getString("Proj_Name") 
						+ "\nProject type: " + res.getString("Proj_Type") 
						+ "\nAddress: " + res.getString("Proj_Address") 
						+ "\nERF number: " + res.getString("Proj_ERF_Num") 
						+ "\nTotal fee: " + res.getFloat("Proj_Fee") 
						+ "\nPaid fee: " + res.getFloat("Proj_Paid_Fee") 
						+ "\nProject deadline: " + res.getDate("Proj_Deadline") 
						+ "\nProject status: " + res.getString("Proj_Completion") 
						+ "\nProject completion date: " + res.getString("Proj_Completion_Date") 
						+ "\nStructural Engineer ID: " + res.getString("Struct_Eng_ID") 
						+ "\nProject Manager ID: " + res.getString("Proj_Man_ID") 
						+ "\nArchitect ID: " + res.getString("Arch_ID") 
						+ "\nCustomer ID: " + res.getString("Cust_ID") 
						+ "\nContractor ID: " + res.getString("Cont_ID"));
				}
				res.close();
				break;	
				
			/* Catch and handle SQLExceptions */
			} catch (SQLException e) {
				System.out.println(SQL_ERROR_MESSAGE);
				break;
			}
		}
	}				
}