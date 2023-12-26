package File;

import java.io.IOException;
import java.io.RandomAccessFile;
import Tree.Tree;
import Tree.Node;

public class FileD {
    private RandomAccessFile input;
    private RandomAccessFile output;
    private int counter;
    private int leaves;
    private Union union;
    public FileD(String[] args) {
        try {
            this.input = new RandomAccessFile(args[1], "r");
            this.output = new RandomAccessFile(args[2], "rw");
            union = new Union();
            checkInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public void checkInput() throws IOException {
        this.counter = input.readByte();
        this.leaves = input.readByte();
    }
// todo: IDK
    
//    public Tree readTreeFromFile(RandomAccessFile input) throws IOException {
//        if(leaves != 0) {
//            Tree tree = new Tree();
//            if(counter == 0) {
//                union.setB(input.readByte() & 0xFF);
//                counter += 8;
//            }
//            int currentBit = union.bit(union.getA(), 7);
//            union.setA(union.getA() << 1 & 0xFF);
//
//            int tmp2 = union.bit(union.getB(), 7);
//            union.setB(union.getB() << 1 & 0xFF);
//            union.setA(union.getA() + tmp2);
//            counter--;
//
//        }
//    }
}
