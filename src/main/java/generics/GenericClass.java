package generics;

class GenericClass<T extends Number> {

    private final T t;

    GenericClass(T t) {
        this.t = t;
    }

    T get() {
        return t;
    }

}
