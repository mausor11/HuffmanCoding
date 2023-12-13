package Dictionary;

public class DictionaryNode {
    private final String code;
    private final int sign;
    private final int level;
    private DictionaryNode left, right;

    public DictionaryNode(int sign, String code, int level) {
        this.sign = sign;
        this.code = code;
        this.level = level;
        this.left = null;
        this.right = null;
    }

    public void setLeft(DictionaryNode left) {
        this.left = left;
    }

    public void setRight(DictionaryNode right) {
        this.right = right;
    }

    public DictionaryNode getLeft() {
        return left;
    }

    public DictionaryNode getRight() {
        return right;
    }

    public int getSign() {
        return sign;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return (char)sign + ": " + code;
    }

}
