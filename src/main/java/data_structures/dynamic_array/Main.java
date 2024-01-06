package data_structures.dynamic_array;

public class Main {
    public static void main(String[] args) {

        DynamicArray dynamicArray = new DynamicArray(4);
        dynamicArray.add("Saron");
        dynamicArray.add("Patri");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);
        dynamicArray.delete("Saron");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);
        dynamicArray.add("Saron");
        dynamicArray.insert(2, "Mati");
        dynamicArray.add("Sami");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.size);
        System.out.println("Found at index " + dynamicArray.search("Mati"));
        dynamicArray.add("Andrei");
        System.out.println(dynamicArray);
        System.out.println(dynamicArray.capacity);

    }
}
