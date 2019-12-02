import java.util.ListResourceBundle;

public class resource_uk extends ListResourceBundle {
    protected Object[][] getContents() {
        return new Object[][] {
                {"OkKey", "Ok"},
                {"CancelKey", "Cancel"},
                {"titleFrame", "Послідовний термінал"},
                {"menuFile", "Файл"},
                {"menuOpenFile", "Відкрити файл"},
                {"menuExit", "Вихід"},
                {"menuTitleTools", "Інструменти"},
                {"menuSpeed", "Швидуість: "},
                {"menuPort", "Порт: "}
        };
    }
}
