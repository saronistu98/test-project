package data_structures.queue.priority_queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
    public static void main(String[] args) {

        Queue<Person> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.offer(new Person("Saron", 25));
        queue.offer(new Person("Patri", 25));
        queue.offer(new Person("Mati", 24));
        queue.offer(new Person("Sami", 27));

        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

    }
}
