package projeto.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import projeto.server.enums.HttpStatus;
import projeto.server.interfaces.RouteRunner;
import projeto.server.mapper.Mappers;
import projeto.server.pojos.Request;
import projeto.server.pojos.Response;

public class Server {

    private int port;

    private ServerSocket serverSocket;

    private Map<Map<String, String>, RouteRunner> routes;

    public Server(int port) {
        this.port = port;
        this.routes = new HashMap<>();
    }

    public void start() throws IOException {

        serverSocket = new ServerSocket(this.port);
        System.out.println("Servidor iniciado na porta " + this.port);

        while (true) {
            Socket client = serverSocket.accept();

            Request request = Mappers.mapInputStreamToRequest(client.getInputStream());

            Response response = this.handleRequest(request);

            OutputStream clientOutputStream = client.getOutputStream();
            response.getHeaders().put("Content-Length", String.valueOf(response.getBody().length()));
            clientOutputStream
                    .write(Mappers.mapHeadersToStringResponse(response.getHeaders(), HttpStatus.OK).getBytes());
            clientOutputStream.write(response.getBody().getBytes());
            client.close();
        }
    }

    public void addNewRoute(String path, String method, RouteRunner routeRunner) {
        Map<String, String> pathMethod = new HashMap<>();
        pathMethod.put("path", path);
        pathMethod.put("method", method);
        routes.put(pathMethod, routeRunner);
    }

    private Response handleRequest(Request request) {
        String route = request.getHeaders().get("route");
        String method = request.getHeaders().get("method");

        for (Map<String, String> map : routes.keySet()) {
            if (map.get("path").equals(route) && map.get("method").equals(method)) {
                return routes.get(map).execute(request);
            } else {
                System.out.println("Deu errado");
            }
        }

        return null;
    }
}
