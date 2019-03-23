import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class DataUtilsTest {

    @Test
    void loadFile() {
        try {
            Block[] blocks = DataUtils.loadFile("data/image.jpg");
            for(Block b : blocks) {
                System.out.println(b);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}