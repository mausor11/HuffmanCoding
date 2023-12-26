import java.io.IOException;
import java.io.RandomAccessFile;

public class HuffmanDecompression {
    private int leaves;
    private int counter;
    private final RandomAccessFile input;

    public HuffmanDecompression(RandomAccessFile input) throws IOException {
        this.input = input;
        this.input.seek(0);

        counter = input.readByte();
        counter ^= 0xFF;
        counter ^= 0b11111111;

        leaves = input.readByte();
    }

    public int getCounter() {
        return counter;
    }

    public int getLeaves() {
        return leaves;
    }
}
