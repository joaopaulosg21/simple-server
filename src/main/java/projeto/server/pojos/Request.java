package projeto.server.pojos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Request {

    private Map<String, String> headers;

    private String body;

    private List<String> pathVariables;

    public Request(Map<String, String> headers, String body) {
        this.headers = headers;
        this.body = body;
        this.pathVariables = new ArrayList<>();
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public List<String> getPathVariables() {
        return pathVariables;
    }

    public void setPathVariables(List<String> pathVariables) {
        this.pathVariables = pathVariables;
    }

}
