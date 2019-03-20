public class Block {

    private byte[] data;

    public Block(byte[] data) {
        this.data = fillBytes(data);
    }

    private byte[] fillBytes(byte[] data) {
        for(int i = data.length - 1; i < 16; i++) {
            data[i] = 0;
        }
        return data;
    }

    public byte[] getData() {
        return data;
    }
}
