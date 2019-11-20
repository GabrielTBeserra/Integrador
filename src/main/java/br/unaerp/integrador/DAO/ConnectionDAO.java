package br.unaerp.integrador.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDAO {
	private final String URL = "jdbc:mysql://localhost:3306/integrador?useTimezone=true&serverTimezone=UTC";
	private final String driver = "com.mysql.jdbc.Driver";
	private final String user = "root";
	private final String password = "";
	private Connection connection;

	protected void connect() throws ClassNotFoundException, SQLException {
		Class.forName(driver);
		connection = DriverManager.getConnection(URL, user, password);

	}

	protected Connection db() {
		return connection;
	}

	protected void disconnect() throws SQLException {
		connection.close();
	}

}