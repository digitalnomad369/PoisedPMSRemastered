/** This code enables users to finalize projects or displays an invoice 
  * to the customer if there is an outstanding project fee still payable 
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 01-07-2022 */

import java.sql.*;
import java.text.SimpleDateFormat;

public class FinalizeProjects extends PoisedPMS {

	/** Method that finalizes existing projects 
	 * 		
	 *  @param statement */
	static void finalizeProj(Statement statement)  {
		
		/* add try and catch blocks to catch Exceptions */
		while (true) {
			try {
				/* Prompt user to enter a valid project ID */
				System.out.println("Please enter the ID of the project that you would like to finalize: ");
				projID = userInput.next();
				userInput.nextLine();
				
				/* Parse current date into valid date format */
				SimpleDateFormat dateFor = new SimpleDateFormat("yyyy-MM-dd");
				String completionDate = dateFor.format(today);
				
				/* Execute Query to set Proj_Completion status to "Finalized" and set 
				 * Proj_Completion_Date to current date where Proj_ID matches given projID
				 * and Proj_Paid_Fee equals Proj_Fee */
				sqlQuery = "UPDATE Project SET Proj_Completion = " + "'Finalized', Proj_Completion_Date = '"
						+ completionDate + "' WHERE Proj_ID = " + projID 
						+ " AND Proj_Paid_Fee = Proj_Fee AND Proj_Completion = 'No'";
				
				/* count and display number of records updated */
				affectedRows = statement.executeUpdate(sqlQuery);
				System.out.println(queryComplete + affectedRows + " record/s updated.");
				
				/* If no records are updated, an appropriate message is displayed.
				 * The viewInvoice() method is called to display the customer and invoice details */
				if(affectedRows == 0) {
					System.out.println("Query is unsuccessful. "
							+ "\nYour requested project could not be finalized as you either have an "
							+ "outstanding balance payable "
							+ "\non the project fee or this project has already been finalized.");	
					viewInvoice(statement);	
				
				/* Else, no invoice is required and project is finalized
				 * call displayAllProjects() method to display all projects
				 * incl. updated finalized project from the db */
				} else {
					System.out.println("\nNo invoice is required.\nYour requested project has been succesfully finalized.");
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
	
	/** Method that prints customer and invoice details
	 * 	
	 *  @param statement */
	static void viewInvoice(Statement statement) {
		while (true) {
			try {
				/* executeQuery: runs a SELECT statement on Customer and selects all rows from
				 * the Customer table where the the customer id corresponds with that in the Project
				 * table */
				String sqlQuery2 = "SELECT * FROM Customer INNER JOIN Project ON Customer.cust_id = Project.cust_id"
						+ " WHERE Project.proj_id = " + projID;
 
				res = statement.executeQuery(sqlQuery2);
				
				/* Display relevant customer information */
				System.out.println("\nINVOICE " 
						+ "\nCustomer details:\n");
				while(res.next()) {
					System.out.println("ID: " + res.getString("Cust_ID") + "\nName: "
							+ res.getString("Cust_Name") + "\nContact number: " 
							+ res.getString("Cust_Phone_Num") + "\nE-mail address: "
							+ res.getString("Cust_Email") + "\nResidential address: "
							+ res.getString("Cust_Address"));
				}
				
				/* Executes a SELECT statement on Proj_Fee and Proj_Paid_Fee to calculate
				 * the difference between the two renamed as Fee_Difference */
				String difference = "SELECT Proj_Fee, Proj_Paid_Fee, Proj_Fee - Proj_Paid_Fee"
						+ " AS Fee_Difference FROM Project WHERE Proj_ID = " + projID;
				res = statement.executeQuery(difference);
				
				/* Display payable project fee to customer */
				System.out.println("\nFee difference: ");
				while(res.next()) {
					System.out.println(res.getString("Fee_Difference"));
				}	
				res.close();
				break;
	
			} catch (SQLException e) {
				e.printStackTrace();
				break;
			}
		}
	}
}