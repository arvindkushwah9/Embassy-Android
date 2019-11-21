package com.example.amal.esa.ui.profile;

import java.util.List;

public class Profile {
    @Override
    public String toString() {
        return "Profile{" +
                "id=" + id +
                ", password='" + password + '\'' +
                ", lastLogin='" + lastLogin + '\'' +
                ", isSuperuser=" + isSuperuser +
                ", username='" + username + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", isStaff=" + isStaff +
                ", isActive=" + isActive +
                ", dateJoined='" + dateJoined + '\'' +
                ", groups=" + groups +
                ", userPermissions=" + userPermissions +
                '}';
    }

    public Integer id;
public String password;
public String lastLogin;
public Boolean isSuperuser;
public String username;
public String firstName;
public String lastName;
public String email;
public Boolean isStaff;
public Boolean isActive;
public String dateJoined;
public List<Object> groups = null;
public List<Object> userPermissions = null;

}