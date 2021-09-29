package bankingsystem;

import java.util.*;

class AddingCustomerDetails {

	private long acno;
	private String cname, actype;
	private double bal;

	// Setters
	public void setAcno(long account_no) {
		acno = account_no;
	}

	public void setCname(String customer_name) {
		cname = customer_name;
	}

	public void setActype(String account_type) {
		actype = account_type;
	}

	public void setBal(double balance) {
		bal = balance;
	}

	// Getters
	public long getAcno() {
		return acno;
	}

	public String getCname() {
		return cname;
	}

	public String getActype() {
		return actype;
	}

	public double getBal() {
		return bal;
	}

	// Method to call the getter methods
	public String details() {
		String str = "[ Accont Number = " + getAcno() + ", Customer Name = " + getCname() + ", Account Type = "
				+ getActype() + ", Balance Amount = " + getBal()+" ]";
		return str;
	}

}

class BankCustomer {

	public static HashMap<Long, AddingCustomerDetails> itms = new HashMap<>();// defining hashmap to store customer details

	static void createCustomer() {// Defining createCustomer method that we are calling from Main.java

		Scanner s = new Scanner(System.in);
		System.out.println("Enter the Following Details\n");

		long acno;
		String cname, actype;
		double bal;
		String actypeOption;

		String[] account_types = { "Savings", "Current", "Recurring Deposit", "Fixed Deposit" };

		int optionForContinue = 0;

		AddingCustomerDetails obj = new AddingCustomerDetails();
		try {
			System.out.print("Please Enter the Account Number  : ");
			acno = s.nextLong();
			if (itms.containsKey(acno)) {
				System.out.print("Sorry! The account had been already added.");
			}

			else {
				System.out.print("Please Enter the Customer Name : ");
				cname = s.next();
				

				System.out.print("1. Savings \n2. Current \n3. Recurring Deposit \n4. Fixed Deposit");
				System.out.print("\nPlease Choose the Account Type :");
				
				actypeOption = s.next();
				if(actypeOption.equals("1") || actypeOption.equals("2") || actypeOption.equals("3") || actypeOption.equals("4"))
				{
					actype = account_types[Integer.parseInt(actypeOption) - 1];
					System.out.println("NOTE : If your balance is less than 1000 your account will be removed automatically");
					System.out.print("Please Enter the Balance Amount. ");
					
					bal = s.nextDouble();
					if(bal<1000)
					{
						System.out.println("Account cannot be added because entered balance is less than 1000");
					}
					else
					{
						obj.setAcno(acno); // invoking setter method for setting account number
						obj.setCname(cname); // invoking setter method for setting customer name
						obj.setActype(actype); // invoking setter method for setting Account type
						obj.setBal(bal); // invoking setter method for setting Balance
						
		
						itms.put(acno, obj);
						System.out.println(itms.get(acno).details()); // printing the customer details after successfully adding
						System.out.println("Customer details have been added successfully!");
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
				
			}
		}
		catch(InputMismatchException e) {
			System.out.println("It  must contain only digits...");
		}
//		catch(Exception e) {
//			System.out.println(e.getMessage());
//		}
	}

	// Defining DeleteCustomer method for deleting the customer from the hash map
	// provided with the account number
	// We are calling this method from the main.java

	static void deleteCustomer() {
		System.out.println("Enter account number you want to delete : ");
		Scanner sc = new Scanner(System.in);
		long ac_no = sc.nextLong();
		try {
			if (itms.containsKey(ac_no)) {
				itms.remove(ac_no);
				System.out.println("Account Deleted Succesfully!...");
			} else {
				throw new Exception("Sorry! User does not exist...");
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
