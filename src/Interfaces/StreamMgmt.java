package Interfaces;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;

public interface StreamMgmt {
    HashSet<PrintWriter> activeOutputStreams = new HashSet<>();

    public void registerOutputStream(PrintWriter newOutputStream);

    public void unregisterOutputStream(OutputStream closedOutputStream);

    public void transmitMessage(String message) throws IOException;
}
