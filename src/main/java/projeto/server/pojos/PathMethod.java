package projeto.server.pojos;

import java.util.HashMap;
import java.util.Map;

import projeto.server.enums.HttpMethod;

public class PathMethod {

    private String requestPath;

    private HttpMethod method;

    public PathMethod() {
    }

    public PathMethod(String requestPath, HttpMethod method) {
        this.requestPath = requestPath;
        this.method = method;
    }

    public PathMethod(String requestPath, String method) {
        this.requestPath = requestPath;
        this.setMethod(method);
    }

    public String getRequestPath() {
        return requestPath;
    }

    public void setRequestPath(String requestPath) {
        this.requestPath = requestPath;
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

        if (this.requestPath.equals(pathMethod.getRequestPath()) && this.method == pathMethod.getMethod()) {
            return true;
        }

        return false;
    }

    public Map<String, String> getQueryParams() {
        Map<String, String> params = new HashMap<>();
        if (this.requestPath.contains("?")) {
            String query = this.requestPath.split("\\?")[1];

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
        this.setRequestPath(this.requestPath.split("\\?")[0]);
        return params;
    }

    public boolean containsQueryParams() {
        return this.requestPath.contains("?");
    }

    public boolean matchesPathVariable(String serverPath, HttpMethod method) {
        // serverPath é o caminho da rota do server, e o
        // requestedPath é o caminho da requisição que ta sendo feita
        if (this.method != method) {
            return false;
        }
        String[] serverPathArr = serverPath.split("/");
        String[] requestPathArr = this.requestPath.split("/");
        if (serverPathArr.length != requestPathArr.length) {
            return false;
        }

        for (int i = 0; i < serverPathArr.length; i++) {
            if (!requestPathArr[i].equals(serverPathArr[i]) && !serverPathArr[i].startsWith("{")) {
                return false;
            }
        }

        return true;
    }

    public Map<String, String> getParameters(String serverPath) {
        String[] serverPathArr = serverPath.split("/");
        String[] requestPathArr = this.requestPath.split("/");

        Map<String, String> variables = new HashMap<>();

        for (int i = 0; i < serverPathArr.length; i++) {
            if (serverPathArr[i].startsWith("{")) {
                var variableName = serverPathArr[i].replace("{", "").replace("}", "");
                variables.put(variableName, requestPathArr[i]);
            }
        }

        return variables;
    }

    @Override
    public String toString() {
        return "[Path=" + requestPath + ", method=" + method + "]";
    }

}
