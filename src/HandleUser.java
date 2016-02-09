import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class HandleUser extends Thread {
    private Socket userSocket = null;

    public HandleUser(Socket socket) {
        this.userSocket = socket;
    }

    public void run() {

        try (
                PrintWriter output = new PrintWriter(userSocket.getOutputStream(), true);
                BufferedReader input = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
        ) {

            String messageFromUser;
            while ((messageFromUser = input.readLine()) != null) {
                output.println(messageFromUser);
            }
            userSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
