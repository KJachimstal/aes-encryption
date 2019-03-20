public class Block {

    private byte[] data;

    public Block(byte[] data) {
        this.data = fillBytes(data);
    }

    private byte[] fillBytes(byte[] data) {
        if (data.length == 16) {
            return data;
        }
        byte[] output = new byte[16];
        for(int i = 0; i < 16; i++) {
            output[i] = (i > data.length - 1 ? 0 : data[i]);
        }
        return output;
    }

    public byte[] getData() {
        return data;
    }
}
