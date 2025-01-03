package DAO;
import DAO.MessageDAO;
import Model.Message;

public class MessageDAOImpl implements MessageDAO{
     // 3) Create New Message
    @Override
    public void createMessage(Message message){
        
    }

    // 4) Retrieve all Messages
    @Override
    public void retrieveMessages();

    // 5) Retrieve Message by ID
    @Override
    public Message retrieveMessageByMessageID(int message_id);

    // 6) Delete Message by MessageID
    @Override
    public Message deleteMessageByMessageID(int message_id);

    // 7) Update Message by MessageID
    @Override
    public Message updateMessageByMessageID(int message_id);

    // 8) Retrieve all messages for user
    @Override
    public void retrieveMessageByAccountID(int account_id);
    
}
