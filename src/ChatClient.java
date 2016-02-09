import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by andacabrera29 on 2/9/16.
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket chatClient = new Socket("127.0.0.1", 7002);

        BufferedReader input = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
        PrintWriter output = new PrintWriter(chatClient.getOutputStream(), true);
        BufferedReader stdin = new BufferedReader(new InputStreamReader(System.in));

        String messageFromUser;
        while ((messageFromUser = stdin.readLine()) != null) {
            output.println(messageFromUser);
            System.out.println(input.readLine());
        }
    }
}


