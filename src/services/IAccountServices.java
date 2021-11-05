package services;


import model.Account;

import java.util.List;

public interface IAccountServices {
    Account getByID(long id) throws Exception;

    List<Account> getAccount() throws Exception;

    void add(Account newAccount) throws Exception;

    void update(Account account) throws Exception;
}
