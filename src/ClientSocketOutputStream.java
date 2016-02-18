import Interfaces.OutputStream;

import java.io.PrintWriter;

/**
 * Created by andacabrera29 on 2/18/16.
 */
public class ClientSocketOutputStream implements OutputStream {
    PrintWriter output = null;

    public ClientSocketOutputStream(PrintWriter output) {
        this.output = output;
    }

    public void writeMessage(String message) {
        output.println(message);
        output.flush();
    }
}
