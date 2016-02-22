package TestingMocks;

import Interfaces.InputStream;

import java.util.ArrayList;
import java.util.Arrays;


public class InputForTesting implements InputStream {
    ArrayList<String> incomingMessages = new ArrayList<>();

    public synchronized String readMessage() {
        String message;
        if (incomingMessages.size() == 1) {
            message = incomingMessages.get(0);
            incomingMessages.remove(0);
        } else {
            message = "";
        }
        return message;
    }

    public synchronized void emptyStream() {
        if (!incomingMessages.isEmpty()) {
            incomingMessages.clear();
        }
    }

    public synchronized void addInputs(String... a) {
        incomingMessages.addAll(Arrays.asList(a));
    }
}
