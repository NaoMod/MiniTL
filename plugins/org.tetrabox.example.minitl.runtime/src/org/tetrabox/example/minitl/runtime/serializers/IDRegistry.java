package org.tetrabox.example.minitl.runtime.serializers;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class IDRegistry {
    private static Map<Object, String> ids = new HashMap<>();

    public static String getId(Object object) {
    	if (object == null) return null;
    	
        if (!ids.containsKey(object))
            ids.put(object, UUID.randomUUID().toString());

        return ids.get(object);
    }
    
    public static boolean contains(Object object) {
    	return ids.containsKey(object);
    }
}
