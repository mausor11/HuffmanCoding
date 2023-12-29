package File;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

import Tree.Tree;
import Tree.Node;

public class FileD {
    private RandomAccessFile input;
    private RandomAccessFile output;
    private int counter;
    private int last;
    private int leaves;
    private Union union;

    public FileD(String[] args) {
        try {
            this.input = new RandomAccessFile(args[1], "r");
            this.output = new RandomAccessFile(args[2], "rw");
            union = new Union();
            this.counter = 8;
            checkInput();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void checkInput() throws IOException {
        this.last = input.readByte();
        this.leaves = input.readByte();
    }

    public Tree readTreeFromFile() throws IOException {
        input.seek(2);
        union.setA(input.readByte());
        union.setB(input.readByte());
        return new Tree(readTreeFromFile(input));
    }

    public Node readTreeFromFile(RandomAccessFile input) throws IOException {
        if (leaves != 0) {
            Node tree = new Node();
            if (counter == 0) {
                union.setB(input.readByte() & 0xFF);
                counter += 8;
            }
            int currentBit = union.bit(union.getA(), 7);
            union.setA(union.getA() << 1 & 0xFF);

            int tmp2 = union.bit(union.getB(), 7);
            union.setB(union.getB() << 1 & 0xFF);
            union.setA(union.getA() + tmp2);
            counter--;

            if (currentBit == 0) {
                tree.setLeft(readTreeFromFile(input));
                tree.setRight(readTreeFromFile(input));
            } else {
                tree.setSign(union.getA());
                tree.counterMinus();
                leaves--;

                union.moveBits(counter);
                union.setB(input.readByte());
                union.moveBits(8 - counter);
            }
            return tree;
        }
        return null;
    }
    public void decodefile(Node root) throws IOException {
        Node lastRoot = decodeFile(root);
        last += counter;
        lastBytes(lastRoot, root);

    }
    private Node decode(Node root) {
        Node tmpRoot = root;
        while(this.counter != 0) {
            if(union.bit(union.getA(), 7) == 0) {
                union.moveBits(1);
                this.counter--;

                tmpRoot = tmpRoot.getLeft();
            } else {
                union.moveBits(1);
                this.counter--;

                tmpRoot = tmpRoot.getRight();
            }
            if(tmpRoot.getCounter() != 0) {
                break;
            }
        }
        return tmpRoot;
    }
    private Node decodeFile(Node root) throws IOException {
        int number, i;
        byte received, cahr = 0;
        byte[] buf = new byte[100];
        Node tmpRoot = root;
        while((number = input.read(buf)) != -1) {
            i = 0;
            while(i < number) {
                cahr = 0;
                while(cahr == 0) {
                    if(this.counter == 0) {
                        if(i < number) {
                            int tmp = buf[i];
                            tmp &= 0xFF;
                            tmp &= 0b11111111;
                            union.setB(tmp);
                            i++;
                            this.counter += 8;
                        } else {
                            break;
                        }
                    }
                    tmpRoot = decode(tmpRoot);
                    if(tmpRoot.getCounter() != 0) {
                        received = (byte) tmpRoot.getSign();
                        cahr = received;
                        output.writeByte(cahr);
                        tmpRoot = root;
                    }
                }
            }
        }
        if(cahr != 0) {
            tmpRoot = root;
        }
        return tmpRoot;
    }
    private void lastBytes(Node lastBytes, Node root) throws IOException {
        while(last != 0) {
            if(lastBytes.getCounter() != 0) {
                output.writeByte(lastBytes.getSign());
                lastBytes = root;
            }
            if(union.bit(union.getA(), 7) == 0) {
                union.moveBits(1);
                last--;
                lastBytes = lastBytes.getLeft();
            } else {
                union.moveBits(1);
                last--;
                lastBytes = lastBytes.getRight();
            }
        }
        output.writeByte(lastBytes.getSign());
    }


}
