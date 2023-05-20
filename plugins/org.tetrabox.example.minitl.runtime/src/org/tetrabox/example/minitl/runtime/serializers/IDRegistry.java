package org.tetrabox.example.minitl.runtime.serializers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IDRegistry {
    private static Map<Object, String> ids = new HashMap<>();

    public static String getId(Object object) {
        if (!ids.containsKey(object))
            ids.put(object, UUID.randomUUID().toString());

        return ids.get(object);
    }
}
