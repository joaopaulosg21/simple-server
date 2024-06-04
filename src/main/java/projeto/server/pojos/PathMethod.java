package projeto.server.pojos;

import java.util.HashMap;
import java.util.Map;

import projeto.server.enums.HttpMethod;

public class PathMethod {

    private String path;

    private HttpMethod method;

    public PathMethod() {
    }

    public PathMethod(String path, HttpMethod method) {
        this.path = path;
        this.method = method;
    }

    public PathMethod(String path, String method) {
        this.path = path;
        this.setMethod(method);
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public void setMethod(String method) {
        if (!this.isValidMethod(method)) {
            throw new RuntimeException("Método HTTP invalido");
        }

        switch (method) {
            case "GET":
                this.method = HttpMethod.GET;
                break;
            case "POST":
                this.method = HttpMethod.POST;
                break;
            case "PUT":
                this.method = HttpMethod.PUT;
                break;
            case "PATCH":
                this.method = HttpMethod.PATCH;
                break;
            case "DELETE":
                this.method = HttpMethod.DELETE;
                break;
        }
    }

    private boolean isValidMethod(String method) {
        return HttpMethod.GET.equals(method) || HttpMethod.POST.equals(method)
                || HttpMethod.PUT.equals(method) || HttpMethod.PATCH.equals(method)
                || HttpMethod.DELETE.equals(method);
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;

        PathMethod pathMethod = (PathMethod) o;

        if (this.path.equals(pathMethod.getPath()) && this.method == pathMethod.getMethod()) {
            return true;
        }

        return false;
    }

    public Map<String, String> getQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (this.path.contains("?")) {
            String query = this.path.split("\\?")[1];

            if (query.contains("&")) {
                for (String param : query.split("&")) {
                    String[] arr = param.split("=");
                    params.put(arr[0], arr[1]);
                }
            } else {
                String[] arr = query.split("=");
                params.put(arr[0], arr[1]);
            }
        }
        this.setPath(this.path.split("\\?")[0]);
        return params;
    }

    public boolean containsQueryParams() {
        return this.path.contains("?");
    }
}
