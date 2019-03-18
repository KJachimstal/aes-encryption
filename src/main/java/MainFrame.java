import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){

        //Frame settings
        setSize(800, 600);
        setTitle("AES Encryption and Descryption");
        setResizable(false);
        setLayout(null);

        //Buttons settings
        JButton encryptionButton = new JButton("Encrypt");
        encryptionButton.setBounds(70, 460, 100,40);
        add(encryptionButton);

        JButton decriptionButton = new JButton("Decrypt");
        decriptionButton.setBounds(170, 460, 100,40);
        add(decriptionButton);

        //Text field settings


        //Label settings
        JLabel lCrypt;
        lCrypt = new JLabel("Tekst do szyfrowania:");
        lCrypt.setBounds(30, 0, 140, 30);
        add(lCrypt);

    }


    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
