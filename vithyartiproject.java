import java.io.*;
import java.util.*;

/*
 * ============================================================
 *              BANK MANAGEMENT SYSTEM
 * ============================================================
 * Technologies: Core Java only
 * Database: None
 * Storage: File Handling
 * Concepts: OOP, ArrayList, Collections, Exception Handling
 * ============================================================
 */

class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    private String accountNumber;
    private String name;
    private String phone;
    private String address;
    private double balance;

    private ArrayList<String> transactions;

    // Constructor
    public Account(String accountNumber, String name,
                   String phone, String address, double initialDeposit) {

        this.accountNumber = accountNumber;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.balance = initialDeposit;

        transactions = new ArrayList<>();

        transactions.add(
            "Account created with initial deposit: ₹" +
            String.format("%.2f", initialDeposit)
        );
    }

    // Getters
    public String getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public double getBalance() {
        return balance;
    }

    // Update account details
    public void updateDetails(String name, String phone, String address) {
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    // Deposit
    public boolean deposit(double amount) {

        if (amount <= 0) {
            return false;
        }

        balance += amount;

        transactions.add(
            "Deposited: ₹" +
            String.format("%.2f", amount) +
            " | Balance: ₹" +
            String.format("%.2f", balance)
        );

        return true;
    }

    // Withdraw
    public boolean withdraw(double amount) {

        if (amount <= 0 || amount > balance) {
            return false;
        }

        balance -= amount;

        transactions.add(
            "Withdrawn: ₹" +
            String.format("%.2f", amount) +
            " | Balance: ₹" +
            String.format("%.2f", balance)
        );

        return true;
    }

    // Add transaction manually
    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }

    // Show transaction history
    public void showTransactions() {

        System.out.println("\n==============================================");
        System.out.println("             TRANSACTION HISTORY");
        System.out.println("==============================================");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
        } else {

            for (int i = 0; i < transactions.size(); i++) {
                System.out.println((i + 1) + ". " + transactions.get(i));
            }
        }

        System.out.println("==============================================");
    }

    // Display account details
    public void displayAccount() {

        System.out.println("\n----------------------------------------------");
        System.out.println("             ACCOUNT DETAILS");
        System.out.println("----------------------------------------------");

        System.out.println("Account Number : " + accountNumber);
        System.out.println("Name           : " + name);
        System.out.println("Phone          : " + phone);
        System.out.println("Address        : " + address);
        System.out.println("Balance        : ₹" +
                String.format("%.2f", balance));

        System.out.println("----------------------------------------------");
    }
}


// ============================================================
// MAIN CLASS
// ============================================================

public class classproject {

    private static final String FILE_NAME = "accounts.dat";

    private static ArrayList<Account> accounts =
            new ArrayList<>();

    private static Scanner sc =
            new Scanner(System.in);


    // ========================================================
    // MAIN METHOD
    // ========================================================

