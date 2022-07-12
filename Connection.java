/** The Connection class is used to establish a connection to the target database. 
 * <p>
  * @author Tammy-Lee Bastian
  * @version 1.0
  * @since 01-07-2022  */
  
import java.sql.*;

public class Connection {
	
	/* String values for url, username and password.
	 *  java.sql.Connection value for con.*/
	private static String url = "jdbc:mysql://localhost:3306/PoisePMS";
    private static String username = "username";
    private static String password = "password";
	private static java.sql.Connection con;

	/** The getConnection() method is used to access connection outside this class
	 * @throws SQLException
	 * @return returns con
	 */
    public static java.sql.Connection getConnection() throws SQLException {
    	con = DriverManager.getConnection(url, username, password);
    	return con;
    }
    
    /** The getStatement() method is used to access statement outside this class
	 * @throws Exception
	 * @return returns statement
	 */
	public static Statement getStatement() throws Exception {
		Statement statement = con.createStatement();
		return statement;
	}
}