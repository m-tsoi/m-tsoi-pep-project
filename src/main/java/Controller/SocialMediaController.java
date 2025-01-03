package Controller;

import javax.security.auth.login.AccountExpiredException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.javalin.Javalin;
import io.javalin.http.Context;
import com.fasterxml.jackson.databind.JsonNode;

import Model.Account;
import Model.Message;

import Service.AccountService;
import Service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller. The endpoints you will need can be
 * found in readme.md as well as the test cases. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
public class SocialMediaController {
    /**
     * In order for the test cases to work, you will need to write the endpoints in the startAPI() method, as the test
     * suite must receive a Javalin object from this method.
     * @return a Javalin app object which defines the behavior of the Javalin controller.
     */

    AccountService accountService;
    MessageService messageService;

    public SocialMediaController(){
        this.accountService = new AccountService();
        this.messageService = new MessageService();
    }


    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        /* ENDPOINTS IN HERE???? I ADDED */
        // 1) POST localhost:8080/register
        app.post("/register", this::registerHandler);

        // 2) POST localhost:8080/login
        app.post("/login", this::loginHandler);

        // 3) POST localhost:8080/messages
        app.post("/messages", this::postMessageHandler);

        // 4) GET localhost:8080/messages
        app.get("/messages", this::getAllMessageHandler);

        // 5) GET localhost:8080/messages/{message_id}
        app.get("/messages/{message_id}", this::getMessageHandler);

        // 6) DELETE localhost:8080/messages/{message_id}
        app.delete("/messages/{message_id}", this::deleteMessageHandler);

        // 7) PATCH localhost:8080/messages/{message_id}
        app.patch("/messages/{message_id}", this::updateMessageHandler);
        
        // 8) GET localhost:8080/accounts/{account_id}/messages
        app.get("/accounts/{account_id}/messages", this::getAllUserMessageHandler);

        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context ctx) {
        ctx.json("sample text");
    }

    // MY CODE
    // 1)
    // @param context given JSON account gives Account
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void registerHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);

        Account returnAccount = accountService.register(account);
        if(returnAccount!=null) {
            ctx.status(200).json(mapper.writeValueAsString(returnAccount));
        }else {
            ctx.status(400);
        }       
    }

    // @param Question 2
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void loginHandler(Context ctx) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        Account account = mapper.readValue(ctx.body(), Account.class);
        Account returnAccount = accountService.login(account);
        if(returnAccount!=null) {
            ctx.status(200).json(mapper.writeValueAsString(returnAccount));
        }else {
            ctx.status(401);
        }   
    }

    // @param Question 3
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void postMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        Message message = mapper.readValue(ctx.body(), Message.class);
        Message returnMessage = messageService.submitPost(message);
        if(returnMessage!=null) {
            ctx.status(200).json(mapper.writeValueAsString(returnMessage));
        }else {
            ctx.status(400);
        }   
    }

    // @param Question 4
    private void getAllMessageHandler(Context ctx){
        ctx.status(200).json(messageService.retrieveAllMessages());
    }

    // @param Question 5
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void getMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String string_message_id = ctx.pathParam("message_id");
        Integer message_id = Integer.parseInt(string_message_id);
        Message returnMessage = messageService.retrieveMessage(message_id);
        if(returnMessage!=null) {
            ctx.status(200).json(mapper.writeValueAsString(returnMessage));
        }else {
            ctx.status(200);
        }  
    }

    // @param Question 6
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void deleteMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();
        String string_message_id = ctx.pathParam("message_id");
        Integer message_id = Integer.parseInt(string_message_id);
        Message returnMessage = messageService.deleteMessage(message_id);
        if(returnMessage!=null) {
            ctx.status(200).json(mapper.writeValueAsString(returnMessage));
        }else {
            ctx.status(200);
        }  
    }

    // @param Question 7
    // @throws JsonProcessingException will be thrown if there is an issue converting JSON into an object.
    private void updateMessageHandler(Context ctx) throws JsonProcessingException{
        ObjectMapper mapper = new ObjectMapper();

        JsonNode jsonNode = mapper.readTree(ctx.body());
        String message_text = jsonNode.get("message_text").asText();

        String string_message_id = ctx.pathParam("message_id");
        Integer message_id = Integer.parseInt(string_message_id);
        Message returnMessage = messageService.updateMessage(message_id, message_text);
        if(returnMessage!= null) {
            ctx.status(200).json(mapper.writeValueAsString(returnMessage));
        }else {
            ctx.status(400);
        }  
    }

    // @param Question 8
    private void getAllUserMessageHandler(Context ctx) {
        String string_account_id = ctx.pathParam("account_id");
        Integer account_id = Integer.parseInt(string_account_id);
        ctx.status(200).json(messageService.retrieveAllUserMessages(account_id));
    }

    
}