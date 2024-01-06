package binary_tree_search;

class BinarySearchTree {

    private Node root;

    void insert(Node node) {
        root = insertHelper(root, node);
    }

    private Node insertHelper(Node root, Node node) {
        int data = node.data;
        if (root == null) {
            root = node;
            return root;
        }
        if (data < root.data)
            root.left = insertHelper(root.left, node);
        else
            root.right = insertHelper(root.right, node);
        return root;
    }

    void display() {
        displayHelper(root);
    }

    private void displayHelper(Node root) {
        if (root != null) {
            displayHelper(root.left);
            System.out.println(root.data);
            displayHelper(root.right);
        }
    }

    boolean search(int data) {
        return searchHelper(root, data);
    }

    private boolean searchHelper(Node root, int data) {
        if (root == null)
            return false;
        if (root.data == data)
            return true;
        if (root.data > data)
            return searchHelper(root.left, data);
        return searchHelper(root.right, data);
    }

    void remove(int data) {
        if (!search(data))
            return;
        removeHepler(root, data);
    }

    private Node removeHepler(Node root, int data) {
        if (root == null)
            return null;
        if (data < root.data) {
            root.left = removeHepler(root.left, data);
        } else if (data > root.data) {
            root.right = removeHepler(root.right, data);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.data = successor(root);
                root.right = removeHepler(root.right, root.data);
            } else {
                root.data = predeccessor(root);
                root.left = removeHepler(root.left, data);
            }
            return root;
        }

        return null;
    }

    private int successor(Node root) {
        root = root.right;
        while (root.left != null)
            root = root.left;
        return root.data;
    }

    private int predeccessor(Node root) {
        root = root.left;
        while (root.left != null)
            root = root.right;
        return root.data;
    }

}
