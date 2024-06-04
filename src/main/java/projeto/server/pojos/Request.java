package projeto.server.pojos;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private Map<String, String> headers;

    private String body;

    private Map<String, String> pathVariables;

    private Map<String, String> queryParams;

    public Request(Map<String, String> headers, String body) {
        this.headers = headers;
        this.body = body;
        this.pathVariables = new HashMap<>();
        this.queryParams = new HashMap<>();
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

    public Map<String, String> getPathVariables() {
        return pathVariables;
    }

    public void setPathVariables(Map<String, String> pathVariables) {
        this.pathVariables = pathVariables;
    }

    public Map<String, String> getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(Map<String, String> queryParams) {
        this.queryParams = queryParams;
    }

}
