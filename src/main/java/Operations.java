public class Operations {
    public static short subBytes(short data) {
        return Constants.SBOX[data >> 4][data & 0x0f];
    }

    public static void rotWord(short[] column) {
        short first = column[0];
        for (int i = 0; i < Constants.BLOCK_SIZE - 1; i++) {
            column[i] = column[i + 1];
        }
        column[column.length - 1] = first;
    }

    public static void shiftRow(short[] row, int shift) {
        for (int i = 0; i < shift; i++) {
            Operations.rotWord(row);
        }
    }
}
