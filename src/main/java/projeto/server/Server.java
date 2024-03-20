package projeto.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import projeto.server.enums.DefaultResponses;
import projeto.server.enums.HttpStatus;
import projeto.server.interfaces.RouteRunner;
import projeto.server.mapper.Mappers;
import projeto.server.pojos.PathMethod;
import projeto.server.pojos.Request;
import projeto.server.pojos.Response;

public class Server {

    private int port;

    private ServerSocket serverSocket;

    private Map<PathMethod, RouteRunner> routes;

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

            if (!Objects.isNull(response)) {
                OutputStream clientOutputStream = client.getOutputStream();
                response.getHeaders().put("Content-Length", String.valueOf(response.getBody().length()));
                clientOutputStream
                        .write(Mappers.mapHeadersToStringResponse(response.getHeaders(), response.getStatus())
                                .getBytes());
                clientOutputStream.write(response.getBody().getBytes());
            }
            client.close();
        }
    }

    public void addNewRoute(String path, String method, RouteRunner routeRunner) {
        PathMethod pathMethod = new PathMethod();
        pathMethod.setPath(path);
        pathMethod.setMethod(method);
        routes.put(pathMethod, routeRunner);
    }

    private Response handleRequest(Request request) {
        String path = request.getHeaders().get("path");
        String method = request.getHeaders().get("method");
        PathMethod pathMethod = new PathMethod();
        pathMethod.setPath(path);
        pathMethod.setMethod(method);

        for (var route : routes.keySet()) {
            if (route.equals(pathMethod)) {
                return routes.get(route).execute(request);
            }
        }

        DefaultResponses body = DefaultResponses.ROUTE_NOT_FOUND;
        var headers = request.getHeaders();
        headers.put("Content-Type", "text/HTML");
        return new Response(headers, body.toString(), HttpStatus.METHOD_NOT_ALLOWED);
    }
}
