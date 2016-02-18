import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class ChatServer {
    private OutputStreamsMgmt outputStreams = new OutputStreamsMgmt();
    private ArrayList<HandleUserThread> allThreads = new ArrayList<HandleUserThread>();
    private InputOutputStreams ioStreams = new InputOutputStreams();

    public void listen() throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                try {
                    outputStreams.transmitMessage("Server is closing");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Closing threads!");
                stopAllThreads();
                System.out.println("Shutdown hook completed.");
            }
        });

        try {
            while (true) {
                Socket userSocket = chatServer.accept();
                PrintWriter userOutputStream = new PrintWriter(userSocket.getOutputStream(), true);
                outputStreams.registerOutputStream(userOutputStream);
                HandleUserThread userThread = new HandleUserThread(userSocket, outputStreams);
                allThreads.add(userThread);
                userThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Test");
        } finally {
            chatServer.close();
        }
    }

    private void stopAllThreads() {
        synchronized (allThreads) {
            for (HandleUserThread thread : allThreads) {
                thread.shutdown();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




