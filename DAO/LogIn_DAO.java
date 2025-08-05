package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import connectDB.ConnectDB;
import GUI.TrangChuNVien_GUI;
import GUI.TrangChuQuanLy_GUI;

public class LogIn_DAO {

    // Hàm kiểm tra đăng nhập
    public String CheckLogin(String username, String password) {
        Connection con = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        String role = null;

        try {
            ConnectDB.getInstance();
            con = ConnectDB.getConnection();
            if (con != null) {
                String sql = "SELECT loaiTK FROM TaiKhoan WHERE tenTK = ? AND matKhau = ?";
                statement = con.prepareStatement(sql);

                statement.setString(1, username);
                statement.setString(2, password);

                rs = statement.executeQuery();
                if (rs.next()) {
                    role = rs.getString("loaiTK").trim(); // Loại bỏ khoảng trắng
                    System.out.println("Vai trò từ cơ sở dữ liệu: " + role); // Debug log
                } else {
                    System.out.println("Tên tài khoản hoặc mật khẩu không chính xác.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi kiểm tra đăng nhập: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (statement != null) statement.close();
                if (con != null) con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return role;
    }

    // Phương thức hiển thị giao diện tương ứng
    public void showGUI(String role) throws IOException {
        if (role == null) {
            JOptionPane.showMessageDialog(null, "Tên tài khoản hoặc mật khẩu không đúng!!!");
            return;
        }

        switch (role.toLowerCase()) { // Chuyển về chữ thường để so sánh
        case "tài khoản quản lý":
            new TrangChuQuanLy_GUI().setVisible(true);
            break;
        case "tài khoản nhân viên":
            new TrangChuNVien_GUI().setVisible(true);
            break;
        default:
            JOptionPane.showMessageDialog(null, "Vai trò không hợp lệ: " + role);
    }
        System.out.println("Vai trò từ cơ sở dữ liệu: " + role);


    }
}
