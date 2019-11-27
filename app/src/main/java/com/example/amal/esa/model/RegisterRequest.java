package com.example.amal.esa.model;

public class RegisterRequest {
    public String username;
    public String first_name;
    public String last_name;
    public String email;
    public String password;
    public String password_confirm;
    public String passport_number;
    public String phone_number;

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", password_confirm='" + password_confirm + '\'' +
                '}';
    }
}
