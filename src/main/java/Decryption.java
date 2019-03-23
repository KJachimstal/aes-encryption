public class Decryption extends Cryptography {

    public Decryption(Block[] blocks, Key key) {
        super(blocks, key);
    }

    public void decrypt() {
//        Rounds
        for (int i = rounds - 1; i > 0; i--) {
            for (int b = 0; b < blocks.length; b++) {
                decryptBlock(blocks[b], i + 1);
            }
        }
    }

    public void decryptBlock(Block block, int round) {
        short[][] data = block.getData();

//        AddRoundKey
        addRoundKey(data, round);
//        InvMixColumns
        if (rounds > 1) {
            mixColumns(data, true);
        }
//        InvShiftRows
        shiftRows(data, true);
    }
}
