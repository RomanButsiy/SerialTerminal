import jssc.SerialPort;
import jssc.SerialPortException;

public class SerialDriver {

    private static SerialPort serialPort;

    public SerialDriver() {
    }



    public boolean initPort(PortAndSpeed portAndSpeed) {
        String port = portAndSpeed.getPort();
        if (port == null) return false;
        serialPort = new SerialPort(port);
        try {
            serialPort.openPort();
            serialPort.setParams(Integer.parseInt(portAndSpeed.getSpeed()),
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);
            //serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
            serialPort.addEventListener(new PortReader(serialPort), SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void write(String text) {
        try {
            serialPort.writeString(text);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }
}
