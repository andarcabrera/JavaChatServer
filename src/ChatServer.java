import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by andacabrera29 on 2/9/16.
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        Socket chatClient = chatServer.accept();

        BufferedReader input = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
        PrintWriter output = new PrintWriter(chatClient.getOutputStream(), true);

        String messageFromClient;
        while ((messageFromClient = input.readLine()) != null) {
            output.println("MESSAGE: " + messageFromClient);
        }
    }
}
