package projeto.server.pojos;

import java.util.Map;

import projeto.server.enums.HttpStatus;

public class Response {

    private Map<String, String> headers;

    private String body;

    private HttpStatus status;

    public Response(Map<String, String> headers, String body, HttpStatus status) {
        this.headers = headers;
        this.body = body;
        this.status = status;
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

}
