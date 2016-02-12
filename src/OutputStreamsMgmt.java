import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;


public class OutputStreamsMgmt {
    private HashSet<PrintWriter> activeOutputStreams = new HashSet<>();

    public void registerOutputStream(PrintWriter newOutputStream) {
        activeOutputStreams.add(newOutputStream);
    }

    public void unregisterOutputStream(PrintWriter closedOutputStream) {
        activeOutputStreams.remove(closedOutputStream);
    }

    public void transmitMessage(String message) throws IOException {
        synchronized (activeOutputStreams) {
            for (PrintWriter currentOutputStream : activeOutputStreams) {
                currentOutputStream.println(message);
            }
        }
    }
}
