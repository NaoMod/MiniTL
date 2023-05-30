package org.tetrabox.example.minitl.runtime.lrp;

/**
 * Location in a textual source file.
 */
public class Location {
	
	/** Starting line. */
    private int line;
    
    /** Starting column. */
    private int column;
    
    /** End line. */
    private int endLine;
    
    /** End column. */
    private int endColumn;

    public Location(int line, int column, int endLine, int endColumn) {
        this.line = line;
        this.column = column;
        this.endLine = endLine;
        this.endColumn = endColumn;
    }

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public int getEndLine() {
        return endLine;
    }

    public int getEndColumn() {
        return endColumn;
    }
}
