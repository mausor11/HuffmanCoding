package File;

public class Union {
    private int A;
    private int B;
    public Union() {
        this.A = 0;
        this.B = 0;
    }

    public void setA(int A) {
        this.A = A;
    }

    public void setB(int B) {
        this.B = B;
    }
    public byte getA() {
        return (byte)A;
    }
    public byte getB() {
        return (byte)B;
    }
    public int bit(byte resource, int which) {
        return (resource >> which) & 1;
    }
    public byte bits(byte b, int numBits) {
        int mask = (1 << numBits) - 1;
        int shift = Byte.SIZE - numBits;
        return (byte) ((b & (mask << shift)) >>> shift);
    }
    public byte getLessBits(byte b, int numBits) {
        int mask = (1 << numBits) - 1;
        return (byte) (b & mask);
    }
    public void moveBits(int many) {
        if(many == 1) {
            this.A <<= 1;
            this.A &= 0xFF;
            this.A &= 0b11111111;
            int tmp2 = bit((byte)this.B, 7);
            this.B <<= 1;
            this.B &= 0xFF;
            this.B &= 0b11111111;
            this.A += tmp2;
        } else {
            this.A <<= many;
            this.A &= 0xFF;
            this.A &= 0b11111111;
            int tmp = bits((byte)this.B, many);
            this.B <<= many;
            this.B &= 0xFF;
            this.B &= 0b11111111;
            this.A += tmp;
        }
    }
}
