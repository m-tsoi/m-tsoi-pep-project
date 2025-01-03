package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;

public interface AccountDAO {
    // 1) Create New Account
    public boolean checkUserNameExists(String userName);
    public boolean checkAccountIDExists(int id);

    public Account createAccount(Account account);

    public Account returnAccountByUserName(String userName);

    // 2) Process Logins
    public Account loginAccount(Account account);

} 
