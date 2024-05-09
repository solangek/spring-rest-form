package hac.form;

/**
 * this is our data aka the model (later replaced by a database)
 * Note that it matches the JSON data sent by the client
 */
public class LoginFormData {
    private String username;
    private String password;

    public LoginFormData() {
    }

    public LoginFormData(String username, String password) {
        super();
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "LoginFormData [username=" + username + ", password=" + password + "]";
    }
}
