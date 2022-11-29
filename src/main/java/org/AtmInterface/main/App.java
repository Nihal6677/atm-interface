package org.AtmInterface.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.ResultSet;
import org.AtmInterface.main.dao.AtmInterfaceOperations;
import org.AtmInterface.main.exception.InvalidAmountException;

public class App 
{
	static BufferedReader bufferedReader =new BufferedReader(new InputStreamReader(System.in)); 

    public static void main( String[] args ) throws Exception
    {
    	System.out.println("***********************************************************************************");
		System.out.println("*************************** WELCOME TO EDUBRIDGE BANK*******************************");
		System.out.println("***********************************************************************************");
		
		boolean status=false;
		do
		{
		
			System.out.println("***********************************************************************************");
			System.out.print("\t\t ENTER THE Name : ");
			String accname=bufferedReader.readLine();
			System.out.println();
			System.out.print("\t\t  ENTER THE PASSWORD : ");
			int password=Integer.parseInt(bufferedReader.readLine());
			System.out.println("***********************************************************************************");
			status=AtmInterfaceOperations.login(accname,password);
		

			if(status)
			{
			do
			{
				System.out.println("***********************************************************************************");
				System.out.println("**********************************WELCOME TO ATM INTERFACE*********************************************");
				System.out.println("***********************************************************************************");
				System.out.println("\t\t  Enter 1-> Check Available Balance");
				System.out.println("\t\t  Enter 2-> Withdraw Cash");
				System.out.println("\t\t  Enter 3-> Deposit Cash");
				System.out.println("\t\t  Enter 4-> Account Detail");
				System.out.println("\t\t  Enter 5-> Logout");
				System.out.println("====================================================================================");
				System.out.println("\t\t  Enter a valid input between 1 to 5:");
				int choice=Integer.parseInt(bufferedReader.readLine());
				
				switch(choice)
				{
				case 1: System.out.println( "Enter The ATM Pin");
						int atm_pin=Integer.parseInt(bufferedReader.readLine());
						System.out.println("Current available balance is :"+AtmInterfaceOperations.balancecheck(atm_pin));
						break;
						
				case 2: System.out.println("Enter The ATM Pin");
						atm_pin=Integer.parseInt(bufferedReader.readLine());
						System.out.println("Enter withdraw amount:");
						double withdrawalAmount=Double.parseDouble(bufferedReader.readLine());		
						
						try
						{
						double result=AtmInterfaceOperations.withdraw(atm_pin,withdrawalAmount);                      
						System.out.println("Transaction successfull!!");
						System.out.println("Remaining balance is:"+result);
						}
						catch(InvalidAmountException e)
						{
							System.out.println("Invalid Withdrawal Amount");
						}
						break;
                           
				 case 3: System.out.println("Enter valid ATM Pin:");
                 atm_pin=Integer.parseInt(bufferedReader.readLine());
                 System.out.println("Enter deposit amount:");
                 double depositAmount=Double.parseDouble(bufferedReader.readLine());
                 double result=AtmInterfaceOperations.deposit(atm_pin, depositAmount);
                 
                 if(result==0)
                 {
                     
                     System.out.println("Transaction is unsuccessfull!!");
                 }
                 else
                 {
                     System.out.println("Transaction successfull!!");
                     System.out.println("New balance is:"+result);
                 }
                 break; 
                 
                 
				  case 4: System.out.println("Enter valid ATM Pin:");
				  atm_pin=Integer.parseInt(bufferedReader.readLine());
                  System.out.println("*******************************************************************");
                  System.out.println("******************* ACCOUNT DETAIL*********************************");

                  System.out.println("*******************************************************************");

                  ResultSet accountInfo=AtmInterfaceOperations.checkAccountInfo(atm_pin); 
                  System.out.println("\t\t  ATM Pin :"+accountInfo.getInt("atm_pin"));
                  System.out.println("\t\t  Name :"+accountInfo.getString("accname"));
                  System.out.println("\t\t  Balance :"+accountInfo.getString("accbalance"));
                  System.out.println("\t\t ATM Password :"+accountInfo.getInt("atm_pass"));

                  System.out.println("*********************************************************************");
                  break;  
                 
                 
				  case 5: status=false;
                  System.out.println("************************************************************************************");
                  System.out.println("You have successfully logged out!!");
                  System.out.println("Bye");
                  System.out.println("************************************************************************************");
                  break;
                  
          default: System.out.println("************************************************************************************");
                   System.out.println("Wrong Choice!!");      
                   System.out.println("************************************************************************************");
                   
          
          }
                 
                 
                 
                 
				}
			while(status);	
			}
			else
			{
				System.out.println("Incorrect Pin or Password");
			}
  
		}
		while(status);
	}
}