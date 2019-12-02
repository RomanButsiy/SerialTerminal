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
                {"menuPort", "Port: "}
        };
    }
}
