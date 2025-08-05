package DAO;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import Entity.HoaDon;
import connectDB.ConnectDB;

public class HoaDon_DAO {
	public double tinhTongTien(double giaVe) {
	    double vat = 0.08;
	    return giaVe + (giaVe * vat);
	}


	public void xuatHoaDonHinhAnh(HoaDon hoaDon, String hoTen, String soGhe, String gaDi, String gaDen, String loaiGhe) {
	    try {
	    	
	        // Đường dẫn lưu hình ảnh
	        String directoryPath = "C:\\Users\\nhatl\\Desktop\\AD";
	        String fileName = directoryPath + "\\HoaDon_" + hoaDon.getMaVe() + ".png";

	        // Log dữ liệu đầu vào
	        System.out.println("Dữ liệu truyền vào xuatHoaDonHinhAnh:");
	        System.out.println("Họ tên: " + hoTen);
	        System.out.println("Số ghế: " + soGhe);
	        System.out.println("Ga đi: " + gaDi);
	        System.out.println("Ga đến: " + gaDen);
	        System.out.println("Loại ghế: " + loaiGhe);
	        System.out.println("Tổng tiền: " + hoaDon.getTongTien());
	        System.out.println("Ngày xuất: " + new SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayXuat()));

	        // Kích thước hình ảnh
	        int width = 650;
	        int height = 650;

	        // Tạo hình ảnh và khởi tạo graphics
	        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	        Graphics2D g2d = bufferedImage.createGraphics();

	        // Thiết lập font chữ và căn chỉnh
	        g2d.setColor(Color.WHITE);
	        g2d.fillRect(0, 0, width, height);
	        g2d.setColor(Color.BLACK);
	        g2d.setFont(new Font("Arial", Font.BOLD, 20));

	        // Vẽ tiêu đề hóa đơn
	        g2d.drawString("HÓA ĐƠN VÉ TÀU", 200, 50);

	        // Vẽ thông tin hóa đơn
	        g2d.setFont(new Font("Arial", Font.PLAIN, 16));
	        g2d.drawString("Mã hóa đơn: " + (hoaDon.getMaHoaDon() != null ? hoaDon.getMaHoaDon() : "N/A"), 50, 100);
	        g2d.drawString("Mã vé: " + (hoaDon.getMaVe() != null ? hoaDon.getMaVe() : "N/A"), 50, 130);
	        g2d.drawString("Họ tên: " + (hoTen != null ? hoTen : "N/A"), 50, 160);
	        g2d.drawString("Ga đi: " + (gaDi != null ? gaDi : "N/A"), 50, 190);
	        g2d.drawString("Ga đến: " + (gaDen != null ? gaDen : "N/A"), 50, 220);
	        g2d.drawString("Loại ghế: " + (loaiGhe != null ? loaiGhe : "N/A"), 50, 250);
	        g2d.drawString("Số ghế_Toa: " + (soGhe != null ? soGhe : "N/A"), 50, 280);
	        double tongTien = hoaDon.getTongTien();
	        double giaVe = tongTien / 1.08;
	        double tienVAT = tongTien - giaVe;

	        g2d.drawString("Giá vé: " + String.format("%.0f", giaVe) + " VND", 50, 310);
	        g2d.drawString("VAT (8%): " + String.format("%.0f", tienVAT) + " VND", 50, 340);
	        g2d.drawString("Tổng tiền: " + String.format("%.0f", tongTien) + " VND", 50, 370);
	        g2d.drawString("Ngày xuất: " + new SimpleDateFormat("dd/MM/yyyy").format(hoaDon.getNgayXuat()), 50, 400);

	     // Ghi chú quan trọng
	        g2d.setFont(new Font("Arial", Font.BOLD, 20));
	        g2d.drawString("Khách hàng lưu ý! Kiểm tra rõ hóa đơn", 50, 430);
	        g2d.drawString("Và nếu không nhận được hóa đơn vui lòng liên hệ ", 50, 460);
	        g2d.drawString("CSKH để được miễn phí vé.", 50, 490);
	        g2d.drawString("Chúng tôi xin chân thành cám ơn vì đã ủng hộ.", 50, 520);

	        // Thông tin các ga (dưới dạng phụ chú)
	        g2d.setFont(new Font("Arial", Font.PLAIN, 14));
	        g2d.drawString("Ga Sài Gòn: 01 Nguyễn Thông, Phường 9, Quận 3, TP.HCM.", 50, 550);
	        g2d.drawString("Ga Hà Nội: 120 Lê Duẩn, Quận Hoàn Kiếm, Hà Nội.", 50, 580);
	        g2d.drawString("Ga Đà Nẵng: 791 Hải Phòng, Quận Thanh Khê, Đà Nẵng.", 50, 610);
	        g2d.drawString("Ga Huế: 02 Bùi Thị Xuân, TP. Huế, tỉnh Thừa Thiên Huế.", 50, 640);



	        // Giải phóng graphics và lưu file
	        g2d.dispose();
	        ImageIO.write(bufferedImage, "png", new File(fileName));

	        JOptionPane.showMessageDialog(null, "Hóa đơn đã được xuất ra file hình ảnh: " + fileName);
	    } catch (Exception e) {
	        e.printStackTrace();
	        JOptionPane.showMessageDialog(null, "Xuất file hình ảnh thất bại!");
	    }
	}

    public boolean themHoaDon(HoaDon hoaDon) {
        String sql = "INSERT INTO HoaDon (maHoaDon, maVe, tongTien, ngayXuat) VALUES (?, ?, ?, ?)";
        Connection con = ConnectDB.getConnection();

        try (PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, hoaDon.getMaHoaDon());
            ps.setString(2, hoaDon.getMaVe());
            ps.setDouble(3, hoaDon.getTongTien());
            ps.setDate(4, new java.sql.Date(hoaDon.getNgayXuat().getTime()));

            int result = ps.executeUpdate();
            return result > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
