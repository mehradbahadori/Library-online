package ir.mehrad.library.models;

public class Response {
    public String message;
 public String token;
    public Response(String message) {
        this.message = message;
    }

    public Response(String message, String token) {
        this.message = message;
        this.token = token;
    }
}
