public class Block {

    private byte[][] data;

    public Block(byte[][] data) {
        this.data = fillBytes(data);
    }

    private byte[][] fillBytes(byte[][] data) {
        if (data.length == Constants.BLOCK_SIZE * Constants.BLOCK_SIZE) {
            return data;
        }
        byte[][] output = new byte[Constants.BLOCK_SIZE][Constants.BLOCK_SIZE];
        for (int i = 0; i < Constants.BLOCK_SIZE; i++) {
            for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
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
