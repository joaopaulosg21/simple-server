package projeto.server.enums;

public enum DefaultResponses {
    ROUTE_NOT_FOUND("<h1> Error </h1>\n" +
    "<p> Route not found</p>");

    private String value;

    DefaultResponses(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
