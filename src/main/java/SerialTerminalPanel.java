import jssc.SerialPort;
import jssc.SerialPortException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

public class SerialTerminalPanel extends AbstractSerialTerminal {
    private IGetPortAndSpeed portAndSpeed;
    private SerialDriver serialDriver;
    private SerialPort serialPort;
    private final CommandHistory commandHistory = new CommandHistory(100);
    SerialTerminalPanel(IGetPortAndSpeed portAndSpeed, ResourceBundle bundle) {
        super(bundle);
        this.portAndSpeed = portAndSpeed;
        serialDriver = new SerialDriver();
        onEnableWindow(false);
        onSendCommand((ActionEvent event) -> {
            String command = textField.getText();
            send(command);
            commandHistory.addCommand(command);
            textField.setText("");
        });
        onClearCommand((ActionEvent event) -> textArea.setText(""));
        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP:
                        if (commandHistory.hasPreviousCommand()) {
                            textField.setText(
                                    commandHistory.getPreviousCommand(textField.getText()));
                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (commandHistory.hasNextCommand()) {
                            textField.setText(commandHistory.getNextCommand());
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        textField.setText(commandHistory.resetHistoryLocation());
                        break;
                }
            }
        });
    }

    private void send(String s) {
        if (serialDriver != null) {
            switch (lineEndings.getSelectedIndex()) {
                case 1:
                    s += "\n";
                    break;
                case 2:
                    s += "\r";
                    break;
                case 3:
                    s += "\r\n";
                    break;
                default:
                    break;
            }
            if ("".equals(s) && lineEndings.getSelectedIndex() == 0) {
                JOptionPane.showMessageDialog(null, "Нічого небуло надіслано. Введіть щось!",
                        "Помилка", JOptionPane.ERROR_MESSAGE);
                return;
            }
            serialDriver.write(s);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == setButton) {

            System.out.println(serialDriver.initPort(portAndSpeed.getPortAndSpeed()));
            System.out.println(portAndSpeed.getPortAndSpeed());
            if (serialDriver.isInit()) {
                serialPort = serialDriver.getSerialPort();
                try {
                    serialPort.addEventListener(new PortReader(serialPort, super::updateTextArea), SerialPort.MASK_RXCHAR);
                    onEnableWindow(true);
                } catch (SerialPortException ignored) {
                }
            }
        }
    }

}
