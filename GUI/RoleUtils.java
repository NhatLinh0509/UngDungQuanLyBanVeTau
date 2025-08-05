package GUI;

import javax.swing.*;

public class RoleUtils {
    public static void disableMenusIfNhanVien(JMenu... menus) {
        if (RoleManager.isNhanVien()) {
            for (JMenu menu : menus) {
                menu.setEnabled(false);
                menu.setToolTipText("Chức năng này không dành cho nhân viên");
            }
        }
    }
}
