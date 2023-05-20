package org.tetrabox.example.minitl.runtime.lrp;

public enum PrimitiveType {
    BOOLEAN ("boolean"),
    STRING ("string"),
    NUMBER ("number");

    private String value;

    private PrimitiveType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
