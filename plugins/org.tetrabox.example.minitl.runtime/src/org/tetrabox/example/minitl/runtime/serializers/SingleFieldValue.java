package org.tetrabox.example.minitl.runtime.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

public class SingleFieldValue implements FieldValue {
    private String key;
    private Object value;
    private ValueType type;

    public SingleFieldValue(String key, Object value, ValueType type) {
        this.key = key;
        this.value = value;
        this.type = type;
    }

    @Override
    public void generate(JsonGenerator gen) throws IOException {
        switch (type) {
            case STRING:
                gen.writeStringField(key, (String) value);
                break;

            case BOOLEAN:
                gen.writeBooleanField(key, (boolean) value);
                break;

            case NUMBER:
                gen.writeNumberField(key, (long) value);
                break;

            case OBJECT:
                gen.writePOJOField(key, value);
                break;
        }
    }

}
