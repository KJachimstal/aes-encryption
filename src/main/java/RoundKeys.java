import java.util.Arrays;

public class RoundKeys {
    private int rounds;
    private Key key;
    private short[][] roundKeys;
    private int roundKeysLength;
    private short[][] roundConstants;

    public RoundKeys(Key key) {
        rounds = key.getRounds();
        this.key = key;
        roundKeysLength = Constants.BLOCK_SIZE * (rounds + 1);
        roundKeys = new short[Constants.BLOCK_SIZE][roundKeysLength];
        fillWithKey();
        buildRoundConstants();
        addRoundKeys();
    }

    private void fillWithKey() {
        short[] data = key.getData();
        for (int column = 0; column < key.getLength() / Constants.BLOCK_SIZE; column++) {
            for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
                roundKeys[j][column] = data[Constants.BLOCK_SIZE * column + j];
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
            short[] w1 = new short[Constants.BLOCK_SIZE];
            for (int row = 0; row < Constants.BLOCK_SIZE; row++) {
                w1[row] = roundKeys[row][column - 1];
            }
            if (column % Constants.BLOCK_SIZE == 0) {
                rotWord(w1);
            }
//          to do
        }
    }

    private void rotWord(short[] column) {
        short first = column[0];
        for (int i = 0; i < Constants.BLOCK_SIZE - 1; i++) {
            column[i] = column[i + 1];
        }
        column[column.length - 1] = first;
    }

    public short[][] getKeys() {
        return roundKeys;
    }

    public short[][] getRoundConstants() {
        return roundConstants;
    }
}