package bank_system;

import java.util.ArrayList;
import java.util.List;

public class InMemoryAccountStorage implements AccountStorage {
    private List<BankAccount> accounts = new ArrayList<>();

    @Override
    public void createAccount(BankAccount account) {
        accounts.add(account);
    }

    @Override
    public BankAccount findAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
}
