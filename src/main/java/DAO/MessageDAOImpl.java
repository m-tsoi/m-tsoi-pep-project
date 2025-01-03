package DAO;

import Util.ConnectionUtil;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DAO.MessageDAO;
import Model.Message;

public class MessageDAOImpl implements MessageDAO{
     // 3) Create New Message
    @Override
    public Message createMessage(Message message){
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "INSERT INTO message(posted_by, message_text,time_posted_epoch) VALUES (?,?,?);";

            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1, message.getPosted_by());
            preparedStatement.setString(2, message.getMessage_text());
            preparedStatement.setLong(3, message.getTime_posted_epoch());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()){
               message.setMessage_id(rs.getInt(1));
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }
         return message;
    }

    // 4) Retrieve all Messages
    @Override
    public ArrayList<Message> retrieveMessages(){
        ArrayList<Message> messages =  new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM message;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
    

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               Message newMessage = new Message();
               newMessage.setMessage_id(rs.getInt(1));
               newMessage.setPosted_by(rs.getInt(2));
               newMessage.setMessage_text(rs.getString(3));
               newMessage.setTime_posted_epoch(rs.getLong(4));
               messages.add(newMessage);
            }
        } catch(SQLException e){
               System.out.println(e.getMessage());
        }
        return messages;
    }

    // 5) Retrieve Message by ID
    @Override
    public Message retrieveMessageByMessageID(int message_id){
        Message message = new Message();
        message.setMessage_id(message_id);

         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM message WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            ResultSet rs = preparedStatement.executeQuery();
            if(rs.next()){
               message.setPosted_by(rs.getInt(2));
               message.setMessage_text(rs.getString(3));
               message.setTime_posted_epoch(rs.getLong(4));
               return message;
            }
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

        return null;
    }

    // 6) Delete Message by MessageID
    @Override
    public Message deleteMessageByMessageID(int message_id){
        Message message = this.retrieveMessageByMessageID(message_id);
        if (message == null){
            return message;
        }

        message.setMessage_id(message_id);

         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "DELETE FROM message WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

            preparedStatement.executeUpdate();
         } catch(SQLException e){
               System.out.println(e.getMessage());
         }

        return message;
    }

    // 7) Update Message by MessageID
    @Override
    public boolean checkMessageIDExists(int message_id){
        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT COUNT(*) FROM message WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, message_id);

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
    public Message updateMessageByMessageID(int message_id, String message_text){
        Message message = this.retrieveMessageByMessageID(message_id);
        if (message == null){
            return null;
        }
        message.setMessage_text(message_text);

         try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "UPDATE message SET message_text = ? WHERE message_id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, message_text);
            preparedStatement.setInt(2, message_id);

            preparedStatement.executeUpdate();
         } catch(SQLException e){
               System.out.println(e.getMessage());
               e.printStackTrace();
         }

        return message;
    }

    // 8) Retrieve all messages for user
    @Override
    public ArrayList<Message> retrieveMessageByAccountID(int account_id){
        ArrayList<Message> messages =  new ArrayList<>();

        try (Connection connection = ConnectionUtil.getConnection()){
            String sql = "SELECT * FROM message WHERE posted_by = ?;";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, account_id);
    

            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()){
               Message newMessage = new Message();
               newMessage.setMessage_id(rs.getInt(1));
               newMessage.setPosted_by(account_id);
               newMessage.setMessage_text(rs.getString(3));
               newMessage.setTime_posted_epoch(rs.getLong(4));
               messages.add(newMessage);
            }
        } catch(SQLException e){
               System.out.println(e.getMessage());
        }
        return messages;

    }
    
}
