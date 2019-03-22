import jdk.jshell.spi.ExecutionControl;

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

    public static void mixColumn(short[] column) {
        for (int i = 0; i < column.length; i++) {
            short sum = 0;
            for (int j = 0; j < column.length; j++) {
                try {
                    sum ^= gfMultiplication((byte)column[j], (byte)j);
                } catch (ExecutionControl.NotImplementedException ex) {
                    System.out.println(ex.getMessage());
                }
            }
            column[i] = sum;
        }
    }

    public static byte gfMultiplication(byte data, byte multiplier)
            throws ExecutionControl.NotImplementedException {
        switch (multiplier) {
            case (byte)0x01:
                return data;
            case (byte)0x02:
                return (byte)(data < 0x80 ? data << 1 : data << 1 % 0x1b);
            case (byte)0x03:
                return (byte)(gfMultiplication(data, (byte)0x02) ^ data);
            default:
                throw new ExecutionControl.NotImplementedException("Multiplier not implemented.");
        }
    }
}
