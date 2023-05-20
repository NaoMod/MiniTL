package org.tetrabox.example.minitl.runtime.serializers;

import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.core.JsonGenerator;

public class MultipleFieldValue implements FieldValue {
    private String key;
    private List<Object> values;
    private ValueType type;

    public MultipleFieldValue(String key, List<Object> values, ValueType type) {
        this.key = key;
        this.values = values;
        this.type = type;
    }

    @Override
    public void generate(JsonGenerator gen) throws IOException {
        gen.writeArrayFieldStart(key);
        switch (type) {
            case STRING:
                for (Object value : values) {
                    gen.writeString((String) value);
                }
                break;

            case BOOLEAN:
                for (Object value : values) {
                    gen.writeBoolean((boolean) value);
                }
                break;

            case NUMBER:
                for (Object value : values) {
                    gen.writeNumber((long) value);
                }
                break;

            case OBJECT:
                for (Object value : values) {
                    gen.writePOJO(value);
                }
                break;
        }

        gen.writeEndArray();
    }

}
