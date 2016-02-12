import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class ChatClient {
    JTextField textField = new JTextField(40);
    JTextArea messageArea = new JTextArea(8, 40);
    JFrame frame = new JFrame("Anda's Chat");
    BufferedReader input;
    PrintWriter output;

    public ChatClient() throws IOException {
        frame.getContentPane().add(textField, "North");
        frame.getContentPane().add(new JScrollPane(messageArea), "Center");
        frame.pack();

        textField.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                output.println(textField.getText());
                textField.setText("");
            }
        });
    }

    public void run() throws IOException {

        Socket chatClient = new Socket("127.0.0.1", 7002);
        input = new BufferedReader(new InputStreamReader(chatClient.getInputStream()));
        output = new PrintWriter(chatClient.getOutputStream(), true);


        String messageFromServer;
        while ((messageFromServer = input.readLine()) != null) {
            messageArea.append(messageFromServer + "\n");
        }

        messageArea.append("Lost connection to the server!");
        chatClient.close();
    }
}


