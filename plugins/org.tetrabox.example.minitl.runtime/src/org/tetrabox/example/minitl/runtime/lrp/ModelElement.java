package org.tetrabox.example.minitl.runtime.lrp;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * Element of the AST or runtime state.
 */
public class ModelElement {
	
	/** Unique identifier of the element. */
    private String id;
    
    /** Type of the element. */
    private String type;
    
    /** Containment relations with other elements. */
    private Map<String, Either<ModelElement, List<ModelElement>>> children;
    
    /** References to other elements. */
    private Map<String, Either<String, List<String>>> refs;
    
    /** Attributes with primitive values. */
    private Map<String, Either<Object, List<Object>>> attributes;

    /** Location of the element in its original source file. */
    @JsonInclude(Include.NON_NULL)
    private Location location;

    public ModelElement(String id, String type) {
        this(id, type, new HashMap<>(), new HashMap<>(), new HashMap<>(), null);
    }

    public ModelElement(String id, String type, Location location) {
        this(id, type, new HashMap<>(), new HashMap<>(), new HashMap<>(), location);
    }

    public ModelElement(String id, String type, Map<String, Either<Object, List<Object>>> attributes,
            Map<String, Either<ModelElement, List<ModelElement>>> children,
            Map<String, Either<String, List<String>>> refs) {
        this(id, type, attributes, children, refs, null);
    }

    public ModelElement(String id, String type, Map<String, Either<Object, List<Object>>> attributes,
            Map<String, Either<ModelElement, List<ModelElement>>> children,
            Map<String, Either<String, List<String>>> refs, Location location) {
        this.id = id;
        this.type = type;
        this.location = location;
        this.attributes = attributes;
        this.children = children;
        this.refs = refs;
    }

    public String getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public Map<String, Either<ModelElement, List<ModelElement>>> getChildren() {
        return children;
    }

    public Map<String, Either<String, List<String>>> getRefs() {
        return refs;
    }

    public Map<String, Either<Object, List<Object>>> getAttributes() {
        return attributes;
    }

    public Location getLocation() {
        return location;
    }
}