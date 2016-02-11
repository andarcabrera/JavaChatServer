import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUser extends Thread {
    private Socket userSocket = null;
    private ThreadsMgmt allthreads;

    public HandleUser(Socket socket, ThreadsMgmt allThreads) {
        this.userSocket = socket;
        this.allthreads = allThreads;
        start();
    }

    public void run() {

        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
                PrintWriter output = new PrintWriter(userSocket.getOutputStream());
        ) {
            while (true) {
                allthreads.registerThread(output);
                String messageFromUser = input.readLine();
                allthreads.transmitMessage(messageFromUser);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                userSocket.close();
                PrintWriter output = new PrintWriter(userSocket.getOutputStream());
                allthreads.unregisterThread(output);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
