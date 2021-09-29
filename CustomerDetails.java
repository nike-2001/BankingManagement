package bankingsystem;

import java.util.Scanner;

class CustomerDetails {
	static void customerDetails() {
		System.out.println("Enter customer account number: ");
		Scanner sc = new  Scanner(System.in);
		long acno = sc.nextLong();
		if(BankCustomer.itms.containsKey(acno)) {
			AddingCustomerDetails customer = BankCustomer.itms.get(acno);
			String str = "[ Acc No :"+customer.getAcno()+", Name :"+customer.getCname()+", Acc Type : "+customer.getActype()+", Balance :"+customer.getBal()+" ]";
			System.out.println(str);
		}
		else {
			System.out.println("No account is associated with this account number...");
		}
	}
//	static void deleteIfBalanceIsLessThan1000() {
//		BankCustomer.itms.forEach((k, v) -> {
//			if(v.getBal()<1000) {
//				BankCustomer.itms.remove(k);
//			}
//		});
//	}
	static void customers() {
		if(BankCustomer.itms.size()==0) {
			System.out.println("Cutomers details found empty...");
		}
		else {
			System.out.println("Customer details are...");
			BankCustomer.itms.forEach((k, v) -> System.out.println(BankCustomer.itms.get(k).details()));
		}
		
	}
}
