package busreservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	
	public static String url = "jdbc:mysql:///busresv";
	public static String username = "root";
	public static String password = "Tarakpwd77";
	
	public static Connection getDBInstance() throws SQLException {

		Connection connection = DriverManager.getConnection(url,username,password);

		return connection;
	}

	
}
