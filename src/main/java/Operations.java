public class Operations {

    private static short _subByte(short data, boolean inverse) {
        int x = (data & 0xf0) >> 4;
        int y = (data & 0x0f);
        return (inverse ? Constants.INVSBOX[x][y] : Constants.SBOX[x][y]);
    }

    public static short subByte(short data) {
        return _subByte(data, false);
    }

    public static short invSubByte(short data) {
        return _subByte(data, true);
    }

    public static void rotWord(short[] column) {
        short first = column[0];
        for (int i = 0; i < Constants.BLOCK_SIZE - 1; i++) {
            column[i] = column[i + 1];
        }
        column[column.length - 1] = first;
    }

    public static void invRotWord(short[] column) {
        short last = column[column.length - 1];
        for (int i = column.length - 1; i > 0; i--) {
            column[i] = column[i - 1];
        }
        column[0] = last;
    }

    public static void shiftRow(short[] row, int shift) {
        for (int i = 0; i < shift; i++) {
            rotWord(row);
        }
    }

    public static void invShiftRow(short[] row, int shift) {
        for (int i = 0; i < shift; i++) {
            invRotWord(row);
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

    public static String hexToString(short[][] data) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[0].length; j++) {
                sb.append(String.format("0x%02X", data[i][j]) + " ");
            }
            sb.append("\n");
        }

        return sb.toString() + "\n";
    }
}
