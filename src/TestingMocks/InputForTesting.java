package TestingMocks;

import Interfaces.InputStream;


public class InputForTesting implements InputStream {
    StringBuffer incomingMessages;

    public InputForTesting(StringBuffer strbuffer) {
        this.incomingMessages = strbuffer;
    }

    public String readMessage() {
        String message = incomingMessages.toString();
        return message;
    }
}
