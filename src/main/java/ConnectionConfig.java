public class ConnectionConfig {

	private final String username;
	private final String password;
	private final int port;
	private final String database;
	private final String host;

	public ConnectionConfig(String username, String password, String host, int port, String database) {
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
		this.database = database;
	}

	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public String getHost() {
		return host;
	}
	public int getPort() {
		return port;
	}
	public String getDatabase() {
		return database;
	}



}
