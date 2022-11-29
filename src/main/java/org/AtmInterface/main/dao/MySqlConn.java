package org.AtmInterface.main.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConn {
	static final String DB_URL="jdbc:mysql://localhost:3306/";
	static final String DB_USER="root";
	static final String DB_PASSWORD="root";
	static final String DB_NAME="AtmInterfacedb";
	
	public static Connection dbconnect()
	{
		Connection connection=null;
		
		try
		{
			connection = DriverManager.getConnection(DB_URL+DB_NAME, DB_USER, DB_PASSWORD);
			if(connection!=null)
			{
				System.out.println("Connection successfull");
			}
			else
			{
				System.out.println("Not Connected");
			}
		}
		catch (SQLException e)
		{
			System.out.println("Connection Error");
		}
		return connection;
		
	}


}
