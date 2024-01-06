package data_structures.adjacency_matrix;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Graph {

    private final ArrayList<Node> nodes;
    private final int[][] matrix;

    Graph(int size) {
        matrix = new int[size][size];
        nodes = new ArrayList<>();
    }

    public void add(Node node) {
        nodes.add(node);
    }

    public void add(int source, int destination) {
        this.matrix[source][destination] = 1;
    }

    boolean checkEdge(int source, int destination) {
        return this.matrix[source][destination] == 1;
    }

    void depthFirstSearch(int source) {
        System.out.println("\nDepth first search");
        boolean[] visited = new boolean[matrix.length];
        depthFirstSearchHelper(source, visited);
    }

    void breadthFirstSearch(int source) {
        System.out.println("\nBreadth first search");
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[matrix.length];
        queue.offer(source);
        visited[source] = true;
        while (queue.size() != 0) {
            source = queue.poll();
            System.out.println(nodes.get(source).data + " visited");
            for (int i = 0; i < matrix[source].length; i++)
                if (matrix[source][i] == 1 && !visited[i]) {
                    queue.offer(i);
                    visited[i] = true;
                }
        }
    }

    private void depthFirstSearchHelper(int source, boolean[] visited) {
        if (visited[source])
            return;
        visited[source] = true;
        System.out.println(nodes.get(source).data + " visited");
        for (int i = 0; i < matrix[source].length; i++) {
            if (matrix[source][i] == 1)
                depthFirstSearchHelper(i, visited);
        }
    }

    @Override
    public String toString() {
        StringBuilder matrixAsString = new StringBuilder("  ");
        for (Node node : nodes)
            matrixAsString.append(node.data).append(" ");
        matrixAsString.append("\n");
        for (int i = 0; i < matrix.length; i++) {
            matrixAsString.append(nodes.get(i).data).append(" ");
            for (int j = 0; j < matrix[i].length; j++)
                matrixAsString.append(matrix[i][j]).append(" ");
            matrixAsString.append("\n");
        }
        return matrixAsString.toString();
    }

}
