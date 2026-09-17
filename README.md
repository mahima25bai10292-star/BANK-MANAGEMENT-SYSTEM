#NAME=MAHIMA SINGH
#REGESTRATION NO=25BAI10292
#SUBJECT=JAVA
#BRANCH=ARTIFICAL INTELLIGENCE AND MACHINE LEARNING



# BANK-MANAGEMENT-SYSTEM
The Bank Management System is a Core Java-based console application for managing basic banking operations. It allows users to create accounts, deposit and withdraw money, check balances, and transfer money. The system also provides account searching, updating, and transaction history features. 
# Bank Management System

A simple **Bank Management System** developed using **Core Java**.
This is a console-based application that allows users to perform basic banking operations such as creating accounts, depositing money, withdrawing money, transferring money, checking balance, and managing transaction history.

## Features

* Create a new bank account
* Search for an account
* Deposit money
* Withdraw money
* Transfer money between accounts
* Check account balance
* View transaction history
* Update account details
* Display all accounts
* Delete an account
* Save account data using file handling
* Load saved account data automatically

## Technologies Used

* **Language:** Java
* **Application Type:** Console-based
* **Database:** None
* **Storage:** File Handling / Serialization
* **Collections:** ArrayList
* **Concepts:** OOP, Collections, Exception Handling, File I/O

## Project Structure

```text
BankManagementSystem/
│
├── classproject.java
├── accounts.dat
└── README.md
```

> `accounts.dat` is created automatically by the program when account data is saved.

## Requirements

Before running the project, make sure you have:

* Java Development Kit (JDK) installed
* Command Prompt / PowerShell / Terminal
* A Java-compatible IDE such as VS Code, IntelliJ IDEA, or Eclipse (optional)

## How to Run

### 1. Clone the Repository

```bash
git clone YOUR_GITHUB_REPOSITORY_URL
```

### 2. Open the Project Folder

```bash
cd BankManagementSystem
```

### 3. Compile the Java Program

```bash
javac classproject.java
```

### 4. Run the Program

```bash
java classproject
```

## Main Menu

After running the program, the following menu is displayed:

```text
================================================
           BANK MANAGEMENT SYSTEM
================================================
1.  Create Account
2.  Search Account
3.  Deposit Money
4.  Withdraw Money
5.  Transfer Money
6.  Check Balance
7.  Transaction History
8.  Update Account
9.  Display All Accounts
10. Delete Account
11. Exit
================================================
```

## Data Storage

The project does not use a database. Account information is stored locally using Java **Serialization and File Handling**.

The file used for storage is:

```text
accounts.dat
```

The application saves the account list to this file and loads it when the program starts.

## OOP Concepts Used

The project demonstrates important Java concepts including:

* Classes and Objects
* Encapsulation
* Constructors
* Methods
* Access Modifiers
* Serialization
* ArrayList
* Exception Handling
* File Input/Output

The `Account` class stores account number, name, phone, address, balance, and transaction information.

## Account Operations

### Create Account

The user provides:

* Account Number
* Name
* Phone Number
* Address
* Initial Deposit

The program checks that the account number does not already exist before creating the account.

### Deposit Money

The user enters an account number and deposit amount. The balance is updated and the transaction is recorded.

### Withdraw Money

The program checks whether the withdrawal amount is valid and whether sufficient balance is available before completing the withdrawal.

### Money Transfer

Money can be transferred from one account to another after checking both accounts, preventing transfers to the same account, and checking the sender's balance.

### Transaction History

Each account maintains a list of transactions using `ArrayList<String>`. Users can view the transaction history for an account.

## File Handling

The application uses:

```java
FileOutputStream
ObjectOutputStream
FileInputStream
ObjectInputStream
```

Account data is saved to and loaded from `accounts.dat` using Java object serialization.

## Input Validation

The program handles invalid numeric input using exception handling. It also prevents empty string input and invalid deposit, withdrawal, and transfer amounts.

## Learning Objectives

This project helps demonstrate practical use of:

1. Core Java programming
2. Object-Oriented Programming
3. ArrayList and Collections
4. Exception Handling
5. File Handling
6. Java Serialization
7. Console-based application development

## Future Enhancements

The project can be extended in the future by adding:

* User login and authentication
* PIN/password protection
* Database connectivity
* Interest calculation
* ATM functionality
* Admin and customer roles
* Graphical User Interface (GUI)
* Online banking features


