package TestingMocks;

import Interfaces.OutputStream;


public class OutputForTesting implements OutputStream {
    private StringBuffer messagesFromServer;

    public OutputForTesting(StringBuffer stringBuffer) {
        this.messagesFromServer = stringBuffer;
    }

    public void writeMessage(String message) {
        messagesFromServer.append(message);
    }


}
