import File.File;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Main {
    public static void main(String[] args) {
        try {
            Huffman huffman = new Huffman(args);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
