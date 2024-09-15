package com.ar.gallerion.user;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

class UserModelDTO {
    @NotNull
    @Size(min = 5, max = 15)
    private String username;

    @NotNull
    @Size(min = 5, max = 15)
    private String password;

    @NotNull
    @Size(min = 5, max = 15)
    private String confirmPassword;

    @AssertTrue
    public boolean isMatchedPassword() {
        return password.compareTo(confirmPassword) == 0;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

}
