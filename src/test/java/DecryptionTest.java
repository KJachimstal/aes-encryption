import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DecryptionTest {

    private Decryption decryption;
    private Block block;

    @BeforeEach
    void initialize() {
        short[][] data = new short[][] {
                { 0x39, 0x02, 0xdc, 0x19 },
                { 0x25, 0xdc, 0x11, 0x6a },
                { 0x84, 0x09, 0x85, 0x0b },
                { 0x1d, 0xfb, 0x97, 0x32 }
        };

        block = new Block(data);
        Key key = new Key(new short[] {
                0x2b, 0x28, 0xab, 0x09,
                0x7e, 0xae, 0xf7, 0xcf,
                0x15, 0xd2, 0x15, 0x4f,
                0x16, 0xa6, 0x88, 0x3c
        });
        decryption = new Decryption(new Block[] { block }, key);
    }

    @Test
    void decrypt() {
        short[][] expected = new short[][] {
                { 0x32, 0x88, 0x31, 0xe0 },
                { 0x43, 0x5a, 0x31, 0x37 },
                { 0xf6, 0x30, 0x98, 0x07 },
                { 0xa8, 0x8d, 0xa2, 0x34 }
        };
        decryption.decrypt();

        Block[] blocks = decryption.getBlocks();
        for (int b = 0; b < blocks.length; b++) {
            System.out.println(blocks[b]);
            for (int i = 0; i < block.getData().length; i++) {
                for (int j = 0; j < block.getData()[0].length; j++) {
                    assertEquals(expected[i][j], blocks[b].getData()[i][j]);
                }
            }
        }
    }

    @Test
    void addRoundKey() {
        decryption.addRoundKey(block.getData(), 10);

        short[][] expected = new short[][] {
                { 0xe9, 0xcb, 0x3d, 0xaf },
                { 0x31, 0x32, 0x2e, 0x09 },
                { 0x7d, 0x2c, 0x89, 0x07 },
                { 0xb5, 0x72, 0x5f, 0x94 }
        };

        for (int i = 0; i < block.getData().length; i++) {
            for (int j = 0; j < block.getData()[0].length; j++) {
                assertEquals(expected[i][j], block.getData()[i][j]);
            }
        }
    }

}