public class Key {
    private int rounds;
    private short[] data;

    public Key(String key) {
        data = new short[key.length()];
        char[] chars = key.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            data[i] = (short) chars[i];
        }
        rounds = calcRounds(data.length);
    }

    private int calcRounds(int key_length) {
        switch (key_length) {
            case 24:
                return 12;
            case 32:
                return 14;
            default:
                return 10;
        }
    }

    public int getRounds() {
        return rounds;
    }
}
