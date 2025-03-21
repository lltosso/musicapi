package com.example.musicApi.entity;

public class Music {
    private String user;
    private String role;

    public Music() {
    }

    public Music(String user, String role) {
        this.user = user;
        this.role = role;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
