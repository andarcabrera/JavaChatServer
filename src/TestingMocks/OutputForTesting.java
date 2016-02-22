package TestingMocks;

import Interfaces.OutputStream;

import java.util.ArrayList;


public class OutputForTesting implements OutputStream {
    private ArrayList<String> messagesFromServer = new ArrayList<>();

    public void writeMessage(String message) {
        messagesFromServer.add(message);
    }

    public synchronized String revealOutputStream(int position) {
        String message;
        if (messagesFromServer.size() >= 1) {
            message = messagesFromServer.get(position);
        } else {
            message = "Nothin'";
        }
        return message;
    }

    public synchronized void emptyStream() {
        if (!messagesFromServer.isEmpty()) {
            messagesFromServer.clear();
        }
    }

    public synchronized int length() {
        return messagesFromServer.size();
    }
}
