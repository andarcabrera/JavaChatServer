import javax.swing.*;
import java.io.IOException;


public class StartClient {
    public static void main(String[] args) throws IOException {
        ChatClient client = new ChatClient();
        client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        client.frame.setVisible(true);
        client.run();
    }
}
