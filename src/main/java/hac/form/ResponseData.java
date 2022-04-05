package hac.form;

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
