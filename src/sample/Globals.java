package sample;

public class Globals {
    public static final String url;
    public static final String databClassforName;
    public static final String username;
    public static final String password;

    static {
        url = "jdbc:mysql://localhost:3306/glbank?serverTimezone=UTC";
        databClassforName = "com.mysql.cj.jdbc.Driver";
        username = "root";
        password = "";

    }
}
