import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Encryption {
    public byte[] loadFile(String filename) throws IOException {
        Path path = Paths.get(filename);
        return Files.readAllBytes(path);
    }
}
