package Service;
import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Account;


public class AccountService {
    private AccountDAO accountDAO;

    public AccountService() {
        accountDAO = new AccountDAOImpl(); // might be dependency injection
    }

    public Account register(Account account){
        if (account.getUsername() != null && !account.getUsername().isBlank() &&
            account.getPassword() != null && account.getPassword().length() >= 4 &&
            !accountDAO.checkUserNameExists(account.getUsername())){
             
            return accountDAO.createAccount(account);
        }
        else{
            return null;
        }
    }

    public Account login(Account account){
        return accountDAO.loginAccount(account);
    }

}