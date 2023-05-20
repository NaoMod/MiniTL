package org.tetrabox.example.minitl.runtime.lrp;

public class BreakpointParameter {

    private String name;
    private boolean isMultivalued;
    private PrimitiveType primitiveType;
    private String objectType;
    
    public BreakpointParameter(String name, boolean isMultivalued, PrimitiveType primitiveType) {
        this.name = name;
        this.isMultivalued = isMultivalued;
        this.primitiveType = primitiveType;
    }

    public BreakpointParameter(String name, boolean isMultivalued, String objectType) {
        this.name = name;
        this.isMultivalued = isMultivalued;
        this.objectType = objectType;
    }

    public String getName() {
        return name;
    }

    public boolean isMultivalued() {
        return isMultivalued;
    }

    public PrimitiveType getPrimitiveType() {
        return primitiveType;
    }

    public String getObjectType() {
        return objectType;
    }
}
