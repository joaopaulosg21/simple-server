package projeto.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import projeto.server.enums.DefaultResponses;
import projeto.server.enums.HttpMethod;
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
        if (!this.isValidMethod(method)) {
            throw new RuntimeException("Método HTTP invalido");
        }
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
            }
        }
        DefaultResponses body = DefaultResponses.ROUTE_NOT_FOUND;
        var headers = request.getHeaders();
        headers.put("Content-Type","text/HTML");
        return new Response(headers, body.toString(), HttpStatus.METHOD_NOT_ALLOWED);
    }

    private boolean isValidMethod(String method) {
        return HttpMethod.GET.equals(method) || HttpMethod.POST.equals(method)
                || HttpMethod.PUT.equals(method) || HttpMethod.PATCH.equals(method)
                || HttpMethod.DELETE.equals(method);
    }
}
