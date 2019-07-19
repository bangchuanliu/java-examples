package generics;

import java.util.HashMap;
import java.util.Map;

public class GenericTypeMap {

    private Map<String, Map<Class<?>, Object>> map = new HashMap<>();


    public <T> void put(String key, Class<T> clazz, Object value) {
        Map<Class<?>, Object> internalMap = map.get(key);
        if (internalMap == null) {
            internalMap = new HashMap<>();
        }
        internalMap.put(clazz, value);
        map.put(key, internalMap);
    }


    public <T> T get(String key, Class<T> clazz) {
        Map<Class<?>, Object> internalMap = map.get(key);
        if (internalMap == null || internalMap.get(clazz) == null) {
            return null;
        }
        
        return clazz.cast(internalMap.get(clazz));
    }
}
