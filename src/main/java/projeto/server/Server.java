package projeto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import projeto.server.mapper.Mappers;

public class Server {

    private int port;

    private ServerSocket serverSocket;

    public Server(int port) {
        this.port = port;
    }

    public void start() throws IOException {

        serverSocket = new ServerSocket(this.port);
        System.out.println("Servidor iniciado na porta " + this.port);

        while (true) {
            Socket client = serverSocket.accept();

            String body = Mappers.mapInputStreamToString(client.getInputStream());
            System.out.println(body);
            client.close();
        }
    }
}
