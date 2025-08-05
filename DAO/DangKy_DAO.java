package DAO;

import Entity.DangKy;
import connectDB.ConnectDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DangKy_DAO {
    public static boolean themTaiKhoan(String tenTK, String matKhau, String loaiTK) {
        try {
            Connection con = ConnectDB.getConnection(); // Lấy kết nối hợp lệ
            String query = "INSERT INTO TaiKhoan (tenTK, matKhau, loaiTK) VALUES (?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, tenTK);
            ps.setString(2, matKhau);
            ps.setString(3, loaiTK);

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<DangKy> layTatCaTaiKhoan() {
        List<DangKy> danhSachTaiKhoan = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnection(); // Đảm bảo kết nối luôn hợp lệ
            if (con != null) {
                String query = "SELECT tenTK, matKhau, loaiTK FROM TaiKhoan";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String tenTK = rs.getString("tenTK");
                    String matKhau = rs.getString("matKhau");
                    String loaiTK = rs.getString("loaiTK");
                    danhSachTaiKhoan.add(new DangKy(tenTK, matKhau, loaiTK));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachTaiKhoan;
    }
}
