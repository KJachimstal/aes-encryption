import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

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
        Key key = new Key(new short[] {
                0x2b, 0x28, 0xab, 0x09,
                0x7e, 0xae, 0xf7, 0xcf,
                0x15, 0xd2, 0x15, 0x4f,
                0x16, 0xa6, 0x88, 0x3c
        });
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
        encryption.addRoundKey(block.getData(), 0);
        encryption.subBytes(block.getData());

        short[][] expected = new short[][] {
                { 0xd4, 0xe0, 0xb8, 0x1e },
                { 0x27, 0xbf, 0xb4, 0x41 },
                { 0x11, 0x98, 0x5d, 0x52 },
                { 0xae, 0xf1, 0xe5, 0x30 }
        };

        for (int i = 0; i < block.getData().length; i++) {
            for (int j = 0; j < block.getData()[0].length; j++) {
                assertEquals(expected[i][j], block.getData()[i][j]);
            }
        }
    }

    @Test
    void shiftRows() {
        encryption.addRoundKey(block.getData(), 0);
        encryption.subBytes(block.getData());
        encryption.shiftRows(block.getData());

        short[][] expected = new short[][] {
            { 0xd4, 0xe0, 0xb8, 0x1e },
            { 0xbf, 0xb4, 0x41, 0x27 },
            { 0x5d, 0x52, 0x11, 0x98 },
            { 0x30, 0xae, 0xf1, 0xe5 }
        };

        for (int i = 0; i < block.getData().length; i++) {
            for (int j = 0; j < block.getData()[0].length; j++) {
                assertEquals(expected[i][j], block.getData()[i][j]);
            }
        }
    }

    @Test
    void mixColumns() {
        encryption.addRoundKey(block.getData(), 0);
        encryption.subBytes(block.getData());
        encryption.shiftRows(block.getData());
        encryption.mixColumns(block.getData());

        short[][] expected = new short[][] {
                { 0x04, 0xe0, 0x48, 0x28 },
                { 0x66, 0xcb, 0xf8, 0x06 },
                { 0x81, 0x19, 0xd3, 0x26 },
                { 0xe5, 0x9a, 0x7a, 0x4c}
        };

        for (int i = 0; i < block.getData().length; i++) {
            for (int j = 0; j < block.getData()[0].length; j++) {
                assertEquals((byte)expected[i][j], block.getData()[i][j]);
            }
        }
    }
}