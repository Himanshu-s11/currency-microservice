package com.microservice.learning.auth_service.dto;

public class UserCredentialsDto {
    private String name;
    private String password;


    public UserCredentialsDto () {

    }

    public UserCredentialsDto(String name, String password) {
        this.name = name;
        this.password = password;
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
