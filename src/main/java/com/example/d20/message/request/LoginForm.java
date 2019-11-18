package com.example.d20.message.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class LoginForm {
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 4)
    private String password;
    
    public LoginForm(String email, String password) {
    	this.email = email;
    	this.password = password;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}