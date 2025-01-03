package Controller;

import javax.security.auth.login.AccountExpiredException;

import io.javalin.Javalin;
import io.javalin.http.Context;

import Model.Account;
import Model.Message;

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
    public Javalin startAPI() {
        Javalin app = Javalin.create();
        app.get("example-endpoint", this::exampleHandler);

        /* ENDPOINTS IN HERE???? I ADDED */
        app.start(8080);
        
        // 1) POST localhost:8080/register
        app.post("/register", this::registerHandler);

        // 2) POST localhost:8080/login
        app.post("/login", ctx -> {
            //logic to be executed when this endpoint is hit
            

        });

        // 3) POST localhost:8080/messages
        app.post("/messages", ctx -> {
            //logic to be executed when this endpoint is hit
        });

        // 4) GET localhost:8080/messages
        app.get("/messages", ctx -> {
            //logic to be executed when this endpoint is hit
        });

        // 5) GET localhost:8080/messages/{message_id}
        app.get("/messages/{message_id}", ctx -> {
            //logic to be executed when this endpoint is hit
        });

        // 6) DELETE localhost:8080/messages/{message_id}
        app.delete("/messages/{message_id}", ctx -> {
            //logic to be executed when this endpoint is hit
        });

        // 7) PATCH localhost:8080/messages/{message_id}
        app.patch("/messages/{message_id}", ctx -> {
            //logic to be executed when this endpoint is hit
        });
        
        // 8) GET localhost:8080/accounts/{account_id}/messages
        app.post("/accounts/{account_id}/messages", ctx -> {
            //logic to be executed when this endpoint is hit
        });





        /* ENDPOINTS ENDPOINT */


        return app;
    }

    /**
     * This is an example handler for an example endpoint.
     * @param context The Javalin Context object manages information about both the HTTP request and response.
     */
    private void exampleHandler(Context context) {
        context.json("sample text");
    }

    // MY CODE
    // 1
    private void registerHandler(Context context) {
            // given body = json account but no account_id
        Account user = context.bodyAsClass(Account.class);
            // if and only if username isnt blank, at least 4 characters, and no account w/ username exists
                // if ues, RESPONSE BODY will have JSON of the account including account_id
            
            // Steps: 
                // 1) Parse JSON into Account Object?
                // 2) Validate data
                // 3) Handle Success

        if (user.getUsername() != null && !user.getUsername().isBlank() &&
            user.getPassword() != null && user.getPassword().length() >= 4){ //&& check databse??){}

            context.status(200); // if good
        }
        else{
            context.status(400); // if bad
        }
    }

    private void loginHandler(Context context) {


    }
}