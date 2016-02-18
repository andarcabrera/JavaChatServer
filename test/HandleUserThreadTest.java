import Interfaces.InputStream;
import Interfaces.OutputStream;

import TestingMocks.InputForTesting;
import TestingMocks.OutputForTesting;
import TestingMocks.OutputStreamsMock;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;


public class HandleUserThreadTest {
    private HandleUserThread userThread;
    StringBuffer inputBuffer = new StringBuffer();
    StringBuffer outputBuffer = new StringBuffer();

    InputStream input = new InputForTesting(inputBuffer);
    OutputStream output = new OutputForTesting(outputBuffer);
    OutputStreamsMock outputStreams = new OutputStreamsMock();

    @Before
    public void setUp() throws Exception {
        userThread = new HandleUserThread(input, output, outputStreams);
        inputBuffer.append("Anda");
        userThread.start();
    }

    @Test
    public void checkWelcomeMessage() {
        assertEquals("Welcome to the chatroom. Please enter a username", outputBuffer.substring(0));
    }
}
