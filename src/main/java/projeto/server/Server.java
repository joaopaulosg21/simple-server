package projeto.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import projeto.server.mapper.Mappers;
import projeto.server.pojos.Request;

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

            Request request = Mappers.mapInputStreamToRequest(client.getInputStream());
            System.out.println(request.getHeaders().get("route"));
            client.close();
        }
    }
}
