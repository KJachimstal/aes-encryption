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

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}