package File;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

import Dictionary.Dictionary;
import Tree.Tree;
import Tree.Node;
public class File {
    private RandomAccessFile input;
    private RandomAccessFile output;
    private int counter;
    private int tmp;
    public File(String[] args) {
        if(args.length < 3) {
            validateFiles(args[0], args[1]);
            try {
                this.input = new RandomAccessFile(args[0], "r");
                this.output = new RandomAccessFile(args[1], "rw");
                this.counter = 0;
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

    public void check() throws IOException {
        int byteRead;
        output.seek(0);
        while((byteRead = output.read()) != -1) {
            System.out.println(byteRead + " " +Integer.toBinaryString(byteRead));
        }
    }

    public void codeFile(Dictionary dictionary) throws IOException {
        input.seek(0);
        byte[] buf = new byte[100];
        int number =input.read(buf);

        while(number != -1) {
            for(int i = 0; i < number; i++) {
                int j = 0;
                while(j < dictionary.get(buf[i]).getCode().length()) {
                    if(counter == 8) {
                        tmp &= 0xFF;
                        output.write(tmp & 0b11111111);
                        tmp = 0;
                        counter = 0;
                    }
                    tmp <<= 1;
                    tmp += (Integer.valueOf(dictionary.get(buf[i]).getCode().charAt(j)) - 48);

                    j++;
                    counter++;
                }
            }
            number = input.read(buf);
        }
        tmp <<= (Byte.SIZE - counter);
        tmp &= 0xFF;
        output.write(tmp & 0b11111111);
        saveCounter(dictionary.getLeavesNum());
    }

    public void codeTreeToFile(Node root) throws IOException {
       if(counter == 8) {
           int k = tmp & 0xFF;

           output.write(k & 0b11111111);
           tmp = 0;
           counter = 0;
       }
       tmp <<= 1;

       counter++;
       if(root.getLeft() == null && root.getRight() == null) {
           tmp |= 1;
           tmp &= 0xFF;
           tmp &= 0b11111111;
           Union union = new Union();
           union.setA((byte)tmp);
           union.setB(root.getSign());
           union.moveBits(8 - counter);
           int k = union.getA() & 0xFF;
           output.write(k & 0b11111111);
           union.moveBits(counter);
           tmp = union.getA();
       } else {
           tmp |= 0;
       }
       if(root.getLeft() != null) {
            codeTreeToFile(root.getLeft());
        }
        if(root.getRight() != null) {
            codeTreeToFile(root.getRight());
        }
    }

    private void saveCounter(int num) throws IOException {
        output.seek(0);
        byte[] remainingBytes = new byte[(int) (output.length())];
        output.readFully(remainingBytes);
        output.seek(0);
        output.write(counter);
        output.write(num);
        output.write(remainingBytes);
    }

}
