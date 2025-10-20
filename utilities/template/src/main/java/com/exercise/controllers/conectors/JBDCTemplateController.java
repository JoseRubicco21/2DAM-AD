package com.exercise.controllers.conectors;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetProvider;

//Wimport org.apache.commons.dbcp2.BasicDataSource;


public abstract class JBDCTemplateController {

	protected String databaseName;
	
	public abstract void init();
	
	/* ==== DIRECT CONNECTIONS ==== */
	/**
	 * A Simple class that templates the connection to Databases.
	 * @param URL The url of the database to connect to. 
	 * @param username The username of the user to connect to the database as.
	 * @param password The password of such user.
	 * @return Connection connection. A connection object that's passed to makeStatement and makePrepared Statements.
	 * @throws SQLException An SQL exception if the SQL query is badly written or contains errors.
	 */
	
	public Connection openConnection(String URL, String username, String password) throws SQLException {
		try(Connection connection = DriverManager.getConnection(URL, username, password);){
			System.out.println("Conexión correcta");
			return connection;	
		}		
	};
	
	/**
	 * 
	 * @param URL The url of the database to connect to. 
	 * @param props The properties of the connection if wanted to use a properties object.
	 * @return Connection connection. A connection object that's passed to makeStatement and makePrepared Statements.
	 * @throws SQLException An SQL exception if the SQL query is badly written or contains errors.
	 */
	public Connection openConnection(String URL, Properties props) throws SQLException {
		try(Connection connection = DriverManager.getConnection(URL, props);){
			System.out.println("Conexión correcta");
			return connection;	
		}		
	};
	
	/**
	 * 
	 * @param URL URL The url of the database to connect to. 
	 * @return Connection connection. A connection object that's passed to makeStatement and makePrepared Statements.
	 * @throws SQLException An SQL exception if the SQL query is badly written or contains errors.
	 */
	public Connection openConnection(String URL) throws SQLException {
		try(Connection connection = DriverManager.getConnection(URL);){
			System.out.println("Conexión correcta");
			return connection;	
		}		
	};
	
	/* ==== DIRECT CONNECTIONS ==== */
	
	
	/* ==== POOL CONNECTIONS ==== */
	/* Use this method to create a fully parameterized connection Pool.*/
	//public abstract Connection openParameterizedPoolConnection() throws SQLException;
	
	/*public Connection openPoolConnection(String URL, String username, String password, int initialPoolSize, int maxPoolSize) throws SQLException {
		try(BasicDataSource dataSource = new BasicDataSource();){
			dataSource.setUrl(URL);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
			dataSource.setInitialSize(initialPoolSize);
			dataSource.setMaxTotal(maxPoolSize);
			Connection connection = dataSource.getConnection();
			
			return connection;
		}
	}*/
	
	/*
	public Connection modifyConnectionPool(Connection connection) throws SQLException {
		try(BasicDataSource dataSource = new BasicDataSource();){
		
		}
		return connection;
	}
	*/
	/* ==== POOL CONNECTIONS ==== */
	
	/* ==== STATEMENTS & PREPARED STATEMENTS ==== */
	public Statement makeStatement(Connection connection, String SQLStatement) throws SQLException {
		try(Statement stt = connection.createStatement()){
			return stt;
		}
	};

	public PreparedStatement makePreparedStatement(Connection connection, String SQLStatement) throws SQLException{
		try(PreparedStatement pstt = connection.prepareStatement(SQLStatement)){
			return pstt;
		}
	};
	
	// Configure statement should
	//public abstract void configureStatement(PreparedStatement preparedStatement) throws SQLException;

	/* ==== ROW SETS ==== */
	
	public JdbcRowSet configureRowSet(String URL, String username, String password) throws SQLException {
		try(JdbcRowSet rowSet = RowSetProvider.newFactory().createJdbcRowSet()){
			rowSet.setUrl(URL);
			rowSet.setUsername(username);
			rowSet.setPassword(password);
			
			return rowSet;
		}
	}
	
	public JdbcRowSet setSQLStatementOfRowSet(JdbcRowSet rowSet, String SQLStatement) throws SQLException {
		rowSet.setCommand(SQLStatement);
		return rowSet;
	}
	
	public JdbcRowSet executeSQLStatementOfRowSet(JdbcRowSet rowSet) throws SQLException {
		rowSet.execute();
		return rowSet;
	};

	public JdbcRowSet makeRowSet(String URL, String username, String password, String SQLStatement) throws SQLException {
		JdbcRowSet jrs = this.configureRowSet(URL, username, password);
		jrs = this.setSQLStatementOfRowSet(jrs, SQLStatement);
		jrs = this.executeSQLStatementOfRowSet(jrs);
		return jrs;
	}
	
}

