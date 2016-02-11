import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUser extends Thread {
    private Socket userSocket = null;
    private ThreadsMgmt allthreads;
    private String name;

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
                String userName = "Please enter a user name! Pretty please...";
                name = input.readLine();
                if (name == null || name.isEmpty()) {
                    output.println(userName);
                } else if (name.contains("Alex")) {
                    output.println("boohoo");
                } else {
                    break;
                }
            }

            allthreads.transmitMessage(name + " has joined the chat.");


            String messageFromUser;
            while ((messageFromUser = input.readLine()) != null) {
                allthreads.transmitMessage(name + ": " + messageFromUser);
            }
            userSocket.close();
            allthreads.transmitMessage(name + " left the chat!");
            allthreads.unregisterThread(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

