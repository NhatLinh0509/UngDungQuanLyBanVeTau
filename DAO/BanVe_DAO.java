package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import Entity.VeTau;
import Entity.HoaDon;
import connectDB.ConnectDB;

public class BanVe_DAO {

	public boolean themVe(VeTau ve) throws SQLException {
	    Connection con = ConnectDB.getConnection();
	    if (con == null || con.isClosed()) {
	        throw new SQLException("Không thể kết nối cơ sở dữ liệu. Kết nối bị đóng.");
	    }

	    java.sql.Date sqlNgayDi = new java.sql.Date(ve.getNgayDi().getTime());

	    // Tách mã chuyến gốc (không kèm giờ khởi hành)
	    String maChuyenThuan = ve.getMaChuyen().split("_")[0];
	    ve.setMaVe(generateMaVe(con, maChuyenThuan, sqlNgayDi));

	    String sqlDatVe = "INSERT INTO DatVeTau (maVe, hoTen, maChuyen, soDienThoai, cmnd, soGhe, gaDi, gaDen, loaiGhe, khuHoi, ngayDi, giaTien) " +
	                      "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

	    try {
	        con.setAutoCommit(false); // Bắt đầu transaction thủ công

	        try (PreparedStatement psDatVe = con.prepareStatement(sqlDatVe)) {
	            psDatVe.setString(1, ve.getMaVe());
	            psDatVe.setString(2, ve.getHoTen());
	            psDatVe.setString(3, maChuyenThuan); // bỏ giờ khởi hành
	            psDatVe.setString(4, ve.getSoDienThoai());
	            psDatVe.setString(5, ve.getCmnd());
	            psDatVe.setString(6, ve.getSoGhe()); // VD: "L2_TA", giữ nguyên
	            psDatVe.setString(7, ve.getGaDi());
	            psDatVe.setString(8, ve.getGaDen());
	            psDatVe.setString(9, ve.getLoaiGhe());
	            psDatVe.setBoolean(10, ve.isKhuHoi());
	            psDatVe.setDate(11, sqlNgayDi);
	            psDatVe.setDouble(12, ve.getGiaTien());

	            psDatVe.executeUpdate();

	            // ✅ Cập nhật trạng thái ghế nếu cần
	            if (!capNhatTrangThaiGhe(con, maChuyenThuan, ve.getSoGhe(), true)) {
	                con.rollback();
	                System.out.println("Rollback do lỗi cập nhật trạng thái ghế!");
	                return false;
	            }

	            con.commit();
	            return true;
	        } catch (SQLException e) {
	            con.rollback();
	            System.err.println("Rollback do lỗi SQL: " + e.getMessage());
	            throw e;
	        }
	    } finally {
	        con.setAutoCommit(true);
	    }
	}



