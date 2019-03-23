public class Encryption extends Cryptography {

    public Encryption(Block[] blocks, Key key) {
        super(blocks, key);
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

}
