import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultCaret;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.StringTokenizer;

public class AbstractSerialTerminal  extends JPanel implements ActionListener {
    JButton setButton, sendButton, clearButton;
    ResourceBundle bundle;
    JTextField textField;
    JTextArea textArea;
    JScrollPane scrollPane;
    JCheckBox autoScrollBox, addTimeStampBox;
    JComboBox<String> lineEndings;
    private boolean isStartingLine = true;

    AbstractSerialTerminal(ResourceBundle bundle) {
        this.bundle = bundle;
        setLayout(new BorderLayout());
        textField = new JTextField(40);
        setButton = new JButton("Ініціалізувати Порт");
        sendButton = new JButton(bundle.getString("sendButton"));
        clearButton = new JButton(bundle.getString("clearButton"));
        textArea = new JTextArea();
        textArea.setRows(16);
        textArea.setColumns(40);
        textArea.setEditable(false);
        scrollPane = new JScrollPane(textArea);
        autoScrollBox = new JCheckBox("Автопрокручування", true);
        addTimeStampBox = new JCheckBox("Показати позначки часу", false);
        lineEndings = new JComboBox<>(new String[]{bundle.getString("noEnding"), bundle.getString("newLine"), bundle.getString("carriageReturn"), bundle.getString("bothNL-CR")});
        setButton.addActionListener(this);
    }

    void init() {
        JPanel upperPane = new JPanel();
        JPanel lowerPane = new JPanel();
        JPopupMenu menu = new JPopupMenu();
        add(scrollPane, BorderLayout.CENTER);
        ((DefaultCaret) textArea.getCaret()).setUpdatePolicy(DefaultCaret.NEVER_UPDATE);
        Action cut = new DefaultEditorKit.CutAction();
        cut.putValue(Action.NAME, bundle.getString("popupCut"));
        menu.add(cut);
        Action copy = new DefaultEditorKit.CopyAction();
        copy.putValue(Action.NAME, bundle.getString("popupCopy"));
        menu.add(copy);
        Action paste = new DefaultEditorKit.PasteAction();
        paste.putValue(Action.NAME, bundle.getString("popupPaste"));
        menu.add(paste);
        textField.setComponentPopupMenu(menu);
        upperPane.setLayout(new BoxLayout(upperPane, BoxLayout.X_AXIS));
        upperPane.setBorder(new EmptyBorder(4, 4, 4, 4));
        upperPane.add(textField);
        upperPane.add(Box.createRigidArea(new Dimension(4, 0)));
        upperPane.add(sendButton);
        add(upperPane, BorderLayout.NORTH);
        lowerPane.setLayout(new BoxLayout(lowerPane, BoxLayout.X_AXIS));
        lowerPane.setBorder(new EmptyBorder(4, 4, 4, 4));
        lowerPane.add(setButton);
        lowerPane.add(autoScrollBox);
        lowerPane.add(addTimeStampBox);
        lowerPane.add(Box.createHorizontalGlue());
        lowerPane.add(Box.createRigidArea(new Dimension(8, 0)));
        lowerPane.add(lineEndings);
        lowerPane.add(Box.createRigidArea(new Dimension(8, 0)));
        lowerPane.add(clearButton);
        add(lowerPane, BorderLayout.SOUTH);
    }

    public void onSendCommand(ActionListener listener) {
        textField.addActionListener(listener);
        sendButton.addActionListener(listener);
    }

    public void onClearCommand(ActionListener listener) {
        clearButton.addActionListener(listener);
    }

    protected void onEnableWindow(boolean enable) {
        textArea.setEnabled(enable);
        clearButton.setEnabled(enable);
        scrollPane.setEnabled(enable);
        textField.setEnabled(enable);
        sendButton.setEnabled(enable);
        autoScrollBox.setEnabled(enable);
        addTimeStampBox.setEnabled(enable);
        lineEndings.setEnabled(enable);
    }

    protected void updateTextArea(String msg) {
        if (addTimeStampBox.isSelected()) {
            textArea.append(addTimestamps(msg));
        } else {
            textArea.append(msg);
        }
        if (autoScrollBox.isSelected()) {
            textArea.setCaretPosition(textArea.getDocument().getLength());
        }
    }

    private String addTimestamps(String text) {
        String now = new SimpleDateFormat("HH:mm:ss.SSS -> ").format(new Date());
        final StringBuilder sb = new StringBuilder(text.length() + now.length());
        StringTokenizer tokenizer = new StringTokenizer(text, "\n", true);
        while (tokenizer.hasMoreTokens()) {
            if (isStartingLine) {
                sb.append(now);
            }
            String token = tokenizer.nextToken();
            sb.append(token);
            isStartingLine = token.equals("\n");
        }
        return sb.toString();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
