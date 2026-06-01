import entities.Actor;
import entities.Movie;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

public class ImdbSearch {

	private static String  keyword;

	public static void main(String[] args) throws SQLException {
		try (Connection connection = openConnection()) {

			String output = "";
			output += selectFirstMovies(connection, 10);
			output += "\n";
			System.out.println(output);

		} catch (IOException e) {
			System.err.println("Could not load properties file.");
			System.exit(1);
		} catch (SQLException e) {
			System.err.println("Error message: " + e.getMessage());
			System.err.println("SQLSTATE: " + e.getSQLState());
			System.err.println("Error code: " + e.getErrorCode());
			System.exit(1);
		}
	}

	protected static ConnectionConfig getConnectionConfig() throws IOException {
		Properties props = new Properties();

		//load a properties file from class path, inside static method
		props.load(ImdbSearch.class.getClassLoader().getResourceAsStream("database.properties"));

		String host = props.getProperty("host");
		String port = props.getProperty("port");
		String database = props.getProperty("database");
		String username = props.getProperty("username");
		String password = props.getProperty("password");

		return new ConnectionConfig(username, password, host, Integer.valueOf(port), database);

	}

	protected static Connection openConnection() throws SQLException, IOException {

		ConnectionConfig cc = getConnectionConfig();
		return DriverManager.getConnection("jdbc:postgresql://" + cc.getHost() + ":"
				+ cc.getPort() +"/" + cc.getDatabase(), cc.getUsername(), cc.getPassword());
	}

	private static String selectFirstMovies(Connection connection, int limit)
			throws SQLException {
		String output = "First " + limit + " MOVIES\n";
		// execute statement for movies with keyword in title
		ResultSet movies = connection.createStatement().executeQuery(
				"SELECT * FROM tmovies ORDER BY startyear ASC LIMIT " + limit);

		while (movies.next()) {
			// build output string
			output += movies.getString("tconst") + ", "
					+ movies.getString("primarytitle") + ", "
					+ movies.getString("isadult") + ", "
					+ movies.getString("startyear") + ", "
					+ movies.getString("runtimeminutes") + ", "
					+ movies.getString("genres") + "\n";
		}
		return output;
	}

	protected static List<Movie> findMovies(Connection connection, String keyword)
			throws SQLException {
		throw new UnsupportedOperationException("Not yet implemented");
	}

	protected static List<Actor> findActors(Connection connection, String keyword)
			throws SQLException {
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
