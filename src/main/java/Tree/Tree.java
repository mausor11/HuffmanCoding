package Tree;


import PriorityQueue.PriorityQueue;

// Frequency tree - nie Huffman Tree
public class Tree {

    public Node root;

    public Tree() {}

    public Tree(Node root) {
        this.root = root;
    }
    public void add(int sign) {
        root = add(root, sign);
    }

    private Node add(Node root, int sign) {
        if(root == null) {
            return new Node(sign);
        }
        if(sign - root.getSign() < 0) {
            root.setLeft(add(root.getLeft(), sign));
        } else if(sign - root.getSign() > 0) {
            root.setRight(add(root.getRight(), sign));
        } else {
            root.counterPlus();
        }
        return root;
    }
    public Node getRoot() {
        return root;
    }
    public void readTree() {
        readTree(root);
    }

    private void readTree(Node root) {
        if (root != null) {
            readTree(root.getLeft());
            readTree(root.getRight());
            System.out.println(root);
        }
    }


    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.add('a');
        tree.add('c');
        tree.add('d');
        tree.add('e');
        tree.add('h');
        tree.add('s');
        tree.add('a');
        tree.readTree();
        PriorityQueue priorityQueue = new PriorityQueue(tree);
    }
}
