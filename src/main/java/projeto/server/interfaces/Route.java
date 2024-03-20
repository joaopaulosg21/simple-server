package projeto.server.interfaces;

import projeto.server.pojos.PathMethod;

public interface Route {

    void setPathMethod(PathMethod pathMethod);

    PathMethod getPathMethod();
    
    RouteRunner getRouteRunner();
}
