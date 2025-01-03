package DAO;

import java.util.ArrayList;
import Model.Message;


public interface MessageDAO {
    // 3) Create New Message
    public Message createMessage(Message message);

    // 4) Retrieve all Messages
    public ArrayList<Message> retrieveMessages();

    // 5) Retrieve Message by ID
    public Message retrieveMessageByMessageID(int message_id);

    // 6) Delete Message by MessageID
    public Message deleteMessageByMessageID(int message_id);

    // 7) Update Message by MessageID
    public boolean checkMessageIDExists(int message_id);
    public Message updateMessageByMessageID(int message_id, String message_text);

    // 8) Retrieve all messages for user
    public ArrayList<Message> retrieveMessageByAccountID(int account_id);
}
