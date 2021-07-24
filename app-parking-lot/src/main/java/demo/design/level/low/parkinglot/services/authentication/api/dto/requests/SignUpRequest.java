package demo.design.level.low.parkinglot.services.authentication.api.dto.requests;

import lombok.Data;

@Data
public class SignUpRequest {

    public static String _name="name";
    public static String _email="email";
    public final static String _username="username";
    public final static String _password="password";
    public static String _nameNullMessage="name is required";
    public static String _emailNullMessage="email is required";
    public final static String _usernameNullMessage="username is required";
    public final static String _passwordNullMessage="password is required";

    String name;
    String username;
    String password;
    String email;

}
