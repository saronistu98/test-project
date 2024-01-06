package binary_tree_search;

public class Main {
    public static void main(String[] args) {

        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(new Node(5));
        binarySearchTree.insert(new Node(1));
        binarySearchTree.insert(new Node(9));
        binarySearchTree.insert(new Node(7));
        binarySearchTree.insert(new Node(3));
        binarySearchTree.insert(new Node(6));
        binarySearchTree.insert(new Node(4));
        binarySearchTree.insert(new Node(8));

        binarySearchTree.remove(1);
        binarySearchTree.remove(0);
        binarySearchTree.display();
        System.out.println(binarySearchTree.search(10));
        System.out.println(binarySearchTree.search(7));

    }
}
