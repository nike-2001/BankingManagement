BankingManagement

Project based on pure core java concepts

1)Create Login/Sign-in would display to ask user id and password if the user details are valid then to enter the page to "Bank Transactions" details otherwise to raise the user defined exception 'invaliduser'

2)To create "Bank customer" with acno, cname, actype, bal details to input and print using setter and getter methods?

3)Once user create account in "bank customer" to enter "bank transaction" with acno, ttype, tamt if the acno is valid then

perform transaction types

    '1'[with draw]   
when customer choose '1' if acno is valid

  tamt <bal 
  

  tamt >=100(Multiples of 100) 
And it should not be less then 100

  if tamt >bal 
or tamt<100 then raise user defined exception 'invalid withdraw'

  '2'[deposit]  
when customer choose '2' if acno is valid
tamt<=50000

    if tamt>50000   <br />
then raise user defined exception 'invalid deposit'

if user ttype is otherthan 'w' or 'd' raise user defined exception 'invalid transaction'

4)to Print customer details i.e acno,cname,bal for given acno is valid otherwise raise exception?

5)to add the new customer or delete customer details if bal is less than 1000?
  
  
  
To compile the whole package :

    use javac -d . bankingsystem\Main.java
  
To run the whole app :

    use java bankingsystem.Main
  
  
