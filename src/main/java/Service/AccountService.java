package Service;
import DAO.AccountDAO;
import Model.Account;
import Model.Message;


public class AccountService {
    private AccountDAO accountDAO;

    public AccountService(AccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public Account register(String username, String password){
        Account account = new Account();
        account.setUsername(username);
        account.setPassword(password); // Hash passwords in production

        return account;
    }

}