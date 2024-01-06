package data_structures.adjacency_list;

import java.util.ArrayList;
import java.util.LinkedList;

class Graph {

    private ArrayList<LinkedList<Node>> alist;

    Graph() {
        alist = new ArrayList<>();
    }

    public void add(Node node) {
        LinkedList<Node> currentList = new LinkedList<>();
        currentList.add(node);
        alist.add(currentList);
    }

    public void add(int source, int destination) {
        LinkedList<Node> currentList = alist.get(source);
        Node destinationNode = alist.get(destination).get(0);
        currentList.add(destinationNode);
    }

    public boolean checkEdge(int source, int destination) {
        LinkedList<Node> currentList = alist.get(source);
        Node destinationNode = alist.get(destination).get(0);
        return currentList.stream()
                .anyMatch(node -> node == destinationNode);
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        for (LinkedList<Node> currentList : alist) {
            for (Node node : currentList)
                string.append(node.data).append(" -> ");
            string.append("\n");
        }
        return string.toString();
    }

}
