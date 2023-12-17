package fer.project.response;

import fer.project.author.Author;

import java.util.List;

public class Response {
    private Integer status;
    private String message;
    private Author[] response;

    public Response(Integer status, String message, List<Author> response) {
        this.status = status;
        this.message = new String(message);
        if(response == null) {
            this.response = null;
        } else {
            this.response = response.toArray(new Author[response.size()]);
        }
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public Author[] getResponse() {
        return response;
    }
}
