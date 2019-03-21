public class RoundKeys {
    private int rounds;
    private Key key;
    private short[][] keys;

    public RoundKeys(Key key) {
        rounds = key.getRounds();
        this.key = key;
        keys = new short[Constants.BLOCK_SIZE][Constants.BLOCK_SIZE * (rounds + 1)];
    }

    private void fillWithKey() {
        short[] data = key.getData();
        for (int column = 0; column < key.getLength() / Constants.BLOCK_SIZE; column++) {
            for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
                keys[j][column] = data[Constants.BLOCK_SIZE * column + j];
            }
        }
    }
}