public class Block {

    private byte[][] data;

    public Block(byte[][] data) {
        this.data = fillBytes(data);
    }

    private byte[][] fillBytes(byte[][] data) {
        if (data.length == 16) {
            return data;
        }
        byte[][] output = new byte[4][4];
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (i >= data.length || j >= data[i].length) {
                    output[i][j] = 0;
                } else {
                    output[i][j] = data[i][j];
                }
            }
        }
        return output;
    }

    public byte[][] getData() {
        return data;
    }
}
