import TestingMocks.InputForTesting;
import TestingMocks.OutputForTesting;
import TestingMocks.OutputStreamsMock;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HandleUserThreadTest {
    private Thread userThread;
    InputForTesting input = new InputForTesting();
    OutputForTesting output = new OutputForTesting();
    OutputStreamsMock outputStreams = new OutputStreamsMock();

    @Before
    public void setUp() throws Exception {
        userThread = new Thread(new HandleUserThread(input, output, outputStreams));
        userThread.start();
        System.out.println(Thread.currentThread().getName());
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void checkWelcomeMessage() {
        input.addInputs("");
        assertEquals("Welcome to the chatroom. Please enter a username", output.revealOutputStream(0));
    }

    @Test
    public void reenterUserNameMessage() {
        input.addInputs("", "", "");
        assertEquals("Please enter a user name! Pretty please...", output.revealOutputStream(1));
    }

    @Test
    public void readMessage() {
        input.addInputs(" ", "Anda");
        System.out.println(output.length());
        assertEquals("Anda", output.revealOutputStream(2));
    }
//
//    @Test
//    public void transmitName() {
//        inputBuffer.append("\n Anda\n Anda");
//        assertEquals("Anda", outputBuffer.substring(0));
//    }
}

