package lambadas_streams;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapNewMethod {

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("a", 112);
        map.put("b", 212);
        map.put("c", 172);

        map.merge("a", 1, Integer::sum);
        map.merge("d", 1, Integer::sum);
        System.out.println(map.get("a"));
        System.out.println(map.get("d"));
        
        Map<String, List<Integer>> groups = new HashMap<>();

        groups.computeIfAbsent("key", (unused) -> new ArrayList<>()).add(1);
        groups.computeIfAbsent("key", (unused) -> new ArrayList<>()).add(2);
        groups.computeIfAbsent("key", (unused) -> new ArrayList<>()).add(1);
        
        System.out.println(groups);
    }
}
