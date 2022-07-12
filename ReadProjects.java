/** This code calls relevant static methods from the PrintAll class to display 
 *  and read information about existing projects and people involved in projects
  * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 01-07-2022  */
 
import java.sql.*;

public class ReadProjects extends PoisedPMS {
	
	/** Method that calls the displayAllProjects() method from the PrintAll
	 *  class to read existing projects from the PoisePMS db
	 *  @param statement
	 *  @throws SQLException */
	static void readProjects(Statement statement) throws SQLException {
		PrintAll.displayAllProjects(statement);
	}
	
	/** Method that calls relevant methods from the PrintAll class to read 
	 *  and print details about all people involved in existing projects from the PoisePMS db
	 *  @param statement
	 *  @throws SQLException */
	static void readPeople(Statement statement) throws Exception {
		PrintAll.displayAllEngineers(statement);
		PrintAll.displayAllManagers(statement);
		PrintAll.displayAllArchitects(statement);
		PrintAll.displayAllCustomers(statement);
		PrintAll.displayAllContractors(statement);
	}		
}