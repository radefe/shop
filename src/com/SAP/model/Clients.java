package com.SAP.model;

import java.io.Serializable;

public class Clients implements Serializable {
    private final String name;
    private String email;

    public Clients(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
