import File.File;
import PriorityQueue.PriorityQueue;
import Tree.Tree;

import java.io.IOException;

public class Huffman {
    private File file;
    private HuffmanTree huffmanTree;
    public Huffman(String[] args) throws IOException {
        this.file = new File(args);
        this.huffmanTree = new HuffmanTree(new PriorityQueue(file.readFileToTree()));
    }
}
