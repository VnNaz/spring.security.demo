package com.example.spring.security.demo.enums;

public enum Authority {
    EMPLOYEE("EMPLOYEE"), MANAGER("MANAGER"), ADMIN("ADMIN");

    Authority(String name) {
        this.value = name;
    }
    private final String value;
    public String getValue(){
        return value;
    }
}
