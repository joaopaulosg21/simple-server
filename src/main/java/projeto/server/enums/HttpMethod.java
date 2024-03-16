package projeto.server.enums;

public enum HttpMethod {
    GET,POST,PUT,PATCH,DELETE;

    public boolean equals(String object) {
        if (object == null) {
            return false;
        }

        return this.name().equals(object);
    }
}
