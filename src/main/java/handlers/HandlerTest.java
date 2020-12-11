package handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import dao.*;
import models.Account;
import models.Card;

import models.Client;
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
    public static ClientDAO clientDAO = new ClientDAOImpl();
    public static AccountDAO accountDAO = new AccountDAOImpl();
    public static CardDAO cardDAO = new CardDAOImpl();

    static {
        clientDAO.save(new Client(1L, "test1"));
        accountDAO.save(new Account(1L, 1L, "1", 1));
        cardDAO.save(new Card(1L, 1L, 1L, "1"));
        System.out.println(clientDAO.findById(1L).toString());
        System.out.println(accountDAO.findById(1L).toString());
        System.out.println(cardDAO.findById(1L).toString());
        System.out.println("");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        JSONObject jsonObject = new JSONObject(IoToString(exchange.getRequestBody()));
        System.out.println(jsonObject.toString());
        String operation = jsonObject.getString("Operation");
        List<String> responce = null;

        if (operation.equals("CreateNewCard"))
            responce = createNewCard(jsonObject);
        else if (operation.equals("GetAllCards"))
            responce = getAllCards(jsonObject);
        else if (operation.equals("Deposit"))
            responce = deposit(jsonObject);
        else if (operation.equals("GetBalance"))
            responce = getBalance(jsonObject);

        response(exchange, responce);
    }

    private List<String> createNewCard(JSONObject jsonObject){
        Long accountId = Long.parseLong(jsonObject.getString("Account_id"));
        Long clientId = Long.parseLong(jsonObject.getString("Client_id"));
        String accountNumber = jsonObject.getString("Account_number");
        float balance = Float.parseFloat(jsonObject.getString("Balance"));

        service.createNewCard(new Account(accountId, clientId, accountNumber, balance));
        List<Card> list;
        List<String> stringList = new LinkedList<>();
        list = cardDAO.findAllById(accountId);
        for (Card c : list)
            stringList.add(c.toString());

        System.out.println("responce:");
        for (String s : stringList)
            System.out.println(s);

        return stringList;
//        return "Done!";
    }

    private List<String> getAllCards(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");

        System.out.println("1");
        List<Card> list;
        list = service.getAllCards(accountDAO.findById(accountId));

        System.out.println("sizeList = " + list.size());

        List<String> stringList = new LinkedList<>();
        for (Card c : list) {
            System.out.println(c.toString());
            stringList.add(c.toString());
        }

        System.out.println("responce:");
        for (String s : stringList)
            System.out.println(s);

        return stringList;
    }

    private List<String> deposit(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");
        float deposit = jsonObject.getFloat("Deposit");

        service.deposit(new Account(accountId, clientId, accountNumber, balance), deposit);
        List<String> stringList = new LinkedList<>();
        stringList.add("" + accountDAO.findById(accountId).getBalance());

        System.out.println("responce:");
        for (String s : stringList)
            System.out.println(s);

        return stringList;
    }

    private List<String> getBalance(JSONObject jsonObject){
        Long accountId = jsonObject.getLong("Account_id");
        Long clientId = jsonObject.getLong("Client_id");
        String accountNumber = jsonObject.getString("Account_number");
        float balance = jsonObject.getFloat("Balance");

        List<String> stringList = new LinkedList<>();
        stringList.add("" + service.getBalance(new Account(accountId, clientId, accountNumber, balance)));

        System.out.println("responce:");
        for (String s : stringList)
            System.out.println(s);

        return stringList;
    }

    private void response(HttpExchange exchange, List<String> response) throws IOException {
        System.out.println(response);
        exchange.sendResponseHeaders(200, response.size());
        OutputStream os = exchange.getResponseBody();
        for (String s : response)
            os.write(s.getBytes());
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
