package org.AtmInterface.main.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.AtmInterface.main.exception.InvalidAmountException;

public class AtmInterfaceOperations {
	public static boolean login(String accname,int password) throws SQLException,ClassNotFoundException
	{
		try
		{
		Connection connection= MySqlConn.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select * from atminterfaceoperation where accname=?");
		statement.setString(1, accname);
		ResultSet result=statement.executeQuery();
		if(result.next())
		{
			if(result.getInt("atm_pass")==(password))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	 catch(SQLException e)
     {
         System.out.println("Username is incorrect!! "); 
     }
     return false;
	}
	
	
	
	
	
	
	
	public static double balancecheck(int atm_pin) throws SQLException,ClassNotFoundException
	
	{
		int balance=0;
        try
        {
        	Connection connection= MySqlConn.dbconnect();
        	PreparedStatement statement=connection.prepareStatement("select * from atminterfaceoperation where atm_pin=?");
        	statement.setInt(1, atm_pin);
	
        	ResultSet result=statement.executeQuery();
        	result.next();
        	 balance=result.getInt("accbalance");
        }
        catch(SQLException e)
        {
            System.out.println("Incorrect Pin or Password!!");
        }
        catch(Exception e)
        {
            System.out.println("Something went wrong!!");
        }
        	return  balance;
        }
	
	
	
	
	
	public static double withdraw(int atm_pin,double withdrawalAmount) throws SQLException,ClassNotFoundException, Exception
	{
		Connection connection= MySqlConn.dbconnect();
		PreparedStatement statement=connection.prepareStatement("select accbalance from atminterfaceoperation where atm_pin=?");
    	statement.setInt(1, atm_pin);
    	
    	ResultSet result=statement.executeQuery();
    	result.next();
    	double availableBalance=result.getDouble("accbalance");
    	
    	if(withdrawalAmount<availableBalance) 
    	{
    		double remainingBalance=availableBalance-withdrawalAmount;
    		statement=connection.prepareStatement("update atminterfaceoperation set accbalance=? where atm_pin=?");
            statement.setDouble(1, new Double(remainingBalance));
            statement.setLong(2, atm_pin);
    		
            if(statement.executeUpdate()>0)
            {
                return remainingBalance;  
            }
            else
            {
                return 0;
            }
         }
         else
         {
             throw new InvalidAmountException("Invalid Withdrawal amount!!");
         }
     }
	public static double deposit(int atm_pin, double depositAmount) throws ClassNotFoundException, SQLException
    {
        Connection connection= MySqlConn.dbconnect();
        PreparedStatement statement=connection.prepareStatement("select * from atminterfaceoperation where atm_pin=?");
        statement.setLong(1,atm_pin);
        
        ResultSet result=statement.executeQuery();
        result.next();
        double availableBalance=result.getDouble("accbalance");
        double newBalance=availableBalance+depositAmount;
        
          statement=connection.prepareStatement("update atminterfaceoperation set accbalance=? where atm_pin=?");
           statement.setDouble(1, new Double(newBalance));
           statement.setInt(2, atm_pin);
           
           if(statement.executeUpdate()>0)
           {
               return newBalance;  
           }
           else
           {
               return 0;
           }
    }
           public static  ResultSet checkAccountInfo(int atm_pin) throws ClassNotFoundException, SQLException
           {
               Connection connection= MySqlConn.dbconnect();
               PreparedStatement statement=connection.prepareStatement("select * from atminterfaceoperation where atm_pin=?");
               statement.setInt(1,atm_pin);
               
               ResultSet result=statement.executeQuery();
               if(result.next())
               {
                   return result;
               }
               else
               {
                   return null;
               }
    }	
}
           
    		
 
	