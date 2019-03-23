import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    private Encryption encryption;

    @BeforeEach
    void initialize() {
        Block[] blocks = new Block[] {
                new Block(new short[][] {
                        { 0x32, 0x88, 0x31, 0xe0 },
                        { 0x43, 0x5a, 0x31, 0x37 },
                        { 0xf6, 0x30, 0x98, 0x07 },
                        { 0xa8, 0x8d, 0xa2, 0x34 }
                })
        };
        Key key = new Key("abcdefghijklmnop");
        encryption = new Encryption(blocks, key);
    }

    @Test
    void encrypt() {
        encryption.encrypt();
    }
}