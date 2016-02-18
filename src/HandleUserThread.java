import Interfaces.InputStream;
import Interfaces.OutputStream;
import Interfaces.StreamMgmt;

import java.io.IOException;


public class HandleUserThread extends Thread {
    private InputStream input;
    private OutputStream output;
    private StreamMgmt outputStreams;
    private Bots bots = new Bots();
    private String name;

    public HandleUserThread(InputStream inputStream, OutputStream outputStream, StreamMgmt outputStreams) {
        this.input = inputStream;
        this.output = outputStream;
        this.outputStreams = outputStreams;
    }

    public String readMessage() {
        String message = input.readMessage();
        return message;
    }

    public void writeMessage(String message) {
        output.writeMessage(message);
    }

    public void transmitMessage(String message) {
        try {
            outputStreams.transmitMessage(message);
        } catch (IOException e1) {
            System.out.println("Cathing interrupted exception in transmitMessage");
        }
    }


    public void run() {

            while (true) {
                String welcome = ("Welcome to the chatroom. Please enter a username");
                writeMessage(welcome);

                String userName = "Please enter a user name! Pretty please...";
                name = readMessage();

                if (name == null || name.isEmpty()) {
                    writeMessage(userName);
                } else if (name.contains("lex")) {
                    writeMessage("Romanians need to go through extra security\n JK");
                    transmitMessage(name + " has joined the chat.");
                    break;
                } else {
                    transmitMessage(name + " has joined the chat.");
                    break;
                }
            }

            String messageFromUser;
        while (((messageFromUser = readMessage())) != null) {
            transmitMessage(name + ": " + messageFromUser);
                String messageFromServer = bots.handleRequest(messageFromUser);
                if (messageFromServer != null) {
                    transmitMessage(messageFromServer);
                }

            }

        transmitMessage(name + " left the chat!");
            outputStreams.unregisterOutputStream(output);
    }
}