    public Double tinhGiaTien(String gaDi, String gaDen, String loaiGhe, boolean khuHoi) {
        String sql = "SELECT giaTienNgoi, giaTienNam FROM ChuyenTau WHERE gaDi = ? AND gaDen = ?";
        double giaTien = -1;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gaDi);
            ps.setString(2, gaDen);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    if ("Ngồi".equals(loaiGhe)) {
                        giaTien = rs.getDouble("giaTienNgoi");
                    } else if ("Nằm".equals(loaiGhe)) {
                        giaTien = rs.getDouble("giaTienNam");
                    }
                }
            }
            if (giaTien != -1 && khuHoi) {
                giaTien *= 2;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return giaTien;
    }
    public List<String> layDanhSachMaChuyen() {
        List<String> danhSachMaChuyen = new ArrayList<>();
        String sql = "SELECT TOP 5 dv.*, CONVERT(VARCHAR, ct.gioKhoiHanh, 108) AS gioKhoiHanh " +
                "FROM DatVeTau dv " +
                "JOIN ChuyenTau ct ON dv.maChuyen = ct.maChuyen " +
                "ORDER BY ngayDi DESC";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
        	while (rs.next()) {
        	    String maChuyen = rs.getString("maChuyen");
        	    String gioKhoiHanh = rs.getString("gioKhoiHanh");
        	    System.out.println("maChuyen: " + maChuyen + ", gioKhoiHanh: " + gioKhoiHanh);

        	    if (maChuyen != null && gioKhoiHanh != null) {
        	        danhSachMaChuyen.add(maChuyen + "_" + gioKhoiHanh);
        	    } else {
        	        System.err.println("Lỗi: maChuyen hoặc gioKhoiHanh bị null!");
        	    }
        	}
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachMaChuyen;
    }
    public List<String> layDanhSachGheTrong(String maChuyen, String loaiGhe) {
        List<String> danhSach = new ArrayList<>();
        Connection con = ConnectDB.getConnection();
        String sql = "SELECT soThuTu, toa FROM SoGhe WHERE maChuyen = ? AND loaiGhe = ? AND trangThai = 0";
        try (PreparedStatement stmt = con.prepareStatement(sql)) {
            stmt.setString(1, maChuyen);
            stmt.setString(2, loaiGhe);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int soGhe = rs.getInt("soThuTu");
                String toa = rs.getString("toa");
                String loai = loaiGhe.equalsIgnoreCase("Nằm") ? "L" : "N";
                danhSach.add(loai + soGhe + "_T" + toa); // VD: L1_TA
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSach;
    }



    public List<String> layDanhSachMaChuyenTheoGa(String gaDi, String gaDen) {
        List<String> danhSachMaChuyen = new ArrayList<>();
        String sql = "SELECT maChuyen, CONVERT(CHAR(5), gioKhoiHanh, 108) AS gioKhoiHanh FROM ChuyenTau WHERE gaDi = ? AND gaDen = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, gaDi);
            ps.setString(2, gaDen);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maChuyen = rs.getString("maChuyen");
                    String gioKhoiHanh = rs.getString("gioKhoiHanh");
                    
                    // Debug dữ liệu lấy từ DB
                    System.out.println("maChuyen: " + maChuyen + ", gioKhoiHanh: " + gioKhoiHanh);

                    if (maChuyen != null && gioKhoiHanh != null) {
                        danhSachMaChuyen.add(maChuyen + "_" + gioKhoiHanh);
                    } else {
                        System.err.println("Dữ liệu null: maChuyen=" + maChuyen + ", gioKhoiHanh=" + gioKhoiHanh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachMaChuyen;
    }


    public String layMaChuyen(Date ngayDi, String gaDi, String gaDen) {
        String sql = "SELECT maChuyen FROM ChuyenTau WHERE ngayDi = ? AND gaDi = ? AND gaDen = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, ngayDi);
            ps.setString(2, gaDi);
            ps.setString(3, gaDen);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getString("maChuyen");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<String> layGheDaDat(String maChuyen, String loaiGhe) {
        List<String> gheDaDat = new ArrayList<>();
        String sql = "SELECT maGhe FROM SoGhe WHERE maChuyen = ? AND loaiGhe = ? AND trangThai = 1";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maChuyen);
            ps.setString(2, loaiGhe);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    gheDaDat.add(rs.getString("maGhe"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return gheDaDat;
    }

    public List<String> layDanhSachMaChuyenTheoNgay(Date ngayDi) {
        List<String> danhSachMaChuyen = new ArrayList<>();
        String sql = "SELECT maChuyen, CONVERT(CHAR(5), gioKhoiHanh, 108) AS gioKhoiHanh FROM ChuyenTau WHERE ngayDi = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setDate(1, ngayDi);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    String maChuyen = rs.getString("maChuyen");
                    String gioKhoiHanh = rs.getString("gioKhoiHanh");
                    if (maChuyen != null && gioKhoiHanh != null) {
                        danhSachMaChuyen.add(maChuyen + "_" + gioKhoiHanh);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachMaChuyen;
    }
    public boolean checkMaVeExists(String maVe) {
        String sql = "SELECT 1 FROM DatVeTau WHERE maVe = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVe);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public String generateMaVe(Connection con, String maChuyen, java.sql.Date date) throws SQLException {
        String sql = "SELECT COUNT(*) + 1 AS soThuTu FROM DatVeTau WHERE maChuyen = ? AND ngayDi = ?";
        String maVe = null;

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maChuyen);
            ps.setDate(2, date);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int soThuTu = rs.getInt("soThuTu");
                    String ngayFormat = new SimpleDateFormat("ddMMyyyy").format(date);
                    maVe = ngayFormat + maChuyen + String.format("%04d", soThuTu);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Lỗi khi tạo mã vé: " + e.getMessage());
        }

        if (maVe == null || maVe.isEmpty()) {
            throw new SQLException("Không thể tạo mã vé. Vui lòng kiểm tra dữ liệu đầu vào.");
        }
        return maVe;
    }
    public boolean capNhatTrangThaiGhe(Connection con, String maChuyen, String soGhe, boolean daDat) throws SQLException {
        String sql = "UPDATE SoGhe SET trangThai = ? WHERE maChuyen = ? AND loaiGhe = ? AND soThuTu = ?";
        int rowsAffected;

        // Tách loại ghế (Nằm/Ngồi) và số thứ tự đúng cách từ "L1_TA"
        String loaiGhe = soGhe.startsWith("L") ? "Nằm" : "Ngồi";

        int soThuTu;
        try {
            // Cách 1: Tách theo dấu "_" nếu có định dạng như "L1_TA"
            if (soGhe.contains("_")) {
                String chiSo = soGhe.substring(1, soGhe.indexOf("_")); // "1" trong "L1_TA"
                soThuTu = Integer.parseInt(chiSo);
            } else {
                // Cách 2: fallback – giữ an toàn nếu format khác
                soThuTu = Integer.parseInt(soGhe.replaceAll("\\D+", ""));
            }
        } catch (NumberFormatException ex) {
            throw new SQLException("Lỗi khi chuyển đổi số ghế. Dữ liệu không đúng định dạng số: " + soGhe);
        }

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, daDat ? 1 : 0);
            ps.setString(2, maChuyen);
            ps.setString(3, loaiGhe);
            ps.setInt(4, soThuTu);

            rowsAffected = ps.executeUpdate();
        }

        return rowsAffected > 0;
    }
    public List<VeTau> layDanhSachVe() {
        List<VeTau> danhSachVe = new ArrayList<>();
        String sql = "SELECT dv.*, CONVERT(VARCHAR, ct.gioKhoiHanh, 108) AS gioKhoiHanh, ct.loaiTau " +
                     "FROM DatVeTau dv JOIN ChuyenTau ct ON dv.maChuyen = ct.maChuyen";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
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
                ve.setGioKhoiHanh(rs.getString("gioKhoiHanh"));
                ve.setLoaiTau(rs.getString("loaiTau"));
                danhSachVe.add(ve);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachVe;
    }

    public boolean capNhatVe(VeTau ve) throws SQLException {
        String sql = "UPDATE DatVeTau SET hoTen = ?, soDienThoai = ?, cmnd = ?, soGhe = ?, gaDi = ?, gaDen = ?, loaiGhe = ?, khuHoi = ?, ngayDi = ?, giaTien = ? WHERE maVe = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, ve.getHoTen());
            ps.setString(2, ve.getSoDienThoai());
            ps.setString(3, ve.getCmnd());
            ps.setString(4, ve.getSoGhe());
            ps.setString(5, ve.getGaDi());
            ps.setString(6, ve.getGaDen());
            ps.setString(7, ve.getLoaiGhe());
            ps.setBoolean(8, ve.isKhuHoi());
            ps.setDate(9, new java.sql.Date(ve.getNgayDi().getTime()));
            ps.setDouble(10, ve.getGiaTien());
            ps.setString(11, ve.getMaVe());

            return ps.executeUpdate() > 0;
        }
    }
    /*public boolean xoaVe(String maVe) {
        String sql = "DELETE FROM DatVeTau WHERE maVe = ?";
        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maVe);

            // Thực thi câu lệnh SQL
            return ps.executeUpdate() > 0; // Trả về true nếu xóa thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu xóa thất bại
    }
    public boolean suaVe(VeTau ve) {
        String sql = "UPDATE DatVeTau SET hoTen = ?, soDienThoai = ?, cmnd = ?, soGhe = ?, gaDi = ?, gaDen = ?, loaiGhe = ?, khuHoi = ?, ngayDi = ?, giaTien = ? WHERE maVe = ?";
        Connection con = ConnectDB.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, ve.getHoTen());
            ps.setString(2, ve.getSoDienThoai());
            ps.setString(3, ve.getCmnd());
            ps.setString(4, ve.getSoGhe());
            ps.setString(5, ve.getGaDi());
            ps.setString(6, ve.getGaDen());
            ps.setString(7, ve.getLoaiGhe());
            ps.setBoolean(8, ve.isKhuHoi());
            ps.setDate(9, new java.sql.Date(ve.getNgayDi().getTime()));
            ps.setDouble(10, ve.getGiaTien());
            ps.setString(11, ve.getMaVe());

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; // Trả về true nếu cập nhật thành công
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }*/
    public String layGioKhoiHanh(String maChuyen) {
        String sql = "SELECT CONVERT(VARCHAR, gioKhoiHanh, 108) AS gioKhoiHanh FROM ChuyenTau WHERE maChuyen = ?";
        String gioKhoiHanh = null;

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, maChuyen);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    gioKhoiHanh = rs.getString("gioKhoiHanh");
                    System.out.println("Giờ khởi hành từ DB: " + gioKhoiHanh);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return gioKhoiHanh;
    }

    public List<String> layDanhSachMaChuyenTheoNgayVaGa(Date ngayDi, String gaDi, String gaDen) {
        List<String> danhSachMaChuyen = new ArrayList<>();
        try {
            Connection con = ConnectDB.getInstance().getConnection();

            System.out.println("=== DEBUG PARAM ===");
            System.out.println("ngayDi: " + ngayDi);
            System.out.println("gaDi: " + gaDi);
            System.out.println("gaDen: " + gaDen);

            String sql = "SELECT maChuyen, gioKhoiHanh, loaiTau FROM ChuyenTau WHERE CAST(ngayDi AS DATE) = ? AND gaDi = ? AND gaDen = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDate(1, ngayDi);
            stmt.setString(2, gaDi);
            stmt.setString(3, gaDen);

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String maChuyen = rs.getString("maChuyen");
                Time gioKhoiHanh = rs.getTime("gioKhoiHanh");
                String loaiTau = rs.getString("loaiTau");

                String gioFormatted = gioKhoiHanh.toString().substring(0, 5);
                String maChuyenHienThi = maChuyen + "_" + gioFormatted + "_" + loaiTau;

                danhSachMaChuyen.add(maChuyenHienThi);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return danhSachMaChuyen;
    }

}
