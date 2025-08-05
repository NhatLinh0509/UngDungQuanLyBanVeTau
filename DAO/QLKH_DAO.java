package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connectDB.ConnectDB;
import Entity.KhachHang;

public class QLKH_DAO {

    // Lấy thông tin khách hàng theo số điện thoại
    public KhachHang getKhachHangTheoSDT(String sdt) {
        KhachHang kh = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT TOP 1 hoTen, soDienThoai, cmnd FROM DatVeTau WHERE soDienThoai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                kh = new KhachHang();
                kh.setTenKH(rs.getString("hoTen"));
                kh.setSdt(rs.getString("soDienThoai"));
                kh.setDiaChi(rs.getString("cmnd")); // Gán CMND vào diaChi (nếu Entity chưa có getCMND)
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return kh;
    }

    // Cập nhật họ tên và CMND theo số điện thoại
    public boolean capNhatKhachHang(String sdt, String hoTenMoi, String cmndMoi) {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE DatVeTau SET hoTen = ?, cmnd = ? WHERE soDienThoai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hoTenMoi);
            ps.setString(2, cmndMoi);
            ps.setString(3, sdt);

            int rows = ps.executeUpdate();
            ps.close();

            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
