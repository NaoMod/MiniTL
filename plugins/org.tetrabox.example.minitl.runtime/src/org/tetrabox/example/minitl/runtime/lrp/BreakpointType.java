package org.tetrabox.example.minitl.runtime.lrp;

import java.util.List;

public class BreakpointType {
    private String id;
    private String name;
    private List<BreakpointParameter> parameters;
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
