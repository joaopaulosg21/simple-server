package projeto.server.interfaces;

import projeto.server.pojos.Response;
import projeto.server.pojos.Request;


@FunctionalInterface
public interface RouteRunner {
    Response execute(Request request);
}
