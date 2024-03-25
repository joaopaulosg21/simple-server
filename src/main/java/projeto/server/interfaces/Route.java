package projeto.server.interfaces;

import projeto.server.pojos.PathMethod;

public abstract class Route {

    private PathMethod pathMethod;

    public Route(PathMethod pathMethod) {
        this.pathMethod = pathMethod;
    }

    public Route(){}

    public void setPathMethod(PathMethod pathMethod) {
        this.pathMethod = pathMethod;
    }

    public PathMethod getPathMethod() {
        return this.pathMethod;
    }

    public abstract RouteRunner getRouteRunner();
}
