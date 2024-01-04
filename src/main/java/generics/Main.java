package generics;

public class Main {
    public static void main(String[] args) {

        Integer[] intArray = {1, 2, 3, 4};
        Double[] doubleArray = {1.1, 2.2, 3.3, 4.4};
        Character[] charArray = {'a', 'b', 'c', 'd'};
        String[] stringArray = {"w", "x", "y", "z"};
        GenericClass<Integer> genericClass = new GenericClass<>(413);
        System.out.println(genericClass.get());
        printArray(intArray);
        printArray(doubleArray);
        printArray(charArray);
        printArray(stringArray);
        System.out.println(getFirstElement(intArray));
    }

    private static <T> void printArray(T[] array) {
        for (T x : array)
            System.out.print(x + " ");
        System.out.println();
    }

    private static <T> T getFirstElement(T[] array) {
        return array[0];
    }

}
