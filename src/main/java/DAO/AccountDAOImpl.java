package DAO;

import Util.ConnectionUtil;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import DAO.AccountDAO;
import Model.Account;

public class AccountDAOImpl implements AccountDAO{
     // 1) Create New Account

     @Override
     public boolean checkUserNameExists(String userName){
         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT COUNT(*) FROM account WHERE username = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
                return rs.getInt(1)>0;
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

         return false;
     }

   @Override
     public boolean checkAccountIDExists(int id){
         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT COUNT(*) FROM account WHERE account_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               return rs.getInt(1)>0;
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

         return false;
     }

     @Override
     public Account createAccount(Account account){
         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO account(username, password) VALUES (?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
               account.setAccount_id(rs.getInt(1));
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }
         return account;
     }
 
     @Override
     public Account returnAccountByUserName(String userName){
         Account account = new Account();

         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM account WHERE username = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, userName);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               account.setAccount_id(rs.getInt(1));
               account.setUsername(rs.getString(2));
               account.setPassword(rs.getString(3));
               return account;
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

        return null;
     }
 
     // 2) Process Logins
     @Override
     public Account loginAccount(Account account){
         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM account WHERE username = ? AND password = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, account.getUsername());
            preparedStatement.setString(2, account.getPassword());

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               account.setAccount_id(rs.getInt(1));
               return account;
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

        return null;
     }
}
