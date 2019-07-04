package com.tryout.yamlMapper;

import java.util.Map;

public class FooBar {
    private String foo;
    private String bar;
    private Map<String, String> address;
    private String[] roles;
    private Map<String, String> f1;
    private Map<String, String> b1;

    public void setF1(Map<String, String> f1) {
        this.f1 = f1;
    }

    public void setB1(Map<String, String> b1) {
        this.b1 = b1;
    }

    public Map<String, String> getF1() {
        return f1;
    }

    public Map<String, String> getB1() {
        return b1;
    }

    public void setRoles(String[] roles) {
        this.roles = roles;
    }

    public String[] getRoles() {
        return roles;
    }

    public void setAddress(Map<String, String> address) {
        this.address = address;
    }

    public Map<String, String> getAddress() {
        return address;
    }

    public void setFoo(String foo) {
        this.foo = foo;
    }

    public void setBar(String bar) {
        this.bar = bar;
    }

    public String getFoo() {
        return foo;
    }

    public String getBar() {
        return bar;
    }
}
