public class Operations {
    public static short subBytes(short data) {
        return Constants.SBOX[data >> 4][data & 0x0f];
    }
}
