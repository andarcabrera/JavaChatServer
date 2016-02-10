import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUser extends Thread {
    private Socket userSocket = null;
    private ChatServer chatServer = null;

    public HandleUser(ChatServer server, Socket socket) {
        this.userSocket = socket;
        this.chatServer = server;
        start();
    }

    public void run() {

        try (
                PrintWriter output = new PrintWriter(userSocket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
        ) {
            String messageFromUser;
            while (true) {
                messageFromUser = input.readLine();
                chatServer.transmitMessage(messageFromUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatServer.removeConnection(userSocket);
        }
    }
}
