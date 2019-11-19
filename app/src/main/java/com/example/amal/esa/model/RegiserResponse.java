package com.example.amal.esa.model;

public class RegiserResponse {
    public int id;
    public String username;
    public  String first_name;
    public  String last_name;
    public  String email;

    @Override
    public String toString() {
        return "RegiserResponse{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
/*"id": 8,
            "username": "kapil",
            "first_name": "kapil",
            "last_name": "goyal",
            "email": "kapilgoyal6@gmail.com"*/

}
