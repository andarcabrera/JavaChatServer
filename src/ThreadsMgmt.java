import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;


public class ThreadsMgmt {
    private HashSet<PrintWriter> activeThreads = new HashSet<>();

    public void registerThread(PrintWriter newThread) {
        activeThreads.add(newThread);
    }

    public void unregisterThread(PrintWriter closedThread) {
        activeThreads.remove(closedThread);
    }

    public void transmitMessage(String message) throws IOException {
        synchronized (activeThreads) {
            for (PrintWriter activeThread : activeThreads) {
                activeThread.println(message);
            }
        }
    }
}
