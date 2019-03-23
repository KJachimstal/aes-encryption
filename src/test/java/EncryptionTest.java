import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    private Encryption encryption;
    private Block block;

    @BeforeEach
    void initialize() {
        short[][] data = new short[][] {
                { 0x32, 0x88, 0x31, 0xe0 },
                { 0x43, 0x5a, 0x31, 0x37 },
                { 0xf6, 0x30, 0x98, 0x07 },
                { 0xa8, 0x8d, 0xa2, 0x34 }
        };

        block = new Block(data);
        Key key = new Key("abcdefghijklmnop");
        encryption = new Encryption(new Block[] { block }, key);
    }

    @Test
    void encrypt() {
        encryption.encrypt();
    }

    @Test
    void subBytes() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
//        Method method = Encryption.class.getDeclaredMethod("subBytes");
//        method.setAccessible(true);
//
//        method.invoke(encryption, data);
        encryption.subBytes(block.getData());

        short[][] expected = new short[][] {
                { 0x23, 0xc4, 0xc7, 0xe1 },
                { 0x1a, 0xbe, 0xc7, 0x9a },
                { 0x42, 0x04, 0x46, 0xc5 },
                { 0xc2, 0x5d, 0x3a, 0x18 }
        };

        for (int i = 0; i < block.getData().length; i++) {
            for (int j = 0; j < block.getData()[0].length; j++) {
                assertEquals(expected[i][j], block.getData()[i][j]);
            }
        }
    }
}