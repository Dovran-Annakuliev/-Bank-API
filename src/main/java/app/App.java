package app;

import com.sun.net.httpserver.HttpServer;
import handlers.HandlerTest;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class App {
    public static void main(String[] args) throws IOException {
//        ThreadPoolExecutor threadPoolExecutor = (ThreadPoolExecutor) Executors.newFixedThreadPool(10);
        System.out.println("Server start");
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8001), 0);
        server.createContext("/test", new HandlerTest());
//        server.setExecutor(threadPoolExecutor);
        server.start();
    }
}
