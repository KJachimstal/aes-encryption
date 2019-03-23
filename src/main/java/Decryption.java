public class Decryption {

    private Block[] blocks;
    private int rounds;
    private RoundKeys roundKeys;

    public Decryption(Block[] blocks, Key key) {
//        Set blocks
        this.blocks = blocks;

//        Get rounds count from key
        rounds = key.getRounds();

//        Create round keys
        roundKeys = new RoundKeys(key);
    }

    public void decrypt() {

    }

}
