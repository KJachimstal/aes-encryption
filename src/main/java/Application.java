import aes.Block;
import aes.DataUtils;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {
//    Frame
    JFrame frame;
//    Main panel
    public JPanel mainPanel;

    private JButton selectFile;
    private JButton button1;
    private JTextArea log;
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

//    Model
    private JFileChooser inputChooser;
    private Block[] blocks;

    public Application(JFrame frame) {
        this.frame = frame;
        createMenu();

        DefaultCaret caret = (DefaultCaret) log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        selectFile.addActionListener(e -> inputFileDialog());
        button1.addActionListener(e -> test());
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

        JMenuItem key_item_1 = new JMenuItem("Import key");
        JMenuItem key_item_2 = new JMenuItem("Export key");

        key.add(key_item_1);
        key.add(key_item_2);

        frame.setJMenuBar(menuBar);
    }
}
