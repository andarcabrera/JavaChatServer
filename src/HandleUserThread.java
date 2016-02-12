import MathWiz.MathProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUserThread extends Thread {
    private Socket userSocket = null;
    private OutputStreamsMgmt outputStreams;
    private String name;
    private MathProtocol mathProtocol = new MathProtocol();

    public HandleUserThread(Socket socket, OutputStreamsMgmt outputStreams) {
        this.userSocket = socket;
        this.outputStreams = outputStreams;
    }

    public void run() {

        try (
                BufferedReader input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
                PrintWriter output = new PrintWriter(userSocket.getOutputStream());
        ) {

            String welcome = ("Welcome to the chatroom. Please enter a username");
            output.println(welcome);
            output.flush();

            while (true) {
                String userName = "Please enter a user name! Pretty please...";
                name = input.readLine();
                if (name == null || name.isEmpty()) {
                    output.println(userName);
                    output.flush();
                } else if (name.contains("lex")) {
                    output.println("Romanians need to go through extra security\n JK");
                    output.flush();
                    break;
                } else {
                    break;
                }
            }

            outputStreams.transmitMessage(name + " has joined the chat.");

            String messageFromUser;
            while ((messageFromUser = input.readLine()) != null) {
                outputStreams.transmitMessage(name + ": " + messageFromUser);
                if (messageFromUser.startsWith("MATH")) {
                    String resolvedEquation = mathProtocol.process(messageFromUser);
                    outputStreams.transmitMessage("MATH-WIZ-GUESS: " + resolvedEquation);
                }
            }

            userSocket.close();
            outputStreams.transmitMessage(name + " left the chat!");
            outputStreams.unregisterOutputStream(output);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

