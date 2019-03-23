import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void loadFile() {
        try {
            Block[] blocks_original = DataUtils.loadFile("data/input.txt");
            Block[] blocks = DataUtils.loadFile("data/input.txt");
            Encryption encryption = new Encryption(blocks, new Key("secretpassword12"));
            encryption.encrypt();
            DataUtils.saveFile(encryption.getBlocks(), "data/input.txt.encrypted");
            System.out.println(blocks_original[0]);

            Decryption decryption = new Decryption(encryption.getBlocks(), new Key("secretpassword12"));
            decryption.decrypt();
            DataUtils.saveFile(decryption.getBlocks(), "data/input.decrypted.txt");

            System.out.println(decryption.getBlocks()[0]);

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}