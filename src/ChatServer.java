import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class ChatServer {
    private OutputStreamsMgmt outputStreams = new OutputStreamsMgmt();

    public void listen() throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        try {
            while (true) {
                Runtime.getRuntime().addShutdownHook(new JVMShutdownHook());
                Socket userSocket = chatServer.accept();
                PrintWriter userOutputStream = new PrintWriter(userSocket.getOutputStream(), true);
                outputStreams.registerOutputStream(userOutputStream);
                HandleUserThread userThread = new HandleUserThread(userSocket, outputStreams);
                userThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatServer.close();
        }
    }
}




