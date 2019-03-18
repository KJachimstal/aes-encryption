import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setSize(800, 600);
        setTitle("AES Encryption and Descryption");
        setResizable(false);
        setLayout(null);
        JButton encryptionButton = new JButton("Encrypt");
        encryptionButton.setBounds(70, 460, 100,40);
        add(encryptionButton);
    }


    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
