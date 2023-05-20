package org.tetrabox.example.minitl.runtime.lrp;

public class Arguments {
    private String sourceFile;

    public String getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(String sourceFile) {
        this.sourceFile = sourceFile;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof ParseArguments))
            return false;

        Arguments castObj = (Arguments) obj;

        return this.sourceFile.equals(castObj.sourceFile);
    }
}
