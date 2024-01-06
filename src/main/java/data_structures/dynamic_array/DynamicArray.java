package data_structures.dynamic_array;

public class DynamicArray {

    int size;
    int capacity = 10;
    Object[] array;

    DynamicArray() {
        this.array = new Object[capacity];
    }

    DynamicArray(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }

    public void add(Object data) {
        if (size == capacity)
            grow();
        array[size] = data;
        size++;
    }

    public void insert(int index, Object data) {
        if (size == capacity)
            grow();
        for (int i = size; i > index; i--)
            array[i] = array[i - 1];
        array[index] = data;
        size++;
    }

    public void delete(Object data) {
        for (int i = 0; i < size; i++) {
            if (array[i] == data) {
                for (int j = 0; j < size - i - 1; j++)
                    array[i + j] = array[i + j + 1];
                array[size - 1] = null;
                size--;
                if (size <= capacity / 3)
                    shrink();
                break;
            }
        }
    }

    public int search(Object data) {
        for (int i = 0; i < capacity; i++)
            if (array[i] == data)
                return i;
        return -1;
    }

    public void grow() {
        int newCapacity = capacity * 2;
        Object[] newArray = new Object[newCapacity];
        if (capacity >= 0)
            for (int i = 0; i < size; i++)
                newArray[i] = array[i];
        this.array = newArray;
        this.capacity = newCapacity;
    }

    public void shrink() {
        int newCapacity = capacity / 2;
        Object[] newArray = new Object[newCapacity];
        if (capacity >= 0)
            for (int i = 0; i < size; i++)
                newArray[i] = array[i];
        this.array = newArray;
        this.capacity = newCapacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (int i = 0; i < capacity; i++)
            string.append(array[i]).append(", ");
        if (!string.toString().equals(""))
            return "[" + string.substring(0, string.length() - 2) + "]";
        return "[]";
    }

}
