package fer.project.response;

import fer.project.author.Author;

public class ResponseDocs {
    private Integer status;
    private String message;
    private String response;

    public ResponseDocs(Integer status, String message, String response) {
        this.status = status;
        this.message = message;
        this.response = response;
    }

    public Integer getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public String getResponse() {
        return response;
    }
}
