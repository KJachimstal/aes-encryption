public class RoundKeys {
    private int rounds;
    private Key key;
    private short[][] keys;
    private int roundKeysLength;
    private short[][] roundConstants;

    public RoundKeys(Key key) {
        rounds = key.getRounds();
        this.key = key;
        roundKeysLength = Constants.BLOCK_SIZE * (rounds + 1);
        keys = new short[Constants.BLOCK_SIZE][roundKeysLength];
        fillWithKey();
        buildRoundConstants();
    }

    private void fillWithKey() {
        short[] data = key.getData();
        for (int column = 0; column < key.getLength() / Constants.BLOCK_SIZE; column++) {
            for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
                keys[j][column] = data[Constants.BLOCK_SIZE * column + j];
            }
        }
    }

    private void buildRoundConstants() {
        roundConstants = new short[Constants.BLOCK_SIZE][rounds];
        for (int column = 0; column < roundConstants[0].length; column++) {
            roundConstants[0][column] = Constants.RCON[column];
            for (int j = 1; j < Constants.BLOCK_SIZE; j++) {
                roundConstants[j][column] = 0x0;
            }
        }
    }

    private void addRoundKeys() {
        for (int column = key.getLength() / Constants.BLOCK_SIZE; column < roundKeysLength; column++) {

        }
    }

    public short[][] getKeys() {
        return keys;
    }

    public short[][] getRoundConstants() {
        return roundConstants;
    }
}