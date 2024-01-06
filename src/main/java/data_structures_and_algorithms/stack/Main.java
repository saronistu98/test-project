package data_structures_and_algorithms.stack;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

        Stack<String> stack = new Stack<>();
        System.out.println(stack.empty());
        stack.push("GTA V");
        stack.push("Euro Truck Simulator 2");
        stack.push("Need for Speed Underground 2");
        System.out.println(stack.empty());
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack.search("GTA V"));

    }
}
