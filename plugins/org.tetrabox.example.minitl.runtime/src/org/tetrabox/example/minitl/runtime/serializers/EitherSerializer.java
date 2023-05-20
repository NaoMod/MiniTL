package org.tetrabox.example.minitl.runtime.serializers;

import java.io.IOException;

import org.tetrabox.example.minitl.runtime.lrp.Either;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class EitherSerializer<L, R> extends StdSerializer<Either<L, R>> {

    public EitherSerializer() {
        this(null);
    }

    public EitherSerializer(Class<Either<L, R>> t) {
        super(t);
    }

    @Override
    public void serialize(Either<L, R> value, JsonGenerator gen, SerializerProvider provider) throws IOException {
        if (value.isLeft()) {
            gen.writeObject(value.getLeft());
        } else {
            gen.writeObject(value.getRight());
        }
    }
}
