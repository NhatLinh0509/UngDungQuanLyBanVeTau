package DAO;

import connectDB.ConnectDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Entity.ChuyenTau;

public class QLChuyenTau_DAO {
    
    // Lấy danh sách toàn bộ chuyến tàu (có thêm cột loaiTau)
    public static List<ChuyenTau> danhSachChuyenTau() {
        List<ChuyenTau> ds = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnection(); // Đảm bảo kết nối luôn hợp lệ
            if (con != null) {
                String query = "SELECT maChuyen, ngayDi, gaDi, gaDen, gioKhoiHanh, loaiTau FROM ChuyenTau";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    ChuyenTau rec = new ChuyenTau();
                    rec.setMaChuyenTau(rs.getString("maChuyen"));
                    rec.setNgayDi(rs.getDate("ngayDi"));
                    rec.setGaDi(rs.getString("gaDi"));
                    rec.setGaDen(rs.getString("gaDen"));
                    rec.setGioKhoiHanh(rs.getTime("gioKhoiHanh"));
                    rec.setLoaiTau(rs.getString("loaiTau"));  // Lấy thêm loại tàu
                    ds.add(rec);
                }
                rs.close();
                stmt.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ds;
    }

    // Cập nhật 1 chuyến tàu (có cập nhật loại tàu)
    public void capNhatChuyenTau(ChuyenTau ct) throws SQLException {
        String sql = "UPDATE ChuyenTau SET ngayDi=?, gaDi=?, gaDen=?, gioKhoiHanh=?, loaiTau=? WHERE maChuyen=?";

        try (Connection conn = ConnectDB.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, ct.getNgayDi());
            pstmt.setString(2, ct.getGaDi());
            pstmt.setString(3, ct.getGaDen());
            pstmt.setTime(4, ct.getGioKhoiHanh());
            pstmt.setString(5, ct.getLoaiTau());  // Cập nhật loại tàu
            pstmt.setString(6, ct.getMaChuyenTau());

            pstmt.executeUpdate();
        }
    }

    // Cập nhật nhiều chuyến tàu cùng lúc trong transaction
    public void capNhatTatCaCT(List<ChuyenTau> dsCT) throws SQLException {
        try (Connection conn = ConnectDB.getConnection()) {
            conn.setAutoCommit(false);

            try {
                for (ChuyenTau chuyenTau : dsCT) {
                    capNhatChuyenTau(chuyenTau);
                }
                conn.commit();
            } catch (SQLException e) {
                conn.rollback();
                throw e;
            }
        }
    }

    // Thêm chuyến tàu mới (có thêm loại tàu)
    public boolean themChuyenTau(ChuyenTau ct) {
        String sql = "INSERT INTO ChuyenTau(maChuyen, ngayDi, gaDi, gaDen, gioKhoiHanh, loaiTau) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, ct.getMaChuyenTau());
            ps.setDate(2, ct.getNgayDi());
            ps.setString(3, ct.getGaDi());
            ps.setString(4, ct.getGaDen());
            ps.setTime(5, ct.getGioKhoiHanh());
            ps.setString(6, ct.getLoaiTau());  // Thêm loại tàu
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Xóa chuyến tàu theo mã chuyến
    public boolean xoaChuyenTau(String maChuyen) {
        String sql = "DELETE FROM ChuyenTau WHERE maChuyen = ?";
        try (Connection conn = ConnectDB.getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, maChuyen);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
