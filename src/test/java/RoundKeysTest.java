import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class RoundKeysTest {
    private RoundKeys roundKeys;
    private String keyString = "1234567890123456";
    private Key key;

    @BeforeEach
    void initialize() {
        key = new Key(keyString);
        roundKeys = new RoundKeys(key);
    }

    @Test
    void getKeys() {
        short[][] keys = roundKeys.getKeys();
        for (int i = 0; i < key.getLength() / Constants.BLOCK_SIZE; i++) {
            for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
                assertEquals(keyString.toCharArray()[Constants.BLOCK_SIZE * i + j], keys[j][i]);
            }
        }
    }

}