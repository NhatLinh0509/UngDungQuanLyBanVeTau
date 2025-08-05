package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Entity.VeTau;
import connectDB.ConnectDB;

public class Trade_DAO {

    public VeTau timVeTheoMa(String maVe) {
        String sql = "SELECT dv.*, CONVERT(VARCHAR, ct.gioKhoiHanh, 108) AS gioKhoiHanh " +
                     "FROM DatVeTau dv JOIN ChuyenTau ct ON dv.maChuyen = ct.maChuyen " +
                     "WHERE maVe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maVe);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    VeTau ve = new VeTau(
                        rs.getString("maVe"),
                        rs.getString("hoTen"),
                        rs.getString("maChuyen"),
                        rs.getString("soDienThoai"),
                        rs.getString("cmnd"),
                        rs.getString("soGhe"),
                        rs.getString("gaDi"),
                        rs.getString("gaDen"),
                        rs.getString("loaiGhe"),
                        rs.getBoolean("khuHoi"),
                        rs.getDate("ngayDi"),
                        rs.getDouble("giaTien"),
                        rs.getString("gioKhoiHanh")
                    );
                    // Nối thêm giờ nếu cần dùng trong combo
                    ve.setMaChuyen(rs.getString("maChuyen") + "_" + rs.getString("gioKhoiHanh"));
                    return ve;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean capNhatVe(VeTau ve) {
        String sql = "UPDATE DatVeTau SET gaDi = ?, gaDen = ?, ngayDi = ? WHERE maVe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ve.getGaDi());
            ps.setString(2, ve.getGaDen());
            ps.setDate(3, new java.sql.Date(ve.getNgayDi().getTime()));
            ps.setString(4, ve.getMaVe());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaVe(String maVe) {
        String sql = "DELETE FROM DatVeTau WHERE maVe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, maVe);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean luuVeDoiTra(String maVe, String hinhThuc, String lyDo) {
        String sql = "INSERT INTO VeDoiTra (maVe, hinhThuc, lyDo) VALUES (?, ?, ?)";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVe);
            ps.setString(2, hinhThuc);
            ps.setString(3, lyDo);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}