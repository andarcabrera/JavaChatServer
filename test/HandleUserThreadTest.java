import TestingMocks.ClientSocketMock;
import TestingMocks.OutputStreamsMock;
import org.junit.Before;
import org.junit.Test;

public class HandleUserThreadTest {
    private HandleUserThread userThread;

    @Before
    public void setUp() throws Exception {
        ClientSocketMock socket = new ClientSocketMock("127.0.0.1", 6000);
        OutputStreamsMock outputStreams = new OutputStreamsMock();
        userThread = new HandleUserThread(socket, outputStreams);
    }

    @Test
    public void extractNumbersTest() {
//        assertEquals(toArrayList("1", "1"), sm.extractNumbers("MATH:1+1"));
    }
}
