package Service;

import java.util.ArrayList;


import DAO.MessageDAO;
import DAO.MessageDAOImpl;
import DAO.AccountDAO;
import DAO.AccountDAOImpl;
import Model.Message;

public class MessageService {
    private AccountDAO accountDAO;
    private MessageDAO messageDAO;

    public MessageService(){
        messageDAO = new MessageDAOImpl();
        accountDAO = new AccountDAOImpl();
    }

    // 3)
    public Message submitPost(Message message){
        // check : 
        if (!message.getMessage_text().isBlank() && message.getMessage_text().length() <= 255
            && accountDAO.checkAccountIDExists(message.getPosted_by())){
            return messageDAO.createMessage(message);
        } else {
            return null;
        }
    }

    // 4)
    public ArrayList<Message> retrieveAllMessages(){
        return messageDAO.retrieveMessages();
    }
    // 5)
    public Message retrieveMessage(int message_id){
        return messageDAO.retrieveMessageByMessageID(message_id);
    }

    // 6)
    public Message deleteMessage(int message_id){
        return messageDAO.deleteMessageByMessageID(message_id);
    }
    // 7)
    public Message updateMessage(int message_id, String message_text){
        if (messageDAO.checkMessageIDExists(message_id) && !message_text.isBlank() && message_text.length() <= 255) {
            return messageDAO.updateMessageByMessageID(message_id, message_text);
        } else {
            return null;
        }
    }
    // 8)
    public ArrayList<Message> retrieveAllUserMessages(int account_id){
        return messageDAO.retrieveMessageByAccountID(account_id);
    }

    
}