    public static void main(String[] args) {

        loadAccounts();

        while (true) {

            displayMenu();

            int choice = readInt("Enter your choice: ");

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    searchAccount();
                    break;

                case 3:
                    depositMoney();
                    break;

                case 4:
                    withdrawMoney();
                    break;

                case 5:
                    transferMoney();
                    break;

                case 6:
                    checkBalance();
                    break;

                case 7:
                    showTransactions();
                    break;

                case 8:
                    updateAccount();
                    break;

                case 9:
                    displayAllAccounts();
                    break;

                case 10:
                    deleteAccount();
                    break;

                case 11:
                    saveAccounts();
                    System.out.println("\nThank you for using Bank Management System!");
                    System.out.println("Program closed successfully.");
                    sc.close();
                    return;

                default:
                    System.out.println("\nInvalid choice!");
                    System.out.println("Please select 1 to 11.");

            }
        }
    }


    // ========================================================
    // DISPLAY MENU
    // ========================================================

    private static void displayMenu() {

        System.out.println("\n\n");
        System.out.println("================================================");
        System.out.println("           BANK MANAGEMENT SYSTEM");
        System.out.println("================================================");
        System.out.println("1.  Create Account");
        System.out.println("2.  Search Account");
        System.out.println("3.  Deposit Money");
        System.out.println("4.  Withdraw Money");
        System.out.println("5.  Transfer Money");
        System.out.println("6.  Check Balance");
        System.out.println("7.  Transaction History");
        System.out.println("8.  Update Account");
        System.out.println("9.  Display All Accounts");
        System.out.println("10. Delete Account");
        System.out.println("11. Exit");
        System.out.println("================================================");
    }


    // ========================================================
    // CREATE ACCOUNT
    // ========================================================

    private static void createAccount() {

        System.out.println("\n==============================================");
        System.out.println("              CREATE ACCOUNT");
        System.out.println("==============================================");

        String accountNumber;

        while (true) {

            accountNumber =
                    readString("Enter Account Number: ");

            if (findAccount(accountNumber) == null) {
                break;
            }

            System.out.println(
                    "Account number already exists!"
            );
        }

        String name = readString("Enter Name: ");

        String phone = readString("Enter Phone Number: ");

        String address = readString("Enter Address: ");

        double initialDeposit;

        while (true) {

            initialDeposit =
                    readDouble("Enter Initial Deposit: ₹");

            if (initialDeposit >= 0) {
                break;
            }

            System.out.println(
                    "Initial deposit cannot be negative."
            );
        }

        Account account = new Account(
                accountNumber,
                name,
                phone,
                address,
                initialDeposit
        );

        accounts.add(account);

        saveAccounts();

        System.out.println("\nAccount created successfully!");
        System.out.println(
                "Account Number: " + accountNumber
        );
    }


    // ========================================================
    // SEARCH ACCOUNT
    // ========================================================

    private static void searchAccount() {

        System.out.println("\n==============================================");
        System.out.println("               SEARCH ACCOUNT");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

        } else {

            account.displayAccount();
        }
    }


    // ========================================================
    // DEPOSIT MONEY
    // ========================================================

    private static void depositMoney() {

        System.out.println("\n==============================================");
        System.out.println("               DEPOSIT MONEY");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

            return;
        }

        double amount =
                readDouble("Enter amount to deposit: ₹");

        if (account.deposit(amount)) {

            saveAccounts();

            System.out.println(
                    "\nMoney deposited successfully!"
            );

            System.out.println(
                    "New Balance: ₹" +
                    String.format("%.2f",
                            account.getBalance())
            );

        } else {

            System.out.println(
                    "Invalid deposit amount!"
            );
        }
    }


    // ========================================================
    // WITHDRAW MONEY
    // ========================================================

    private static void withdrawMoney() {

        System.out.println("\n==============================================");
        System.out.println("              WITHDRAW MONEY");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

            return;
        }

        double amount =
                readDouble("Enter amount to withdraw: ₹");

        if (amount <= 0) {

            System.out.println(
                    "Invalid amount!"
            );

            return;
        }

        if (amount > account.getBalance()) {

            System.out.println(
                    "Insufficient balance!"
            );

            return;
        }

        if (account.withdraw(amount)) {

            saveAccounts();

            System.out.println(
                    "\nWithdrawal successful!"
            );

            System.out.println(
                    "Remaining Balance: ₹" +
                    String.format("%.2f",
                            account.getBalance())
            );
        }
    }


    // ========================================================
    // TRANSFER MONEY
    // ========================================================

    private static void transferMoney() {

        System.out.println("\n==============================================");
        System.out.println("              MONEY TRANSFER");
        System.out.println("==============================================");

        String senderNumber =
                readString("Enter Sender Account Number: ");

        Account sender =
                findAccount(senderNumber);

        if (sender == null) {

            System.out.println(
                    "Sender account not found!"
            );

            return;
        }

        String receiverNumber =
                readString("Enter Receiver Account Number: ");

        Account receiver =
                findAccount(receiverNumber);

        if (receiver == null) {

            System.out.println(
                    "Receiver account not found!"
            );

            return;
        }

        if (senderNumber.equals(receiverNumber)) {

            System.out.println(
                    "Sender and receiver cannot be same!"
            );

            return;
        }

        double amount =
                readDouble("Enter amount to transfer: ₹");

        if (amount <= 0) {

            System.out.println(
                    "Invalid transfer amount!"
            );

            return;
        }

        if (amount > sender.getBalance()) {

            System.out.println(
                    "Insufficient balance!"
            );

            return;
        }

        sender.withdraw(amount);

        receiver.deposit(amount);

        sender.addTransaction(
                "Transferred ₹" +
                String.format("%.2f", amount) +
                " to Account " +
                receiverNumber
        );

        receiver.addTransaction(
                "Received ₹" +
                String.format("%.2f", amount) +
                " from Account " +
                senderNumber
        );

        saveAccounts();

        System.out.println(
                "\nMoney transferred successfully!"
        );

        System.out.println(
                "Transferred Amount: ₹" +
                String.format("%.2f", amount)
        );

        System.out.println(
                "Sender Balance: ₹" +
                String.format("%.2f",
                        sender.getBalance())
        );
    }


    // ========================================================
    // CHECK BALANCE
    // ========================================================

    private static void checkBalance() {

        System.out.println("\n==============================================");
        System.out.println("               CHECK BALANCE");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

        } else {

            System.out.println(
                    "\nAccount Holder: " +
                    account.getName()
            );

            System.out.println(
                    "Account Number: " +
                    account.getAccountNumber()
            );

            System.out.println(
                    "Current Balance: ₹" +
                    String.format("%.2f",
                            account.getBalance())
            );
        }
    }


    // ========================================================
    // TRANSACTION HISTORY
    // ========================================================

    private static void showTransactions() {

        System.out.println("\n==============================================");
        System.out.println("          TRANSACTION HISTORY");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

        } else {

            account.showTransactions();
        }
    }


    // ========================================================
    // UPDATE ACCOUNT
    // ========================================================

    private static void updateAccount() {

        System.out.println("\n==============================================");
        System.out.println("              UPDATE ACCOUNT");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

            return;
        }

        System.out.println(
                "\nEnter new account details:"
        );

        String name =
                readString("New Name: ");

        String phone =
                readString("New Phone: ");

        String address =
                readString("New Address: ");

        account.updateDetails(
                name,
                phone,
                address
        );

        saveAccounts();

        System.out.println(
                "\nAccount updated successfully!"
        );
    }


    // ========================================================
    // DISPLAY ALL ACCOUNTS
    // ========================================================

    private static void displayAllAccounts() {

        System.out.println("\n==============================================");
        System.out.println("             ALL BANK ACCOUNTS");
        System.out.println("==============================================");

        if (accounts.isEmpty()) {

            System.out.println(
                    "No accounts available."
            );

            return;
        }

        for (Account account : accounts) {

            account.displayAccount();
        }
    }


    // ========================================================
    // DELETE ACCOUNT
    // ========================================================

    private static void deleteAccount() {

        System.out.println("\n==============================================");
        System.out.println("              DELETE ACCOUNT");
        System.out.println("==============================================");

        String accountNumber =
                readString("Enter Account Number: ");

        Account account =
                findAccount(accountNumber);

        if (account == null) {

            System.out.println(
                    "Account not found!"
            );

            return;
        }

        System.out.println(
                "Account Holder: " +
                account.getName()
        );

        String confirmation =
                readString(
                        "Are you sure you want to delete? (yes/no): "
                );

        if (confirmation.equalsIgnoreCase("yes")) {

            accounts.remove(account);

            saveAccounts();

            System.out.println(
                    "\nAccount deleted successfully!"
            );

        } else {

            System.out.println(
                    "\nAccount deletion cancelled."
            );
        }
    }


    // ========================================================
    // FIND ACCOUNT
    // ========================================================

    private static Account findAccount(
            String accountNumber) {

        for (Account account : accounts) {

            if (account.getAccountNumber()
                    .equalsIgnoreCase(accountNumber)) {

                return account;
            }
        }

        return null;
    }


    // ========================================================
    // FILE HANDLING - SAVE
    // ========================================================

    private static void saveAccounts() {

        try {

            FileOutputStream file =
                    new FileOutputStream(FILE_NAME);

            ObjectOutputStream output =
                    new ObjectOutputStream(file);

            output.writeObject(accounts);

            output.close();
            file.close();

        } catch (IOException e) {

            System.out.println(
                    "Error saving accounts: " +
                    e.getMessage()
            );
        }
    }


    // ========================================================
    // FILE HANDLING - LOAD
    // ========================================================

    @SuppressWarnings("unchecked")
    private static void loadAccounts() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            FileInputStream inputFile =
                    new FileInputStream(FILE_NAME);

            ObjectInputStream input =
                    new ObjectInputStream(inputFile);

            accounts =
                    (ArrayList<Account>) input.readObject();

            input.close();
            inputFile.close();

        } catch (IOException | ClassNotFoundException e) {

            System.out.println(
                    "Error loading accounts."
            );
        }
    }


    // ========================================================
    // INTEGER INPUT
    // ========================================================

    private static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        sc.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );
            }
        }
    }


    // ========================================================
    // DOUBLE INPUT
    // ========================================================

    private static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        sc.nextLine().trim()
                );

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid amount."
                );
            }
        }
    }


    // ========================================================
    // STRING INPUT
    // ========================================================

    private static String readString(String message) {

        while (true) {

            System.out.print(message);

            String input =
                    sc.nextLine().trim();

            if (!input.isEmpty()) {
                return input;
            }

            System.out.println(
                    "Input cannot be empty."
            );
        }
    }
}