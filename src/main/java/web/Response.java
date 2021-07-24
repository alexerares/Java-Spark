package web;

public class Response {
    private String message;
    private String error;
    private int counter;

    public Response(String message, String error, int counter) {
        this.message = message;
        this.error = error;
        this.counter = counter;
    }

    public String getMessage() {
        return message;
    }

    public String getError() {
        return error;
    }

    public int getCounter() {
        return counter;
    }

}
