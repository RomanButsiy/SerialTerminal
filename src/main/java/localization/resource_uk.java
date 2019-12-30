package localization;

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
                {"menuPort", "Порт: "},
                {"noEnding", "Без закінчення рядка"},
                {"newLine", "Новий рядок (NL)"},
                {"carriageReturn", "Повернення каретки (CR)"},
                {"bothNL-CR", "NL і CR разом"},
                {"autoScrollBox", "Автопрокручування"},
                {"addTimeStampBox", "Показувати позначки часу"},
                {"sendButton", "Надіслати"},
                {"clearButton", "Очистити вивід"},
                {"popupCut", "Вирізати"},
                {"popupCopy", "Копіювати"},
                {"popupPaste", "Вставити"}
        };
    }
}
