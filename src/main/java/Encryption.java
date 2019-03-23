public class Encryption {

    private Block[] blocks;
    private int rounds;
    private RoundKeys roundKeys;

    public Encryption(Block[] blocks, Key key) {
//        Set blocks
        this.blocks = blocks;

//        Get rounds count from key
        rounds = key.getRounds();

//        Create round keys
        roundKeys = new RoundKeys(key);
    }

    public void encrypt() {
//        Add round key
        for (int b = 0; b < blocks.length; b++) {
            addRoundKey(blocks[b].getData(), 0);
        }

//        Rounds
        for (int i = 0; i < rounds; i++) {
            for (int b = 0; b < blocks.length; b++) {
                encryptBlock(blocks[b], i + 1);
            }
        }
    }

    private void encryptBlock(Block block, int round) {
        short[][] data = block.getData();

//        SubBytes
        subBytes(data);
//        ShiftRows
        shiftRows(data);
//        MixColumns
        if (rounds != round) {
            mixColumns(data);
        }
//        AddRoundKey
        addRoundKey(data, round);
    }

    public void subBytes(short[][] data) {
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                data[i][j] = Operations.subByte(data[i][j]);
            }
        }
    }

    public void shiftRows(short[][] data) {
        for (int i = 0; i < data.length; i++) {
            Operations.shiftRow(data[i], i);
        }
    }

    public void mixColumns(short[][] data) {
        for (int i = 0; i < data.length; i++) {
            short[] tmp = new short[data.length];
            for (int j = 0; j < data[0].length; j++) {
                tmp[j] = data[j][i];
            }

            short[] mixed = Operations.mixColumn(tmp);
            for (int j = 0; j < data[0].length; j++) {
                data[j][i] = mixed[j];
            }
        }
    }

    public void addRoundKey(short[][] data, int round) {
        Operations.addRoundKey(data, roundKeys, round);
    }

    public Block[] getBlocks() {
        return blocks;
    }
}
