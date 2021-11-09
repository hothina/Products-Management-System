package repository;

import exception.ProductException;
import model.Account;

import services.AccountServices;
import utils.CsvUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AccountRepository implements IAccountRepository {
    private final String ACCOUNT_PATH = "data\\accounts.csv";

    public AccountRepository() {
    }

    @Override
    public Account getById(long id) {
        List<Account> accountList = getAccount();
        for (Account account : accountList) {
            if (account.getId() == id)
                return account;
        }
        return null;
    }

    @Override
    public List<Account> getAccount() {
        List<Account> newAccountList = new ArrayList<>();
        try {
            List<String> records = CsvUtils.read(ACCOUNT_PATH);
            for (String record : records) {
                newAccountList.add(new Account(record));
            }
            return newAccountList;
        } catch (IOException ex) {
            throw new ProductException("Account error");
        }
    }

    @Override
    public boolean exist(long id) {
        return getById(id) != null;
    }

    @Override
    public void add(Account newAccount) throws IOException {
        List<Account> accountList = getAccount();
        accountList.add(newAccount);
        CsvUtils.write(ACCOUNT_PATH, accountList);
    }

    public void remove(Account oldAccount, List<Account> accountList) throws IOException {
        accountList.remove(oldAccount);
        CsvUtils.write(ACCOUNT_PATH, accountList);
    }


    @Override
    public void update(Account account) {
        Account oldAccount = getById(account.getId());
        Account.transferFields(oldAccount, account);
    }


}
