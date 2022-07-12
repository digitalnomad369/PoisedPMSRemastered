/** The PrintAll class contains code to display all details about projects 
  * and people(e.g. customers, project managers) involved in projects
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 02-07-2022  */
 
import java.sql.*;

public class PrintAll extends PoisedPMS {
	
	/** Method that reads existing projects from the PoisePMS db
	  * @param statement
	  * @throws SQLException */
	static void displayAllProjects(Statement statement) throws SQLException {
		/* executeQuery: runs a SELECT statement and returns the results. */
		res = statement.executeQuery("SELECT * FROM Project");
		
		/* Iterate over the results and display all existing projects in database */
		System.out.println("EXISTING PROJECT DETAILS:");
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
	}
	
	/** Method that reads and displays information about all structural engineers
	  * from the PoisePMS db
	  * @param statement
	  * @throws SQLException */
	static void displayAllEngineers(Statement statement) throws Exception {
		/* Execute a SELECT statement and returns the results. */
		res = statement.executeQuery("SELECT * FROM Structural_Engineer");
		
		/* Loop over returned results and display structural engineers
		 * from the database  */
		System.out.println("\nStructural Engineers: ");
		while (res.next()) {
			System.out.println(STR1 + res.getString("Struct_Eng_ID")
				+ STR2 + res.getString("SE_Name") + STR3 + res.getString("SE_Phone_Num") 
				+ STR4 + res.getString("SE_Email") + STR5 + res.getString("SE_Address"));
		}
		res.close();
	}
	
	/** Method that reads and displays information about all project managers
	  * from the PoisePMS db
	  * @param statement
	  * @throws SQLException */
	static void displayAllManagers (Statement statement) throws Exception {
		/* executeQuery: runs a SELECT statement and returns the results. */
		res = statement.executeQuery("SELECT * FROM Project_Manager");
		System.out.println("\nProject Managers: ");
		while (res.next()) {
			System.out.println(STR1 + res.getString("Proj_Man_ID") 
				+ STR2 + res.getString("PM_Name") + STR3 + res.getString("PM_Phone_Num") 
				+ STR4 + res.getString("PM_Email") + STR5 + res.getString("PM_Address"));
		}
		res.close();
	}
	
	/** Method that reads and displays information about all architects from the database
	  * @param statement
	  * @throws Exception */
	static void displayAllArchitects(Statement statement) throws Exception {
		/* Execute a SELECT query and returns the results. */
		res = statement.executeQuery("SELECT * FROM Architect");
		
		/* Iterate over the results and display all architects contained in database */
		System.out.println("\nArchitects: ");
		while (res.next()) {
			System.out.println(STR1 + res.getString("Arch_ID") 
				+ STR2 + res.getString("Arch_Name") + STR3 + res.getString("Arch_Phone_Num") 
				+ STR4 + res.getString("Arch_Email") + STR5 + res.getString("Arch_Address"));
		}
		res.close();
	}
	
	/** Method that reads and displays information about all customers from the database
	  * @param statement
	  * @throws Exception */
	static void displayAllCustomers (Statement statement) throws Exception {
		/* executeQuery: runs a SELECT statement and returns the results. */
		res = statement.executeQuery("SELECT * FROM Customer");
		
		/* Loop over returned results and display customers from the PoisePMS db  */
		System.out.println("\nCustomers: ");
		while (res.next()) {
			System.out.println(STR1 + res.getString("Cust_ID") + STR2 + res.getString("Cust_Name")
				+ STR3 + res.getString("Cust_Phone_Num") + STR4 + res.getString("Cust_Email") 
				+ STR5 + res.getString("Cust_Address"));
		}
		res.close();
	}
	
	/** Method that reads and displays information about all contractors from the database
	  * @param statement
	  * @throws Exception */
	static void displayAllContractors (Statement statement) throws Exception {
		/* Execute a SELECT statement and returns the results. */
		res = statement.executeQuery("SELECT * FROM Contractor");
		
		/* Iterate over the results and display all contractors contained in database */
		System.out.println("\nContractors: ");
		while (res.next()) {
			System.out.println(STR1 + res.getString("Cont_ID") + STR2 + res.getString("Cont_Name")
				+ STR3 + res.getString("Cont_Phone_Num") + STR4 + res.getString("Cont_Email") 
				+ STR5 + res.getString("Cont_Address"));
		}
		res.close();
	}
}