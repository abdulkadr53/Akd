import java.io.*;
import java.net.*;

public class Client {
    private static final String HOST = "localhost";
    private static final int PORT = 8000;

    public static void main(String[] args){
        try {
            Socket skt = new Socket(HOST, PORT);
            System.out.println("Connected to server.");

            BufferedReader In = new BufferedReader(new InputStreamReader(skt.getInputStream()));
            PrintWriter Out = new PrintWriter(skt.getOutputStream(), true);

            new Thread(() -> {
                try {
                    String serverMsg;
                    while ((serverMsg = In.readLine()) != null) {
                        System.out.println("server: " + serverMsg);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            }).start();

            BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
            String input;
            while ((input = userInput.readLine()) != null) {
                Out.println(input);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

