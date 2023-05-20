package org.tetrabox.example.minitl.runtime.serializers;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;

public interface FieldValue {
    void generate(JsonGenerator gen) throws IOException;
}
