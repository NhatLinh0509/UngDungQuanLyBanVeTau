package Entity;

import java.util.Date;

public class HoaDon {
    private String maHoaDon;
    private String maVe;
    private Date ngayXuat;
    private double tongTien;

    public HoaDon(String maHoaDon, String maVe, Date ngayXuat, double tongTien) {
        this.maHoaDon = maHoaDon;
        this.maVe = maVe;
        this.ngayXuat = ngayXuat;
        this.tongTien = tongTien;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public Date getNgayXuat() {
        return ngayXuat;
    }

    public void setNgayXuat(Date ngayXuat) {
        this.ngayXuat = ngayXuat;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public String toString() {
        return "HoaDon{" +
                "maHoaDon='" + maHoaDon + '\'' +
                ", maVe='" + maVe + '\'' +
                ", ngayXuat=" + ngayXuat +
                ", tongTien=" + tongTien +
                '}';
    }
}
