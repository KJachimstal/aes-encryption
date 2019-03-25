import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("AES Encryption & Decryption");
        Application application = new Application(frame);
        frame.setContentPane(application.mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationByPlatform(true);
        frame.setSize(new Dimension(800, 500));
        frame.setVisible(true);
    }
}
