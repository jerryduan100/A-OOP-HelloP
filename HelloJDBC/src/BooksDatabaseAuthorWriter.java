import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BooksDatabaseAuthorWriter {
	public static void main(String[] args) {
		String connectionString = "jdbc:mysql://localhost/books";
//		String username = "scott";
//		String password = "tiger";
		String username = "yueli";
		String password = "xiaoli";
		Connection connection = null;
		PreparedStatement statement = null;
		ResultSet resultset = null;
		try {
			connection = DriverManager.getConnection(connectionString, username, password);			
			statement = connection.prepareStatement("insert into Authors (FirstName,LastName) values ('Yueli','Zhu')");
			statement.executeUpdate();
		} catch (SQLException e) {
			System.err.println("Problem accessing database");
			System.err.println(e.getMessage());
		} finally { // most important lines in this entire program!!!
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				System.err.println(e.getMessage());
			}
		} // close try-catch
	} // close main

}
