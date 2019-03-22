package ru.job4j.h2socket.t1bot;

import org.junit.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * @author Rav, date: 21.03.2019
 * @version 1.0
 */
public class ClientTest {
    /**
     * Разделитель строк.
     */
    private static final String LN = System.getProperty("line.separator");

    /**
     * @throws IOException искл.
     * @throws InterruptedException искл.
     */
    @Test
    public void whenClientChoosesExitThenJobIsDone() throws IOException, InterruptedException {
        Socket socket = mock(Socket.class);
        InputStream in = new ByteArrayInputStream(String.format("Hello, dear friend, I'm Oracle!%s%s", LN, LN).getBytes());
        OutputStream out = new ByteArrayOutputStream();
        when(socket.getInputStream()).thenReturn(in);
        when(socket.getOutputStream()).thenReturn(out);
        Client client = new Client(socket);
        client.start();
        assertThat(out.toString(), is(String.format("hello%sexit%s", LN, LN)));
    }
}