package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Model.Account;

public interface AccountDAO {

    // 1) Create New Account
    public void createAccount(Account account);

    // 2) Process Logins
    public void loginAccount(Account account);

} 
