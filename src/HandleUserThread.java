import MathWiz.MathProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUserThread extends Thread {
    private Socket userSocket = null;
    private OutputStreamsMgmt outputStreams;
    private Bots bots = new Bots();
    private String name;
    private boolean running = true;

    public HandleUserThread(Socket socket, OutputStreamsMgmt outputStreams) {
        this.userSocket = socket;
        this.outputStreams = outputStreams;
    }

    public void run() {

        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
                PrintWriter output = new PrintWriter(userSocket.getOutputStream());
        ) {

            while (!this.isInterrupted()) {
                String welcome = ("Welcome to the chatroom. Please enter a username");
                output.println(welcome);
                output.flush();
                String userName = "Please enter a user name! Pretty please...";
                name = input.readLine();
                if (name == null || name.isEmpty()) {
                    output.println(userName);
                    output.flush();
                } else if (name.contains("lex")) {
                    output.println("Romanians need to go through extra security\n JK");
                    output.flush();
                    outputStreams.transmitMessage(name + " has joined the chat.");
                    break;
                } else {
                    outputStreams.transmitMessage(name + " has joined the chat.");
                    break;
                }
            }

            String messageFromUser;
            while (!this.isInterrupted() && ((messageFromUser = input.readLine())) != null) {
                outputStreams.transmitMessage(name + ": " + messageFromUser);
                String messageFromServer = bots.handleRequest(messageFromUser);
                if (messageFromServer != null) {
                    outputStreams.transmitMessage(messageFromServer);
                }
            }

            outputStreams.transmitMessage(name + " left the chat!");
            outputStreams.unregisterOutputStream(output);
        } catch (IOException e) {
            System.out.println("Catching interrupting exception!");
        } finally {
            try {
                userSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        try {
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

