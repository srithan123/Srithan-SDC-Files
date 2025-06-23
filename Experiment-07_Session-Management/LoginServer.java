import com.sun.net.httpserver.*;
import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;
import java.nio.charset.StandardCharsets; // ✅ Add this


public class LoginServer {
    // Simulated in-memory session store (username -> sessionId)
    private static Map<String, String> sessions = new ConcurrentHashMap<>();

    public static void main(String[] args) throws IOException {
        // Create HTTP server on port 8080
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        // Handlers
        server.createContext("/login", new LoginHandler());
        server.createContext("/transaction", new TransactionHandler());

        server.setExecutor(Executors.newFixedThreadPool(5)); // Simple thread pool
        server.start();

        System.out.println("Server started at http://localhost:8080/login");
    }

    static class LoginHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String method = exchange.getRequestMethod();

            if ("POST".equalsIgnoreCase(method)) {
                InputStreamReader isr = new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String formData = br.readLine();

                // Parse form data
                Map<String, String> params = queryToMap(formData);
                String username = params.get("username");

                // Create a session ID
                String sessionId = UUID.randomUUID().toString();
                sessions.put(sessionId, username);

                // Set cookie
                Headers headers = exchange.getResponseHeaders();
                headers.add("Set-Cookie", "sessionId=" + sessionId + "; Max-Age=3600");

                // Response
                String response = "<html><body>" +
                        "<h3>Welcome, " + username + "</h3>" +
                        "<a href='/transaction'>View Transaction History</a>" +
                        "</body></html>";

                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.getBytes().length);
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            } else {
                // Display form for GET
                String response = "<html><body>" +
                        "<form method='POST' action='/login'>" +
                        "Username: <input type='text' name='username'><br>" +
                        "<input type='submit' value='Login'>" +
                        "</form></body></html>";
                exchange.getResponseHeaders().set("Content-Type", "text/html");
                exchange.sendResponseHeaders(200, response.length());
                OutputStream os = exchange.getResponseBody();
                os.write(response.getBytes());
                os.close();
            }
        }
    }

    static class TransactionHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            // Extract sessionId from cookies
            Headers headers = exchange.getRequestHeaders();
            String sessionId = getSessionIdFromCookies(headers.getFirst("Cookie"));

            String username = sessions.get(sessionId);

            String response;
            if (username != null) {
                response = "<html><body><h3>Transaction History for: " + username + "</h3></body></html>";
            } else {
                response = "<html><body><h3>Session expired or not logged in.</h3></body></html>";
            }

            exchange.getResponseHeaders().set("Content-Type", "text/html");
            exchange.sendResponseHeaders(200, response.length());
            OutputStream os = exchange.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }

    // Utility method: parse query string
    public static Map<String, String> queryToMap(String query) {
        Map<String, String> result = new HashMap<>();
        if (query != null) {
            for (String param : query.split("&")) {
                String[] entry = param.split("=");
                if (entry.length > 1) {
                    result.put(URLDecoder.decode(entry[0], StandardCharsets.UTF_8),
                            URLDecoder.decode(entry[1], StandardCharsets.UTF_8));
                }
            }
        }
        return result;
    }

    // Utility method: get sessionId from cookie header
    public static String getSessionIdFromCookies(String cookieHeader) {
        if (cookieHeader == null) return null;
        String[] cookies = cookieHeader.split(";");
        for (String cookie : cookies) {
            String[] pair = cookie.trim().split("=");
            if (pair.length == 2 && pair[0].equals("sessionId")) {
                return pair[1];
            }
        }
        return null;
    }
}
