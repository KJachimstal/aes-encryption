import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void loadFile() {
        try {
            Block[] blocks = DataUtils.loadFile("data/image.jpg");
            Encryption encryption = new Encryption(blocks, new Key("secretpassword12"));
            encryption.encrypt();
            DataUtils.saveFile(encryption.getBlocks(), "data/output.encrypted");

            Block[] blocks_encrypted = DataUtils.loadFile("data/output.encrypted");
            Decryption decryption = new Decryption(blocks_encrypted, new Key("secretpassword12"));
            decryption.decrypt();
            DataUtils.saveFile(decryption.getBlocks(), "data/output_decrypted.jpg");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}