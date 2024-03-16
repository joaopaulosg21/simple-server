package projeto.server.enums;

public enum HttpStatus {
    OK("200 OK"),
    NOT_FOUND("404 NOT FOUND"),
    CREATED("201 CREATED"),
    BAD_REQUEST("400 BAD REQUEST"),
    UNAUTHORIZED("401 UNAUTHORIZED"),
    METHOD_NOT_ALLOWED("405 METHOD NOT ALLOWED"),
    INTERNAL_SERVER_ERROR("500 INTERNAL SERVER ERROR");

    String value;

    HttpStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
