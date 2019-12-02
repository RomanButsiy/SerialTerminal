import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class PortReader implements SerialPortEventListener {

    private SerialPort serialPort;

    public PortReader(SerialPort serialPort) {
        this.serialPort = serialPort;
    }

    @Override
    public void serialEvent(SerialPortEvent serialPortEvent) {
        if(serialPortEvent.isRXCHAR() && serialPortEvent.getEventValue() > 0){
            try {
                String data = serialPort.readString(serialPortEvent.getEventValue());
                System.out.println(data);
            }
            catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }
}
