package TestingMocks;

import Interfaces.StreamMgmt;

import java.io.IOException;
import java.io.PrintWriter;


public class OutputStreamsMock implements StreamMgmt {
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
                currentOutputStream.flush();
            }
        }
    }
}
