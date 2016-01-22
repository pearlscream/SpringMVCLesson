package com.budko.springmvc.beans;

import org.springframework.stereotype.Component;

import javax.validation.OverridesAttribute;
import javax.validation.constraints.Size;


/**
 * Created by dimon on 17.01.2016.
 */

public class User extends Exception {

    @Size(min = 1, message = "{name.size.error}")
    private String name;
    @Size(min = 1,max = 10, message = "{password.size.error}")
    private String password;
    private boolean isAdministrator;

    public boolean isAdministrator() {
        return isAdministrator;
    }

    public User() {

    }

    public User(String name) {
        this.name = name;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
