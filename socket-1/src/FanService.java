import java.io.Serializable;

public class FanService implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private String message;
    private String content;

    public FanService() {
    }

    public FanService(String message, String content) {
        this.message = message;
        this.content = content;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FanService{message='" + message + "', content='" + content + "'}";
    }
}
