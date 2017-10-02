package lu.lllc;

public class DBInfo {
	static String host = "localhost"; // In most cases you will use
													// localhost
	static String DBname = "lllc";
	static int port = 3306; // This is for MySQL
	static String mySQLdbURL = "jdbc:mysql://" + host + ":" + port + "/"
			+ DBname;

	static String user = "root";
	static String password = "root";
	
	static String driver = "com.mysql.jdbc.Driver";

	public DBInfo() {

	}

	public static String getDBURL() {
		return mySQLdbURL;
	}

	public static String getUser() {
		return user;
	}

	public static String getPassword() {
		return password;
	}

	public static String getDriver() {
		return driver;
	}

}
