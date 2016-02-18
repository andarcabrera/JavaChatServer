import Interfaces.InputStream;
import Interfaces.OutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Enumeration;
import java.util.Hashtable;


public class ChatServer {
    private OutputStreamsMgmt outputStreams = new OutputStreamsMgmt();
    private Hashtable<HandleUserThread, Socket> allThreads = new Hashtable<HandleUserThread, Socket>();
    private InputOutputStreams ioStreams = new InputOutputStreams();

    public void listen() throws IOException {
        ServerSocket chatServer = new ServerSocket(7002);

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                System.out.println("Shut down hook started to close all threads.");
                try {
                    outputStreams.transmitMessage("Server is closing");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                stopAllThreads();
                System.out.println("Doug - Shutdown hook completed :).");
            }
        });

        try {
            while (true) {
                Socket userSocket = chatServer.accept();
                PrintWriter userOutputStream = new PrintWriter(userSocket.getOutputStream(), true);

                BufferedReader inputReader = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
                InputStream userInputStream = new ClientSocketInputStream(inputReader);

                OutputStream outputStream = new ClientSocketOutputStream(userOutputStream);

                outputStreams.registerOutputStream(userOutputStream);
                HandleUserThread userThread = new HandleUserThread(userInputStream, outputStream, outputStreams);
                allThreads.put(userThread, userSocket);
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
            Enumeration<HandleUserThread> enumKey = allThreads.keys();
            while (enumKey.hasMoreElements()) {
                HandleUserThread thread = enumKey.nextElement();
                Socket socket = allThreads.get(thread);
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                thread.interrupt();
                try {
                    thread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




