package org.tetrabox.example.minitl.runtime.lrp;

public class CheckBreakpointArguments extends Arguments {
    private String typeId;
    private String elementId;

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getElementId() {
        return elementId;
    }

    public void setElementId(String elementId) {
        this.elementId = elementId;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof CheckBreakpointArguments))
            return false;

        CheckBreakpointArguments castObj = (CheckBreakpointArguments) obj;

        return this.getSourceFile().equals(castObj.getSourceFile()) && this.typeId.equals(castObj.getTypeId())
                && this.elementId.equals(castObj.getElementId());
    }
    
    @Override
    public int hashCode() {
    	return getSourceFile().hashCode() + typeId.hashCode() + elementId.hashCode();
    }
}
