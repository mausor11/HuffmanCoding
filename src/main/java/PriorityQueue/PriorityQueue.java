package PriorityQueue;

import Tree.Tree;
import Tree.Node;

import java.util.ArrayList;
import java.util.List;

public class PriorityQueue {
    private List<Node> priorityQueue;
    public PriorityQueue(Tree tree) {
        priorityQueue = new ArrayList<>();
        addToPriorityQueue(tree.getRoot());
    }
    public int size() {
        return priorityQueue.size();
    }
    public void heapUp(int sign, int counter) {
        priorityQueue.add(new Node(sign, counter));
        int added = priorityQueue.size() - 1;
        while(added != 0) {
            int parent = (added - 1) / 2;
            if(priorityQueue.get(added).getCounter() < priorityQueue.get(parent).getCounter()) {
                swap(added, parent);
                added = parent;
            } else {
                break;
            }
        }
    }

    public void heapUp(Node node) {
        priorityQueue.add(node);
        int added = priorityQueue.size() - 1;
        while (added != 0) {
            int parent = (added - 1) / 2;
            if (priorityQueue.get(added).getCounter() < priorityQueue.get(parent).getCounter()) {
                swap(added, parent);
                added = parent;
            } else {
                break;
            }
        }
    }

    public Node heapDown() {
        Node minNode = priorityQueue.get(0);
        priorityQueue.set(0, priorityQueue.get(priorityQueue.size() - 1));
        priorityQueue.remove(priorityQueue.size() - 1);
        minHeapify();
        return minNode;
    }
    private void minHeapify() {
        int currId = 0;
        while (true) {
            int leftChild = 2 * currId + 1;
            int rightChild = 2 * currId + 2;
            int min = currId;

            if (leftChild < priorityQueue.size() && priorityQueue.get(leftChild).getCounter() < priorityQueue.get(min).getCounter()) {
                min = leftChild;
            }

            if (rightChild < priorityQueue.size() && priorityQueue.get(rightChild).getCounter() < priorityQueue.get(min).getCounter()) {
                min = rightChild;
            }

            if (min != currId) {
                swap(min, currId);
                currId = min;
            } else {
                break;
            }
        }

    }

    private void addToPriorityQueue(Node root) {
        if(root != null) {
            addToPriorityQueue(root.getLeft());
            this.heapUp(root.getSign(), root.getCounter());
            addToPriorityQueue(root.getRight());
        }
    }
    private void swap (int firstId, int secondId) {
        Node tmp = priorityQueue.get(firstId);
        priorityQueue.set(firstId, priorityQueue.get(secondId));
        priorityQueue.set(secondId, tmp);
    }

}
