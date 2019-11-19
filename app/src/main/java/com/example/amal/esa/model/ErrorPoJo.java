package com.example.amal.esa.model;

import java.util.List;

public class ErrorPoJo {
    public List<String> email = null;
    public List<String> password = null;
    public List<String>username=null;
    public List<String> first_name = null;
    public List<String> last_name = null;
    public List<String> password_confirm = null;

    @Override
    public String toString() {
        return "ErrorPoJo{" +
                "email=" + email +
                ", password=" + password +
                ", username=" + username +
                ", first_name=" + first_name +
                ", last_name=" + last_name +
                ", password_confirm=" + password_confirm +
                '}';
    }
}
