package repository;

import model.Account;

import java.io.IOException;
import java.util.List;

public interface IAccountRepository {
    Account getById(long id);
    List<Account> getAccount();
    boolean exist(long id);
    void add(Account newAccount) throws IOException;
    void update(Account account);

}
