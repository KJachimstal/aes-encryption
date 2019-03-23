import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OperationsTest {

    @Test
    void subBytes() {
        assertEquals(0xff, Operations.subByte((short) 0x7d));
        assertEquals(0x16, Operations.subByte((short) 0xff));
    }

    void invSubBytes() {
        assertEquals(0x7d, Operations.invSubByte((short) 0xff));
        assertEquals(0xff, Operations.invSubByte((short) 0x16));
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
    void mixColumn() {
        short[] column = new short[] { 0xd4, 0xbf, 0x5d, 0x30 };
        short[] result = Operations.mixColumn(column);
        assertEquals((byte)0x04, result[0]);
        assertEquals((byte)0x66, result[1]);
        assertEquals((byte)0x81, result[2]);
        assertEquals((byte)0xe5, result[3]);

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