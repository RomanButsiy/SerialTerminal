import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

public class SerialTerminal {

    // sudo usermod -aG dialout roman

    private static final String[][] resourceBundle = {{"English", "Українська"}, {"en", "uk"}};
    private static final int selectedLanguage = 1;

    private SerialTerminal(String title, ResourceBundle bundle) {
        MenuBar menuBar = new MenuBar(bundle);
        SerialTerminalPanel panel = new SerialTerminalPanel(menuBar::getPortAndSpeed, bundle);
        JFrame frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(menuBar);
        frame.setResizable(true);
        frame.getContentPane().add(panel);
        panel.init();
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("localization.resource", new Locale(resourceBundle[1][selectedLanguage]));
       new SerialTerminal(bundle.getString("titleFrame"), bundle);
    }
}
