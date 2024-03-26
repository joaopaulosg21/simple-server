package projeto.server.pojos;

import java.util.ArrayList;
import java.util.List;

public class PathMethodParameter {
    private String path;

    private String routePath;

    public PathMethodParameter(String path) {
        this.path = path;
    }

    public void setRoutePath(String routePath) {
        this.routePath = routePath;
    }

    public boolean matchesPathVariable(String path) {
        // serverPath é o caminho da rota do server, e o
        // requestPath é o caminho da requisição que ta sendo feita

        this.setRoutePath(path);
        String[] serverPath = path.split("/");
        String[] requestPath = this.path.split("/");
        if (serverPath.length != requestPath.length) {
            return false;
        }

        for (int i = 0; i < serverPath.length; i++) {
            if (!requestPath[i].equals(serverPath[i]) && !serverPath[i].startsWith("{")) {
                return false;
            }
        }

        return true;
    }

    public List<String> getParameters() {
        String[] serverPath = this.routePath.split("/");
        String[] requestPath = this.path.split("/");

        List<String> variables = new ArrayList<>();

        for (int i = 0; i < serverPath.length; i++) {
            if (serverPath[i].startsWith("{")) {
                variables.add(requestPath[i]);
            }
        }

        return variables;
    }
}
