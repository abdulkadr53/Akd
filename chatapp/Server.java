import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (ServerSocket sskt = new ServerSocket(PORT)) {
            System.out.println("Server waiting for clients!");

            while (true) {
                Socket skt = sskt.accept();
                System.out.println("Connected to client.");

                BufferedReader In = new BufferedReader(new InputStreamReader(skt.getInputStream()));
                PrintWriter Out = new PrintWriter(skt.getOutputStream(), true);

                new Thread(() -> {
                    try {
                        String clientMsg;
                        while ((clientMsg = In.readLine()) != null) {
                            System.out.println("client: " + clientMsg);
                        }
                    } catch (IOException e) {
                        System.out.println("Client disconnected.");
                    }
                }).start();

                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
                String input;
                while ((input = userInput.readLine()) != null) {
                    Out.println(input);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
