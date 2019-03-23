import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DataUtils {
    public static Block[] loadFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        byte[] bytes = Files.readAllBytes(path);
        int numberOfBlocks = (int) Math.ceil(bytes.length / (Constants.BLOCK_SIZE * Constants.BLOCK_SIZE)) + 1;
        Block[] blocks = new Block[numberOfBlocks];
        int processed = 0, bi = 0;

        while (processed < bytes.length - 1) {
            short[][] tmp = new short[Constants.BLOCK_SIZE][Constants.BLOCK_SIZE];
            for (int i = 0; i < Constants.BLOCK_SIZE; i++) {
                for (int j = 0; j < Constants.BLOCK_SIZE; j++) {
                    if (processed + 1 < bytes.length) {
                        tmp[j][i] = bytes[processed++];
                    }
                }
            }
            blocks[bi++] = new Block(tmp);
        }

        return blocks;
    }
}
