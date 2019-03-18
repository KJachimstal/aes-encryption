import javax.swing.*;

public class MainFrame extends JFrame {

    public MainFrame(){
        setSize(800, 600);
        setTitle("AES Encryption and Descryption");
    }


    public static void main(String[] args){
        MainFrame mainFrame = new MainFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setVisible(true);
    }
}
