package bank_system.src.main.java;

import java.io.IOException;
import java.util.Scanner;

public class BankManagementSystem {
    private FileHandler fileHandler = new FileHandler();
    private static AccountStorage accountStorage;
    private static Scanner scanner = new Scanner(System.in);

    public BankManagementSystem(AccountStorage storage) {
        accountStorage = storage;
    }

    public static void main(String[] args) {
        BankManagementSystem bankSystem = new BankManagementSystem(new InMemoryAccountStorage());
        bankSystem.run();
    }

    public void run() {
        while (true) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    depositAmount();
                    break;
                case 3:
                    transferAmount();
                    break;
                case 4:
                    accountInfo();
                    break;
                case 5:
                    checkBalance();
                    break;
                case 6:
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void displayMenu() {
        System.out.println("\nBank Management System Menu:");
        System.out.println("1. Create Account");
        System.out.println("2. Deposit Amount");
        System.out.println("3. Transfer Amount");
        System.out.println("4. Account Info");
        System.out.println("5. Check Balance");
        System.out.println("6. Exit");
        System.out.print("Enter your choice: ");
    }

    private void createAccount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter Initial Deposit Amount: $");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline

        BankAccount account = new BankAccount(accountNumber, customerName, initialBalance);
        accountStorage.createAccount(account);

        System.out.println("Account created successfully.");
    }

    private void depositAmount() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accountStorage.findAccount(accountNumber);

        if (account != null) {
            System.out.print("Enter Deposit Amount: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            account.deposit(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    private void transferAmount() {
        System.out.print("Enter Source Account Number: ");
        String sourceAccountNumber = scanner.nextLine();
        BankAccount sourceAccount = accountStorage.findAccount(sourceAccountNumber);

        System.out.print("Enter Target Account Number: ");
        String targetAccountNumber = scanner.nextLine();
        BankAccount targetAccount = accountStorage.findAccount(targetAccountNumber);

        if (sourceAccount != null && targetAccount != null) {
            System.out.print("Enter Transfer Amount: $");
            double amount = scanner.nextDouble();
            scanner.nextLine(); // Consume newline
            sourceAccount.transfer(targetAccount, amount);
        } else {
            System.out.println("One or both accounts not found.");
        }
    }

    private void accountInfo() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accountStorage.findAccount(accountNumber);

        if (account != null) {
            account.displayAccountInfo();
        } else {
            System.out.println("Account not found.");
        }
    }

    private void checkBalance() {
        System.out.print("Enter Account Number: ");
        String accountNumber = scanner.nextLine();
        BankAccount account = accountStorage.findAccount(accountNumber);

        if (account != null) {
            System.out.println("Balance: $" + account.getBalance());
        } else {
            System.out.println("Account not found.");
        }
    }

    public FileHandler getFileHandler() {
        return fileHandler;
    }

    public void setFileHandler(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }
}
