import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockTest {

    private Block block;

    @BeforeEach
    void initialize() {
        block = new Block(new byte[] {
                11, -50, 37, 81,
                20, -46, -81, 70,
                55, 21, -66, 23,
                77, 74, -21, 1
        });
    }

    @Test
    void getData() {
        assertEquals(16, block.getData().length);
    }

    @Test
    void fillBytes() {
        Block invalidBlock = new Block(new byte[] {
                11, -50, 37, 81,
                20, -46, -81, 70,
                55, 21, -66, 23
        });
        assertEquals(16, invalidBlock.getData().length);
    }
}