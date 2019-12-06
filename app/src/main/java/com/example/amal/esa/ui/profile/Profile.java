package com.example.amal.esa.ui.profile;

import java.util.List;

public class Profile {
    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", isSuperuser=" + is_superuser +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", isStaff=" + is_staff +
                ", isActive=" + is_active +
                ", dateJoined='" + dateJoined + '\'' +
                ", groups=" + groups +
                ", userPermissions=" + userPermissions +
                '}';
    }

    public Integer id;
public String password;
public String lastLogin;
public Boolean is_superuser;
public String username;
public String firstName;
public String lastName;
public String email;
public Boolean is_staff;
public Boolean is_active;
public String dateJoined;
public List<Object> groups = null;
public List<Object> userPermissions = null;

}