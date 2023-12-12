package Dictionary;

import java.util.Arrays;

public class Dictionary {
    private DictionaryNode root;
    private int leaves;
    public Dictionary() {
        leaves = 0;
    }

    public void put(int sign, String code, int level) {
        root = put(root, sign, code, level);
        this.leaves += 1;
    }
    public int getLeavesNum() {
        return leaves;
    }

    private DictionaryNode put(DictionaryNode root, int sign, String code, int level) {
        if (root == null) {
            return new DictionaryNode(sign, code, level);
        }
        if (isSignSmaller(sign, root)) {
            root.setLeft(put(root.getLeft(), sign, code, level));
        } else {
            root.setRight(put(root.getRight(), sign, code, level));
        }
        return root;
    }
    public DictionaryNode get(int sign) {
        return get(root, sign);
    }
    private DictionaryNode get(DictionaryNode root, int sign) {
        if(root == null || root.getSign() == sign) {
            return root;
        }
        if(isSignSmaller(sign, root)) {
            return get(root.getLeft(), sign);
        }
        return get(root.getRight(), sign);

    }
    private boolean isSignSmaller(int sign, DictionaryNode node) {
        return node.getSign() > sign;
    }

    public void readDictionary() {
        readDictionary(root);
    }

    private void readDictionary(DictionaryNode root) {
        if (root != null) {
            System.out.println(root);
            readDictionary(root.getLeft());
            readDictionary(root.getRight());
        }
    }
}
