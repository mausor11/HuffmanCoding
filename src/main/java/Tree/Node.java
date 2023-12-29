package Tree;

public class Node{
    private int counter;
    private int sign;
    private Node left, right;
    public Node() {
        this.sign = 0;
        this.counter = 0;
        this.left = null;
        this.right = null;
    }
    public Node(int sign) {
        this.sign = sign;
        this.counter = 1;
        this.left = null;
        this.right = null;
    }
    public Node(int sign, int counter) {
        this.sign = sign;
        this.counter = counter;
        this.left = null;
        this.right = null;
    }
    public void setSign(int sign) {
        this.sign = sign;
    }

    public void setLeft(Node left) {
        this.left = left;
    }
    public void setRight(Node right) {
        this.right = right;
    }
    public Node getLeft() {
        return left;
    }
    public Node getRight() {
        return right;
    }
    public int getSign() {
        return sign;
    }
    public void counterPlus() {
        counter++;
    }
    public void counterMinus() {
        counter--;
    }
    public int getCounter() {
        return counter;
    }
    public void setCounter(int counter) {
        this.counter = counter;
    }
    @Override
    public String toString() {
        return (char)sign + " | " + counter;
    }
}