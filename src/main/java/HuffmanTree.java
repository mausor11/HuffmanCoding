import Dictionary.Dictionary;
import PriorityQueue.PriorityQueue;
import Tree.Node;
import Tree.Tree;

public class HuffmanTree {
    private Dictionary dictionary;
    private PriorityQueue priorityQueue;
    private Tree huffmanTree;
    public HuffmanTree(PriorityQueue priorityQueue) {
        this.priorityQueue = priorityQueue;
        this.dictionary = new Dictionary();
        makeHTree();
        makeDictionary(huffmanTree.getRoot(), new StringBuilder(), 0);
    }

    private void makeHTree() {
        int size = priorityQueue.size();
        for(int i=0; i < size -1; i++) {
            Node parent = new Node(0,0);
            Node leftChild = priorityQueue.heapDown();
            Node rightChild = priorityQueue.heapDown();
            parent.setLeft(leftChild);
            parent.setRight(rightChild);
            parent.setCounter(leftChild.getCounter() + rightChild.getCounter());
            priorityQueue.heapUp(parent);
        }
        huffmanTree =  new Tree(priorityQueue.heapDown());
    }

    private void makeDictionary(Node root, StringBuilder code, int level) {
        if(root != null) {
            if (root.getLeft() == null && root.getRight() == null) {
                dictionary.put(root.getSign(), code.toString(), level);
            } else {
                code.append('0');
                makeDictionary(root.getLeft(), code, level + 1);
                code.deleteCharAt(code.length() - 1);

                code.append('1');
                makeDictionary(root.getRight(), code, level + 1);
                code.deleteCharAt(code.length() - 1);
            }
        }
    }

    public void readHTree() {
        readHTree(huffmanTree.getRoot(), 0);
    }

    private void readHTree(Node root, int level) {
        if (root != null) {
            level++;
            System.out.println(root + " | " + level);
            readHTree(root.getLeft(), level);
            readHTree(root.getRight(), level);
        }
    }



}
