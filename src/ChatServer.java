import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {
    private ThreadsMgmt threadMgmt = new ThreadsMgmt();
    private boolean listening = true;

    public ChatServer() throws IOException {
        listen();
    }

    private void listen() throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        while (listening) {
            Socket userSocket = chatServer.accept();
            PrintWriter userOutputStream = new PrintWriter(userSocket.getOutputStream(), true);
            threadMgmt.registerThread(userOutputStream);
            new HandleUser(userSocket, threadMgmt);
        }
    }
}


