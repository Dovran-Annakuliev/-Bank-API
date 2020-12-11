package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import models.Account;
import models.Card;

import org.json.JSONObject;
import services.ServiceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class HandlerTest implements HttpHandler {
    public static ServiceImpl service = new ServiceImpl();

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JSONObject jsonObject = new JSONObject(IoToString(exchange.getRequestBody()));
        System.out.println(jsonObject.toString());
        String operation = jsonObject.getString("Operation");
        System.out.println("operation = " + operation);
        String responce = null;

        System.out.println("1");

        if (operation.equals("CreateNewCard")) {
            System.out.println("if");
            responce = createNewCard(jsonObject);
        }
        else if (operation.equals("GetAllCards"))
            responce = getAllCards(jsonObject);
        else if (operation.equals("Deposit"))
            responce = deposit(jsonObject);
        else if (operation.equals("GetBalance"))
            responce = getBalance(jsonObject);
        System.out.println("3");

        response(exchange, responce);
    }

    private String createNewCard(JSONObject jsonObject){
        Long accountId = Long.parseLong(jsonObject.getString("Account_id"));
        Long clientId = Long.parseLong(jsonObject.getString("Client_id"));
        String accountNumber = jsonObject.getString("Account_number");
        float balance = Float.parseFloat(jsonObject.getString("Balance"));

        service.createNewCard(new Account(accountId, clientId, accountNumber, balance));
        return "Done!";
    }

    private String getAllCards(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");

        List<Card> list = new LinkedList<>();
        list = service.getAllCards(new Account(accountId, clientId, accountNumber, balance));
        return list.toString();
    }

    private String deposit(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");
        float deposit = jsonObject.getFloat("Deposit");

        service.deposit(new Account(accountId, clientId, accountNumber, balance), deposit);
        return "Done!";
    }

    private String getBalance(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");

        Float bal = service.getBalance(new Account(accountId, clientId, accountNumber, balance));
        return bal.toString();
    }

    private void response(HttpExchange exchange, String response) throws IOException {
        System.out.println("test");

        exchange.sendResponseHeaders(200, response.length());
        OutputStream os = exchange.getResponseBody();
        os.write(response.getBytes());
        os.close();
    }

    private String IoToString(InputStream inputStream) {
        String res = "";
        Scanner scanner = new Scanner(inputStream);
        while (scanner.hasNext()){
            res += scanner.next();
        }
        return res;
    }
}
