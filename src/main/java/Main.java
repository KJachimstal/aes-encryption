import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("AES Encryption & Decryption");
        frame.setContentPane(new Application().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
