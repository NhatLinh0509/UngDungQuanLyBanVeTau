package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.sql.Date;

import connectDB.ConnectDB;
import Entity.NhanVien;

public class QLNV_DAO {

    public NhanVien timNhanVienTheoSDT(String sdt) {
        NhanVien nv = null;
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhanVien WHERE sdtNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                nv = new NhanVien(
                    rs.getString("maNV"),
                    rs.getString("tenNV"),
                    0, // bỏ qua tuổi
                    rs.getString("sdtNV"),
                    rs.getDate("namSinh"),
                    rs.getBoolean("phai"),
                    rs.getString("CMND"),
                    rs.getString("chucVu"),
                    rs.getFloat("luong"),
                    rs.getDate("ngayLamViec"),
                    null // bỏ qua TaiKhoan
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return nv;
    }

    public boolean capNhatNhanVien(NhanVien nv) {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "UPDATE NhanVien SET tenNV=?, sdtNV=?, namSinh=?, phai=?, CMND=?, chucVu=?, luong=?, ngayLamViec=? WHERE maNV=?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getTenNV());
            ps.setString(2, nv.getSdtNV());
            ps.setDate(3, nv.getNamSinh());
            ps.setBoolean(4, nv.isPhai());
            ps.setString(5, nv.getCMND());
            ps.setString(6, nv.getChucVu());
            ps.setFloat(7, nv.getLuong());
            ps.setDate(8, nv.getNgayLamViec());
            ps.setString(9, nv.getMaNV());

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean xoaNhanVien(String maNV) {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "DELETE FROM NhanVien WHERE maNV = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maNV);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public boolean themNhanVien(NhanVien nv) {
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "INSERT INTO NhanVien (maNV, tenNV, sdtNV, namSinh, phai, CMND, chucVu, luong, ngayLamViec) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, nv.getMaNV());
            ps.setString(2, nv.getTenNV());
            ps.setString(3, nv.getSdtNV());
            ps.setDate(4, nv.getNamSinh());
            ps.setBoolean(5, nv.isPhai());
            ps.setString(6, nv.getCMND());
            ps.setString(7, nv.getChucVu());
            ps.setFloat(8, nv.getLuong());
            ps.setDate(9, nv.getNgayLamViec());
            int rows = ps.executeUpdate();
            ps.close();
            return rows > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Hàm lấy danh sách nhân viên
    public List<NhanVien> layDanhSachNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT * FROM NhanVien";
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                NhanVien nv = new NhanVien(
                    rs.getString("maNV"),
                    rs.getString("tenNV"),
                    0, // có thể bỏ hoặc cập nhật nếu cần
                    rs.getString("sdtNV"),
                    rs.getDate("namSinh"),
                    rs.getBoolean("phai"),
                    rs.getString("CMND"),
                    rs.getString("chucVu"),
                    rs.getFloat("luong"),
                    rs.getDate("ngayLamViec"),
                    null // nếu có trường khác
                );
                list.add(nv);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
