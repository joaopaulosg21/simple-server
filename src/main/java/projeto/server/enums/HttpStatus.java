package projeto.server.enums;

public enum HttpStatus {
    OK("200 OK");

    String value;
    HttpStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
