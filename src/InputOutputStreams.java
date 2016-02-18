import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by andacabrera29 on 2/17/16.
 */
public class InputOutputStreams {

    public BufferedReader getInput(Socket socket) throws IOException {
        return new BufferedReader(new InputStreamReader(socket.getInputStream()));
    }

    public PrintWriter getOutput(Socket socket) throws IOException {
        return new PrintWriter(socket.getOutputStream());
    }
}
