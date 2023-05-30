package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Parameter required by a breakpoint type.
 */
public class BreakpointParameter {
	
	/** Name of the parameter. */
    private String name;
    
    /** True is the parameter is a collection, false otherwise. */
    private boolean isMultivalued;
    
    /**
     * Primitive type of the parameter.
     * Exactly one of `primitiveType` and `objectType` must be set.
     */
    private PrimitiveType primitiveType;
    
    /**
     * Object type of the parameter, as defined in {@link ModelElement.type}.
     * Exactly one of `primitiveType` and `objectType` must be set.
     */
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
