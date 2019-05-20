package com.example.parkinglot.beans.request.dto;

import lombok.Data;

@Data
public class LoginRequest {

    public final static String _username="username";
    public final static String _password="password";
    public final static String _usernameNullMessage="username is required";
    public final static String _passwordNullMessage="password is required";

    private String username;
    private String password;

}
