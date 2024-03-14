package projeto.server.pojos;

public class Headers {

    private String httpMethod;

    private String host;

    private String contentType;

    private String userAgent;

    private String accept;

    private String contentLength;

    public Headers(String httpMethod, String host, String contentType, String userAgent, String accept,
            String contentLength) {
        this.httpMethod = httpMethod;
        this.host = host;
        this.contentType = contentType;
        this.userAgent = userAgent;
        this.accept = accept;
        this.contentLength = contentLength;
    }

    public Headers() {
    }

    public String getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    public String getAccept() {
        return accept;
    }

    public void setAccept(String accept) {
        this.accept = accept;
    }

    public String getContentLength() {
        return contentLength;
    }

    public void setContentLength(String contentLength) {
        this.contentLength = contentLength;
    }

}
