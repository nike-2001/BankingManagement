package bankingsystem;

import java.util.Scanner;

//UserDefined Exception for Invalid Attempt To Withdraw
class InvalidWithdraw extends Exception {// generates custom message if invalid withdrawl attempts
	InvalidWithdraw(String msg) {
		super(msg);
	}
}

//UserDefined Exception for Invalid choice 
class InvalidChoice extends Exception {// generates custom message if invalid choice chooses
	InvalidChoice(String msg) {
		super(msg);
	}
}

//UserDefined Exception for Invalid Attempt To Deposit
class InvalidDeposit extends Exception {// generates custom message if invalid deposit attempted
	InvalidDeposit(String msg) {
		super(msg);
	}
}

//UserDefined Exception for Invalid Details
class InvalidDetails extends Exception {// generates custom message if invalid details to be deleted
	InvalidDetails(String msg) {
		super(msg);
	}
}

public class Transaction {
	long accno;
	String ttype;
	double tamt;
	Scanner sc;
	static int transactionAttempts = 0;

	public void repeatTransaction() {

		System.out.println("Do you want to perform transaction ? 1/0");
		int transactionOption = sc.nextInt();
		if (transactionOption == 1)
			this.transaction();
		else {
			System.out.println("Thank You, Have a great Day...");
			System.exit(0);// if no is selected or any other character is found closing the process
		}
	}

	public void transaction() {
		sc = new Scanner(System.in);

		System.out.println("Bank Transaction Page...");

		System.out.print("Enter accno : ");
		accno = sc.nextLong();
		String[] account_types = { "Savings", "Current", "Recurring Deposit", "Fixed Deposit" };
		System.out.print("\n1. Savings \n2. Current \n3. Recurring Deposit  \n4. Fixed Deposit \nChoose transaction type : ");
		String actypeOption = sc.next();
		if(actypeOption.equals("1") || actypeOption.equals("2") || actypeOption.equals("3") || actypeOption.equals("4"))
		{
			ttype = account_types[Integer.parseInt(actypeOption)-1];

		// checks whether accno exists/not and acctype matches with the acctype given
		// during account creation
			if (BankCustomer.itms.containsKey(accno) && ttype.equals(BankCustomer.itms.get(accno).getActype())) {//
	//		 This menu is for the user to select if he wants to Withdraw / deposit  
	
				System.out.println("1. [Withdraw]   2. [Deposit] ");
				String option = sc.next();
				if (option.equals("1")) {
					System.out.println("Your current balance is: "+BankCustomer.itms.get(accno).getBal());
					System.out.println("NOTE : If your balance is less than 1000 your account will be removed automatically");
					System.out.print("Enter transaction amount...");
					
					double tamt = sc.nextDouble();
					double customerBal = BankCustomer.itms.get(accno).getBal();
					if (tamt < customerBal) {
						// transaction amount must be >0 and should be multiples of 100
						if (tamt > 0 && tamt % 100 == 0) {
							double newBal = customerBal - tamt;
							BankCustomer.itms.get(accno).setBal(newBal);
							System.out.println("New balance is: "+BankCustomer.itms.get(accno).getBal());
							System.out.println("Withdrawl Successful..");
							if(newBal<1000) {
								BankCustomer.itms.remove(accno);
								System.out.println("Account deleted due to balance less than 1000");
							}
						} 
						else {
							try {
								throw new InvalidWithdraw("Sorry, Transaction amount must be multiples of 100 only...");
							} 
							catch (InvalidWithdraw iw) {
								System.out.println(iw.getMessage());
							}
						}
					}
					// Checking if the customer balance is less then the tamt and also less then cannot be withdrawed
					else if (tamt > customerBal || tamt < 100) {
						try {
							throw new InvalidWithdraw("Sorry,Invalid withdrawl attempted.Transaction failed ");
						} 
						catch (InvalidWithdraw iw) {
							System.out.println(iw.getMessage());
						}
					}
				} 
				else if (option.equals("2")) {
					transactionAttempts++;
					double depositAmount = 0;
					System.out.print("Enter the amount you want to deposit : ");
					depositAmount = sc.nextDouble();
					if (depositAmount > 50000) {
						try {
							throw new InvalidDeposit("Invalid Deposit...");
						} 
						catch (InvalidDeposit id) {
							System.out.println(id.getMessage());
						}
					} 
					else {
						double customerBal = BankCustomer.itms.get(accno).getBal();
						BankCustomer.itms.get(accno).setBal(customerBal + depositAmount);
						System.out.println("New balance is: "+BankCustomer.itms.get(accno).getBal());		//printing new balance after deposit 
						System.out.println("Deposit Successful...");
					}
	
				} 
				else {
					try {
						throw new InvalidChoice("Invalid Choice...");					//exception if entered option is invalid
					} 
					catch (InvalidChoice ic) {
						System.out.println(ic.getMessage());
					}
				}
			} 
			else {
				try {
					//exception if entered account number and account type are not valid 
					throw new InvalidDetails("User does not exist with the above details....");		
				} 
				catch (InvalidDetails id) {
					System.out.println(id.getMessage());
				}
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
		Login.options();
	}
}
