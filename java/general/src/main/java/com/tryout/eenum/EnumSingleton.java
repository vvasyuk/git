package com.tryout.eenum;

public enum EnumSingleton {
    INSTANCE;

    EnumSingleton(int value) {
        this.value = value;
    }

    EnumSingleton() {
    }

    int value;

    public int getValue() {
        return value;
    }
    public void setValue(int value) {
        this.value = value;
    }
}
