package services;

import model.Account;
import model.Drink;
import repository.AccountRepository;
import repository.DrinkRepository;
import repository.IAccountRepository;
import repository.IDrinkRepository;

import java.util.List;

public class AccountServices implements IAccountServices{
    private IAccountRepository accountRepository;

    public AccountServices() {
        accountRepository = new AccountRepository();
    }
    @Override
    public Account getByID(long id) throws Exception {
        Account account = accountRepository.getById(id);
        if (account != null)
            throw new Exception("Account already exists");
        return account;
    }

    @Override
    public List<Account> getAccount() throws Exception {
        return accountRepository.getAccount();
    }

    @Override
    public void add(Account newAccount) throws Exception {
        if (accountRepository.exist(newAccount.getId()))
            throw new Exception("account already exists");
        accountRepository.add(newAccount);

    }

    @Override
    public void update(Account account) throws Exception {
        if (accountRepository.exist(account.getId()))
            throw new Exception("account already exists");
        accountRepository.update(account);

    }

    }
