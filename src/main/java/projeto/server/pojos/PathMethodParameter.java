package projeto.server.pojos;

import java.util.ArrayList;
import java.util.List;

public class PathMethodParameter {
    private String requestedPath;

    private String serverPath;

    public PathMethodParameter(String requestedPath) {
        this.requestedPath = requestedPath;
    }

    public void setServerPath(String serverPath) {
        this.serverPath = serverPath;
    }

    public boolean matchesPathVariable(String path) {
        // serverPath é o caminho da rota do server, e o
        // requestedPath é o caminho da requisição que ta sendo feita

        this.setServerPath(path);
        String[] serverPathArr = this.serverPath.split("/");
        String[] requestPathArr = this.requestedPath.split("/");
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

    public List<String> getParameters() {
        String[] serverPathArr = this.serverPath.split("/");
        String[] requestPathArr = this.requestedPath.split("/");

        List<String> variables = new ArrayList<>();

        for (int i = 0; i < serverPathArr.length; i++) {
            if (serverPathArr[i].startsWith("{")) {
                variables.add(requestPathArr[i]);
            }
        }

        return variables;
    }
}
