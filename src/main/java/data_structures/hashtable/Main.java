package data_structures.hashtable;

import java.util.Hashtable;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        Map<String, String> table = new Hashtable<>(10, 0.5f);
        table.put("100", "Saron");
        table.put("123", "Patri");
        table.put("321", "Mati");
        table.put("555", "Sami");
        table.put("777", "Andrei");

        for (String key : table.keySet())
            System.out.println(key.hashCode() % 10 + "\t" + key + "\t" + table.get(key));

    }
}
