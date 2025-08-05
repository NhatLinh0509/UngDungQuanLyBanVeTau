package connectDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDB {
    private static ConnectDB instance; // Singleton instance
    private static Connection con = null;
    private static final String URL = "jdbc:sqlserver://localhost:1433;databaseName=PTUD;encrypt=true;trustServerCertificate=true;useUnicode=true&characterEncoding=UTF-8;loginTimeout=30";
    private static final String USER = "sa";
    private static final String PASSWORD = "sa12345";

    // Private constructor to prevent instantiation
    private ConnectDB() {}

    // Get the Singleton instance
    public static ConnectDB getInstance() {
        if (instance == null) {
            instance = new ConnectDB();
        }
        return instance;
    }

    // Phương thức kết nối
    public static void connect() throws SQLException {
        if (con == null || con.isClosed()) {
            try {
                con = DriverManager.getConnection(URL, USER, PASSWORD);
                System.out.println("Kết nối thành công!");
            } catch (SQLException e) {
                System.err.println("Không thể kết nối đến cơ sở dữ liệu: " + e.getMessage());
                throw e;
            }
        }
    }
    public static Connection getConnection() {
        try {
            if (con == null || con.isClosed()) {
                connect(); // Tái kết nối nếu cần
            }
        } catch (SQLException e) {
            System.err.println("Lỗi khi lấy kết nối: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return con;
    }

    

    public static void disconnect() {
        if (con != null) {
            try {
                if (!con.isClosed()) {
                    con.close();
                    System.out.println("Đóng kết nối thành công!");
                }
            } catch (SQLException e) {
                System.err.println("Không thể đóng kết nối: " + e.getMessage());
            }
        }
    }
}
