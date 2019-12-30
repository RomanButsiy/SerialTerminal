package localization;

import java.util.ListResourceBundle;

public class resource_en extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][] {
                {"OkKey", "Ok"},
                {"CancelKey", "Cancel"},
                {"titleFrame", "Serial terminal"},
                {"menuFile", "File"},
                {"menuOpenFile", "Open file"},
                {"menuExit", "Exit"},
                {"menuTitleTools", "Tools"},
                {"menuSpeed", "Speed: "},
                {"menuPort", "Port: "},
                {"noEnding", "No line ending"},
                {"newLine", "New line"},
                {"carriageReturn", "Carriage return"},
                {"bothNL-CR", "Both NL & CR"},
                {"autoScrollBox", "Auto scroll"},
                {"addTimeStampBox", "Show timestamp"},
                {"sendButton", "Send"},
                {"clearButton", "Clear output"},
                {"popupCut", "Cut"},
                {"popupCopy", "Copy"},
                {"popupPaste", "Paste"}
        };
    }
}
