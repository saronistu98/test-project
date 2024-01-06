package data_structures.adjacency_matrix;

import java.util.ArrayList;

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
        boolean[] visited = new boolean[matrix.length];
        depthFirstSearchHelper(source, visited);
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
