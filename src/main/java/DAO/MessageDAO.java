package DAO;

import Model.Message;


public interface MessageDAO {
    // 3) Create New Message
    public void createMessage(Message message);

    // 4) Retrieve all Messages
    public void retrieveMessages();

    // 5) Retrieve Message by ID
    public Message retrieveMessageByMessageID(int message_id);

    // 6) Delete Message by MessageID
    public Message deleteMessageByMessageID(int message_id);

    // 7) Update Message by MessageID
    public Message updateMessageByMessageID(int message_id);

    // 8) Retrieve all messages for user
    public void retrieveMessageByAccountID(int account_id);
}
