import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void subBytes() {
        assertEquals(0xff, Operations.subBytes((short) 0x7d));
        assertEquals(0x16, Operations.subBytes((short) 0xff));
    }

}