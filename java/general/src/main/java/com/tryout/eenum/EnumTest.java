package com.tryout.eenum;

public enum EnumTest {
    // enum fields
    FOO("f") {
        @Override
        public String print() {
            String message = "Foo:" + getAbreviation();
            return message;
        }
    },
    BAR("b") {
        @Override
        public String print() {
            String message = "Bar:" + getAbreviation();
            return message;
        }
    };

    private EnumTest(String abreviation) {
        this.abreviation = abreviation;
    }

    // internal state
    private String abreviation;

    public String getAbreviation() {
        return abreviation;
    }

    public abstract String print();
}
