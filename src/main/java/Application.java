import javax.swing.*;

public class Application {

    public JPanel mainPanel;
    private JButton selectFile;

    public Application() {
        selectFile.addActionListener(e -> openDialog());
    }

    public void openDialog() {
        JFileChooser fc = new JFileChooser();
        int returnValue = fc.showOpenDialog(mainPanel);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(fc.getSelectedFile().getName());
        }
    }
}
