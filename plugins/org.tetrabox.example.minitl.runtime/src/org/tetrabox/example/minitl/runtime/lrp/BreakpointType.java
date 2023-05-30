package org.tetrabox.example.minitl.runtime.lrp;

import java.util.List;

/**
 * Breakpoint type defined by the language runtime.
 */
public class BreakpointType {
	
	/** Unique identifier of the breakpoint type. */
    private String id;
    
    /** Human-readable name of the breakpoint type. */
    private String name;
    
    /** Parameters needed to evaluate a breakpoint of this type. */
    private List<BreakpointParameter> parameters;
    
    /** Human-readable description of the breakpoint type. */
    private String description;

    public BreakpointType(String id, String name, List<BreakpointParameter> parameters, String description) {
        this.id = id;
        this.name = name;
        this.parameters = parameters;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<BreakpointParameter> getParameters() {
        return parameters;
    }

    public String getDescription() {
        return description;
    }
}
