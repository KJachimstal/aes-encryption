import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Application {

    public JPanel mainPanel;
    private JButton selectFile;
    private JButton button1;
    private JTextArea log;
    private DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public Application() {
        DefaultCaret caret = (DefaultCaret) log.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

        selectFile.addActionListener(e -> openDialog());
        button1.addActionListener(e -> test());
        log("Application started");

    }

    public void openDialog() {
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(mainPanel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(fc.getSelectedFile().getName());
        }
    }

    public void test() {
        log("a");
    }

    private void log(String message) {
        Date date = new Date();
        log.append('[' + dateFormat.format(date) + "]: " + message + '\n');
    }
}
