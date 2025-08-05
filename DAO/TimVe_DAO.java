package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import connectDB.ConnectDB;

public class TimVe_DAO {

    public List<String[]> timVeTheoSoDienThoai(String sdt) {
        List<String[]> dsVe = new ArrayList<>();

        try {
            Connection con = ConnectDB.getConnection();
            String sql = "SELECT maVe, hoTen, maChuyen, soDienThoai, cmnd, soGhe, gaDi, gaDen, loaiGhe, khuHoi, ngayDi, giaTien "
                       + "FROM DatVeTau WHERE soDienThoai = ?";
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String[] row = new String[12];
                row[0] = rs.getString("maVe");
                row[1] = rs.getString("hoTen");
                row[2] = rs.getString("maChuyen");
                row[3] = rs.getString("soDienThoai");
                row[4] = rs.getString("cmnd");
                row[5] = rs.getString("soGhe");
                row[6] = rs.getString("gaDi");
                row[7] = rs.getString("gaDen");
                row[8] = rs.getString("loaiGhe");
                row[9] = rs.getInt("khuHoi") == 1 ? "Có" : "Không";
                row[10] = rs.getString("ngayDi");
                row[11] = String.valueOf(rs.getInt("giaTien"));
                dsVe.add(row);
            }

            rs.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return dsVe;
    }
}
