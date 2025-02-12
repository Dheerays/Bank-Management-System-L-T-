package bank_system;

public interface AccountStorage {
    void createAccount(BankAccount account);
    BankAccount findAccount(String accountNumber);
}
