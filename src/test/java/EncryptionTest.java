import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileOutputStream;

import static org.junit.jupiter.api.Assertions.*;

class EncryptionTest {

    private Encryption encryption;

    @BeforeEach
    void initialize() {
        encryption = new Encryption();
    }

    @Test
    void loadFile() {
        try {
            byte[] data = encryption.loadFile("data/image.jpg");
            assertNotEquals(0, data.length);

            try (FileOutputStream fos = new FileOutputStream("data/bytes.jpg")) {
                fos.write(data);
            }
        } catch (Exception e) {

        }

    }
}