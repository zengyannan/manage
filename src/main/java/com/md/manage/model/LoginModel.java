package com.md.manage.model;

import org.hibernate.validator.constraints.NotBlank;

public class LoginModel {

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    @NotBlank
    private String loginType;


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

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }
}
