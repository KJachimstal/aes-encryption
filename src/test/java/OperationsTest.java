import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void subBytes() {
        assertEquals(0xff, Operations.subBytes((short) 0x7d));
        assertEquals(0x16, Operations.subBytes((short) 0xff));
    }

    @Test
    void shiftRow() {
        short[] row = new short[] { 1, 2, 3, 4 };
        Operations.shiftRow(row, 2);
        assertEquals(3, row[0]);
        assertEquals(4, row[1]);
        assertEquals(1, row[2]);
    }

    @Test
    void gfMultiplication() {
        try {
            assertEquals((byte)0xA8, Operations.gfMultiplication((byte)0xD4, (byte)0x02));
            assertEquals((byte)0xC1, Operations.gfMultiplication((byte)0xBF, (byte)0x03));
        } catch (Exception ex) {
//            fail(); not working
            assertEquals(false, true);
        }

    }

}