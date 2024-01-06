package data_structures.graph;

public class Main {
    public static void main(String[] args) {

        Graph graph = new Graph(5);
        graph.add(new Node('A'));
        graph.add(new Node('B'));
        graph.add(new Node('C'));
        graph.add(new Node('D'));
        graph.add(new Node('E'));
        graph.add(0, 1);
        graph.add(1, 2);
        graph.add(2, 3);
        graph.add(2, 4);
        graph.add(4, 0);
        graph.add(4, 2);

        System.out.println(graph.checkEdge(0, 1));
        System.out.println(graph);

    }
}
