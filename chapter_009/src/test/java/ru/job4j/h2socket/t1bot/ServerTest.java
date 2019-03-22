package ru.job4j.h2socket.t1bot;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Rav, date: 21.03.2019
 * @version 1.0
 */
public class ServerTest {
    /**
     * Разделитель строк.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * @throws IOException искл.
     */
    @Test
    public void whenClientSaysExitThenGetAnswerFromServer() throws IOException {
        this.testServer(
                "exit",
                String.format("OK, good buy!%s%s", LN, LN)
        );
    }

    /**
     * @throws IOException искл.
     */
    @Test
    public void whenClientSaysSomethingThenGetAnswerFromServer() throws IOException {
        this.testServer(
                String.join(LN, "hello", "exit"),
                String.format("Hello, dear friend, I'm Oracle!%s%sOK, good buy!%s%s", LN, LN, LN, LN)
        );
    }

    /**
     * @param input строка с вводимым значением.
     * @param expected ожидаемый результат.
     * @throws IOException искл.
     */
    private void testServer(String input, String expected) throws IOException {
        Socket socket = mock(Socket.class);
        InputStream in = new ByteArrayInputStream(input.getBytes());
        OutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Server server = new Server(socket);
        server.start();
        assertThat(out.toString(), is(expected));
    }
}