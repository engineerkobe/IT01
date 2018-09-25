package it04;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class IT04{

    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            System.out.println(response.length());
            t.sendResponseHeaders(200, response.length());
//            ((Object) t).addResponseHeader("200",response);
            /*
            Map<String,List<String>> m = t.getRequestHeaders();
            for (List<String> key : m.values()) {
            	for(String tmp:key) {
                System.out.println(tmp);
            	}
            }
            */
                System.out.println(t.getRequestURI() );
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

}