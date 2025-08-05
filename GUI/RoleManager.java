package GUI;

public class RoleManager {
    private static UserRole currentRole;

    public static void setRole(UserRole role) {
        currentRole = role;
    }

    public static UserRole getRole() {
        return currentRole;
    }

    public static boolean isNhanVien() {
        return currentRole == UserRole.NHAN_VIEN;
    }

    public static boolean isQuanLy() {
        return currentRole == UserRole.QUAN_LY;
    }
}
