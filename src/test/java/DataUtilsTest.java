import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void loadFile() {
        try {
            //Block[] blocks_original = DataUtils.loadFile("data/input.txt");
            Block[] blocks = DataUtils.loadFile("data/image.jpg");
            Encryption encryption = new Encryption(blocks, new Key("secretpassword12"));
            encryption.encrypt();
            DataUtils.saveFile(encryption.getBlocks(), "data/input.encrypted.jpg");

            Block[] encrypted_blocks = DataUtils.loadFile("data/input.encrypted.jpg");

            System.out.println(encryption.getBlocks()[0]);
            System.out.println(encrypted_blocks[0]);
            Decryption decryption = new Decryption(encrypted_blocks, new Key("secretpassword12"));
            decryption.decrypt();
            DataUtils.saveFile(decryption.getBlocks(), "data/input.decrypted.jpg");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}