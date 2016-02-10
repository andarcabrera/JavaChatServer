import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Hashtable;

/**
 * Created by andacabrera29 on 2/9/16.
 */
public class ChatServer {
    private Hashtable allOutputStreams = new Hashtable();
    private boolean listening = true;

    public ChatServer() throws IOException {
        listen();
    }

    private void listen() throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        while (listening) {
            Socket userSocket = chatServer.accept();
            PrintWriter userOutputStream = new PrintWriter(userSocket.getOutputStream(), true);
            allOutputStreams.put(userSocket, userOutputStream);
            new HandleUser(this, userSocket);
        }
    }

    public void transmitMessage(String message) throws IOException {
        synchronized (allOutputStreams) {
            for (Enumeration e = allOutputStreams.elements(); e.hasMoreElements(); ) {
                PrintWriter currentOutStream = (PrintWriter) e.nextElement();
                currentOutStream.println(message);
            }
        }
    }

    void removeConnection(Socket s) {
        synchronized (allOutputStreams) {
            allOutputStreams.remove(s);
            try {
                s.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


