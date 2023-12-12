package File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import Tree.Tree;

public class File {
    private RandomAccessFile input;
    private RandomAccessFile output;
    public File(String[] args) {
        if(args.length < 3) {
            validateFiles(args[0], args[1]);
            try {
                this.input = new RandomAccessFile(args[0], "r");
                this.output = new RandomAccessFile(args[1], "rw");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new IllegalArgumentException("Too much parameters added");
        }
    }

    private void validateFiles(String inputURL, String outputURL) {
        if (inputURL == null || outputURL == null) {
            throw new IllegalArgumentException("Check if name of input and output files is added!");
        }
    }
    public Tree readFileToTree() throws IOException {
        input.seek(0);
        int number;
        Tree tree = new Tree();
        byte[] buf = new byte[100];
        while(true) {
            if((number = input.read(buf)) == -1) {
                break;
            }
            for(int i = 0; i < number; i++) {
                tree.add(buf[i]);
            }
        }
        return tree;
    }
}
