import Controller.SocialMediaController;
import io.javalin.Javalin;
import javafx.concurrent.Service;
import Service.MessageService;

import Model.Message;

/**
 * This class is provided with a main method to allow you to manually run and test your application. This class will not
 * affect your program in any way and you may write whatever code you like here.
 */
public class Main {

    public static void main(String[] args) {
        MessageService messageService = new MessageService();

        //Message message = new Message(100, 1, "Test message", 1627556845L);
        System.out.println(messageService.updateMessage(100, "chicken"));



    }
}
