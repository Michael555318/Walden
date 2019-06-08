package com.example.walden;

public class MainSetting {

    private boolean on;
    private String name;

    public MainSetting() {}

    public MainSetting(boolean on, String name) {
        this.name = name;
        this.on = on;
    }

    public String getName() {
        return name;
    }

    public void setName(String n) {
        name = n;
    }

    public boolean getOn() {
        return on;
    }

    public void setOn(boolean o) {
        on = o;
    }





}
