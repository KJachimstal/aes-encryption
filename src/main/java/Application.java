import aes.Block;
import aes.Constants;
import aes.DataUtils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
//    Frame
    JFrame frame;
//    Main panel
    public JPanel mainPanel;

    private JButton inputFile;
    private JButton importCipherKeyButton;
    private JTextArea log;
    private JLabel infoInputFile;
    private JLabel infoBlocksCount;
    private JLabel infoFileSize;
    private JLabel infoBlockSize;
    private JTextArea cipherKey;
    private JButton enterCipherKey;
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    Model
    private JFileChooser inputChooser;
    private Block[] blocks;

    public Application(JFrame frame) {
        this.frame = frame;
        createMenu();

        DefaultCaret caret = (DefaultCaret) log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

//        Buttons
        setIcon(inputFile, "file_in.png");
        setIcon(importCipherKeyButton, "cipher_key.png");
        setIcon(enterCipherKey, "keyboard.png");

//        Info
        infoBlockSize.setText(Constants.BLOCK_SIZE + "x" + Constants.BLOCK_SIZE);

        inputFile.addActionListener(e -> inputFileDialog());
        importCipherKeyButton.addActionListener(e -> test());
    }

    public void inputFileDialog() {
        inputChooser = new JFileChooser();
        int returnValue = inputChooser.showOpenDialog(mainPanel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            String selectedFile = inputChooser.getSelectedFile().getPath();
            try {
                blocks = DataUtils.loadFile(selectedFile);
                String message = blocks.length + " data blocks have been loaded.";
                log("File " + inputChooser.getSelectedFile().getName() + " loaded.");
                log(message);
                infoInputFile.setText(inputChooser.getSelectedFile().getName());
                infoBlocksCount.setText(Integer.toString(blocks.length));
                infoFileSize.setText(blocks.length * Constants.BLOCK_SIZE * Constants.BLOCK_SIZE + "B");
            } catch (IOException ex) {
                String message = "Could not load file: " + selectedFile;
                log(message);
                JOptionPane.showMessageDialog(frame, message, "Loading error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void test() {
        log("a");
    }

    private void log(String message) {
        Date date = new Date();
        log.append('[' + dateFormat.format(date) + "]: " + message + '\n');
    }

    public void dispose() {}

    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();

//        File
        JMenu file = new JMenu("File");
        menuBar.add(file);

        JMenuItem file_item_1 = new JMenuItem("Load file");
        file_item_1.addActionListener(e -> inputFileDialog());

        file.add(file_item_1);

//        Key
        JMenu key = new JMenu("Key");
        menuBar.add(key);

        JMenuItem key_item_1 = new JMenuItem("Import cipher key");
        JMenuItem key_item_2 = new JMenuItem("Export cipher key");
        JMenuItem key_item_3 = new JMenuItem("Enter key from keyboard");

        key.add(key_item_1);
        key.add(key_item_2);
        key.add(key_item_3);

        frame.setJMenuBar(menuBar);
    }

    private void setIcon(JButton button, String path) {
        try {
            Image img = ImageIO.read(getClass().getResource(path));
            img = img.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
            ImageIcon imgIcon = new ImageIcon(img);
            button.setIcon(imgIcon);
        } catch (Exception ex) {
//            ...
            System.out.println(ex);
        }
    }
}
