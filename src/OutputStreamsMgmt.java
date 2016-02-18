import Interfaces.OutputStream;
import Interfaces.StreamMgmt;

import java.io.IOException;
import java.io.PrintWriter;

public class OutputStreamsMgmt implements StreamMgmt {
    public void registerOutputStream(PrintWriter newOutputStream) {
        activeOutputStreams.add(newOutputStream);
    }

    public void unregisterOutputStream(OutputStream closedOutputStream) {
        activeOutputStreams.remove(closedOutputStream);
    }

    public void transmitMessage(String message) throws IOException {
        synchronized (activeOutputStreams) {
            for (PrintWriter currentOutputStream : activeOutputStreams) {
                currentOutputStream.println(message);
                currentOutputStream.flush();
            }
        }
    }
}
