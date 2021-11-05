package model;

public class Account {
    private String name;
    private String password;
    private long id;

    public Account(String raw) {
        String[] fields = raw.split(",");
        name = fields[0];
        password = fields[1];
        id = Long.parseLong(fields[2]);
    }

    public String getName() {
        return name;
    }

    public Account(String username, String password, long id) {
        name = username;
        this.password = password;
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("%s,%s,%d", name,password,id);
    }
    public static void transferFields(Account oldAccount, Account newAccount){
        oldAccount.id = newAccount.id;
        oldAccount.password = newAccount.password;
        oldAccount.name = newAccount.name;
    }

}