import java.util.Arrays;

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
            rotWord(row);
        }
    }

    public static short[] mixColumn(short[] column) {
//        Temporary array for results
        short[] output = new short[column.length];

//        Assign values
        for (int i = 0; i < column.length; i++) {
            short sum = 0;
            for (int j = 0; j < column.length; j++) {
                try {
                    sum ^= gfMultiplication(column[j], (byte)Constants.GALOIS[i][j]);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            output[i] = sum;
        }
        return output;
    }

    public static short gfMultiplication(short data, byte multiplier)
            throws Exception {
        switch (multiplier) {
            case (byte)0x01:
                return (byte)data;
            case (byte)0x02:
                if (data < 0x80) {
                    return (byte)((byte)data << 1);
                } else {
                    return (byte)((byte)(data << 1) ^ (byte)0x1b);
                }
            case (byte)0x03:
                byte x = (byte)gfMultiplication(data, (byte)0x02);
                return (byte)(x ^ (byte)data);
            default:
                throw new Exception("Multiplier not implemented.");
        }
    }
}
