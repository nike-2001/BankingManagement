package bankingsystem;

import java.util.HashMap;
import java.util.Scanner;
import java.io.Console;  

class InvalidUser extends Exception {

	public InvalidUser(String name) {
		super(name);
	}

}

class InvalidOptionSelected extends Exception
{
	public InvalidOptionSelected(String name) {
		super(name);
	}
}

class Login {
	static int countAttempts = 0;
	static Scanner sc = new Scanner(System.in);

	
	public static void options() {
		String optionForContinue ;
		String option;
		do {
			System.out.println("---------------------------------------------------------------------------------------------------");
			System.out.println(" 1. Add Customer 2. Transactions 3. Customer Details  4. Delete Customer  5. All Customer Details  6. exit");
			
			option = sc.next();
			
			if(option.equals("1")  ||  option.equals("2")||  option.equals("3")||  option.equals("4")||  option.equals("5") || option.equals("6"))
			{
				switch (option) {

				case "1" : BankCustomer.createCustomer();
						break;
					
				case "2" : Transaction t = new Transaction();
						t.transaction();
						break;
					
				case "3" : CustomerDetails.customerDetails();
						break;
				case "4" : BankCustomer.deleteCustomer();
						break;
				case "5" : CustomerDetails.customers();
						break;
				case "6" : System.out.println("Thank you!! Have a great day :)");
							System.exit(0);
				default:System.out.println("Invalid Choice...");

				}
			}
			else
			{
				try
				{
					throw new InvalidOptionSelected("Invalid option selected...!");
				}catch(InvalidOptionSelected e)
				{
					System.out.println(e.getMessage());
				}
				
			}
			System.out.println("\nDo you want to continue ? 1/0 ");
			optionForContinue = sc.next();
			if (!optionForContinue.equals("1") && !optionForContinue.equals("0")) {
				System.out.println("Invalid Choice...");
				System.exit(0);
			}
			
		} while (optionForContinue.equals("1"));
	}

	public static void validate(String userId, String password) {
		Scanner sc = new Scanner(System.in);
		if (Main.validUsers.containsKey(userId) && Main.validUsers.get(userId).equals(password)) {
			System.out.println("Login Successful...");
			options();
		} else {
			try {
				throw new InvalidUser("Invalid Login...");
			} catch (InvalidUser invaliduser) {
				countAttempts++;
				if (countAttempts == 5) {
					System.out.println("Sorry, You have reached maximum number of attempts");
					System.exit(0);
				}
				System.out.println(invaliduser.getMessage());
				System.out.println("Do you want to continue ? 1/0");
				int option = sc.nextInt();
				if (option == 1) {
					String inputUserId, inputPassword;
					System.out.print("Enter userId :");
					inputUserId = sc.next();
					System.out.print("Enter password : ");
					inputPassword = sc.next();
					validate(inputUserId, inputPassword);
				} else {
					System.out.println("Thank you for visiting....");
					System.exit(0);
				}
			}
		}
	}
}

public class Main {

	public static HashMap<String, String> validUsers;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Console con =  System.console(); 
		Main main = new Main();

		validUsers = new HashMap<>();
		validUsers.put("sai", "sai000");
		validUsers.put("12345", "12345");

		String inputUserId;
		System.out.print("Enter userId :");
		inputUserId = sc.next();
		System.out.print("Enter password : ");
		char[] ch=con.readPassword();   //only for cmd use
        	String inputPassword = String.valueOf(ch);   //only for cmd use


		//System.out.print("Enter password : ");//for eclipse and cmd use
		//inputPassword = sc.next();

		Login.validate(inputUserId, inputPassword);

	}
}
