package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedHashMap;
import java.util.Map;

import connectDB.ConnectDB;

public class ThongKe_DAO {

    public Map<String, Double> getDoanhThuTheoTuan(int month, int year) {
        Map<String, Double> result = new LinkedHashMap<>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT DATEPART(WEEK, ngayDi) AS Tuan, " +
                    "SUM(giaTien) AS TongTien " +
                    "FROM [PTUD].[dbo].[DatVeTau] " +
                    "WHERE MONTH(ngayDi) = ? AND YEAR(ngayDi) = ? " +
                    "GROUP BY DATEPART(WEEK, ngayDi) " +
                    "ORDER BY Tuan";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String tuan = "Tuần " + rs.getInt("Tuan");
                double tong = rs.getDouble("TongTien");
                result.put(tuan, tong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Map<String, Integer> getTiLeLoaiVe(int month, int year) {
        Map<String, Integer> result = new LinkedHashMap<>();
        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT loaiGhe, COUNT(*) AS SoLuong " +
                    "FROM [PTUD].[dbo].[DatVeTau] " +
                    "WHERE MONTH(ngayDi) = ? AND YEAR(ngayDi) = ? " +
                    "GROUP BY loaiGhe";

            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String loai = rs.getString("loaiGhe");
                int soLuong = rs.getInt("SoLuong");
                result.put(loai, soLuong);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public Map<String, Integer> getThongKeVeDoiTra(int month, int year) {
        Map<String, Integer> result = new LinkedHashMap<>();
        String sql = "SELECT hinhThuc, COUNT(*) AS soLuong " +
                     "FROM VeDoiTra " +
                     "WHERE MONTH(ngayThaoTac) = ? AND YEAR(ngayThaoTac) = ? " +
                     "GROUP BY hinhThuc";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, month);
            ps.setInt(2, year);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.put(rs.getString("hinhThuc"), rs.getInt("soLuong"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
