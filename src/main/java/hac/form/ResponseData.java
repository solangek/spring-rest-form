package hac.form;

/**
 * we define a class that will be used to transfer specific responses from the server to the client
 */
public class ResponseData {

    private String text;

    public ResponseData(String text) {
        this.setText(text);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
