package data_structures.adjacency_list;

public class Main {
    public static void main(String[] args) {

        // Smaller space complexcity but bigger search complexity

        Graph graph = new Graph();
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

        System.out.println(graph.checkEdge(1, 2));
        System.out.println(graph);

    }
}
