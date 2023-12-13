import File.File;
import PriorityQueue.PriorityQueue;

import java.io.IOException;

public class Huffman {
    private File file;
    private HuffmanTree huffmanTree;
    public Huffman(String[] args) throws IOException {
        this.file = new File(args);
        this.huffmanTree = new HuffmanTree(new PriorityQueue(file.readFileToTree()));
        this.file.codeTreeToFile(this.huffmanTree.getRoot());
        this.file.codeFile(this.huffmanTree.getDictionary());

    }
}
