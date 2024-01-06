package data_structures.queue.linked_list;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Queue<String> queue = new LinkedList<>();
        queue.offer("Saron");
        queue.offer("Patri");
        queue.offer("Mati");
        queue.offer("Sami");
        System.out.println(queue);
        System.out.println(queue.peek());
        queue.poll();
        System.out.println(queue.peek());

    }
}
