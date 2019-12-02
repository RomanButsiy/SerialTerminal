import jssc.SerialPortList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class SerialTerminalPanel extends JPanel implements ActionListener {
    private IGetPortAndSpeed portAndSpeed;
    private JButton setButton, resetButton;
    private JTextField textField;
    private SerialDriver serialDriver;
    public SerialTerminalPanel(IGetPortAndSpeed portAndSpeed, ResourceBundle bundle) {
        this.portAndSpeed = portAndSpeed;
        serialDriver = new SerialDriver();
        setLayout(new GridBagLayout());
        textField = new JTextField("Some Text");
        setButton = new JButton("Ініціалізувати Порт");
        resetButton = new JButton("Надіслати Щось");
        setButton.addActionListener(this);
        resetButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setButton) {
            System.out.println(serialDriver.initPort(portAndSpeed.getPortAndSpeed()));
            System.out.println(portAndSpeed.getPortAndSpeed());
        }
        if (e.getSource() == resetButton) {
            serialDriver.write(textField.getText());
        }
    }

    public void init() {
        Insets insets = new Insets(2, 2, 2, 2);
        add(textField, new GridBagConstraints(0, 0, 2, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL,
                new Insets(10, 10, 10, 10), 0, 0));
        add(setButton, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
        add(resetButton, new GridBagConstraints(1, 1, 1, 1, 1, 1,
                GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, insets, 0, 0));
    }
}
