package org.tetrabox.example.minitl.runtime.lrp;

public class Location {
    private int line;
    private int column;
    private int endLine;
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
