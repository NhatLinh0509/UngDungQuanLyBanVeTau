package Entity;

import java.util.Date;

public class VeTau {
    private String maVe;
    private String hoTen;
    private String maChuyen;
    private String soDienThoai;
    private String cmnd;
    private String soGhe;       // giữ nguyên L1_TA hoặc N2_TB
    private String gaDi;
    private String gaDen;
    private String loaiGhe;
    private boolean khuHoi;
    private Date ngayDi;
    private double giaTien;
    private String gioKhoiHanh; // giữ nguyên nếu bạn cần

    // Constructor đầy đủ
    public VeTau(String maVe, String hoTen, String maChuyen, String soDienThoai, String cmnd, String soGhe,
                 String gaDi, String gaDen, String loaiGhe, boolean khuHoi, Date ngayDi, double giaTien, String gioKhoiHanh) {
        this.maVe = maVe;
        this.hoTen = hoTen;
        this.maChuyen = maChuyen;
        this.soDienThoai = soDienThoai;
        this.cmnd = cmnd;
        this.soGhe = soGhe;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.loaiGhe = loaiGhe;
        this.khuHoi = khuHoi;
        this.ngayDi = ngayDi;
        this.giaTien = giaTien;
        this.gioKhoiHanh = gioKhoiHanh;
    }

    // Constructor rút gọn (khi thêm vé, chưa có mã vé)
    public VeTau(String hoTen, String maChuyen, String soDienThoai, String cmnd, String soGhe,
                 String gaDi, String gaDen, String loaiGhe, boolean khuHoi, Date ngayDi, double giaTien) {
        this.hoTen = hoTen;
        this.maChuyen = maChuyen;
        this.soDienThoai = soDienThoai;
        this.cmnd = cmnd;
        this.soGhe = soGhe;
        this.gaDi = gaDi;
        this.gaDen = gaDen;
        this.loaiGhe = loaiGhe;
        this.khuHoi = khuHoi;
        this.ngayDi = ngayDi;
        this.giaTien = giaTien;
    }

    // Getters và Setters
    public String getMaVe() {
        return maVe;
    }

    public void setMaVe(String maVe) {
        this.maVe = maVe;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getMaChuyen() {
        return maChuyen;
    }

    public void setMaChuyen(String maChuyen) {
        this.maChuyen = maChuyen;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(String soGhe) {
        this.soGhe = soGhe;
    }



    public String getGaDi() {
        return gaDi;
    }

    public void setGaDi(String gaDi) {
        this.gaDi = gaDi;
    }

    public String getGaDen() {
        return gaDen;
    }

    public void setGaDen(String gaDen) {
        this.gaDen = gaDen;
    }

    public String getLoaiGhe() {
        return loaiGhe;
    }

    public void setLoaiGhe(String loaiGhe) {
        this.loaiGhe = loaiGhe;
    }

    public boolean isKhuHoi() {
        return khuHoi;
    }

    public void setKhuHoi(boolean khuHoi) {
        this.khuHoi = khuHoi;
    }

    public Date getNgayDi() {
        return ngayDi;
    }

    public void setNgayDi(Date ngayDi) {
        this.ngayDi = ngayDi;
    }

    public double getGiaTien() {
        return giaTien;
    }

    public void setGiaTien(double giaTien) {
        this.giaTien = giaTien;
    }
    public String getGioKhoiHanh() {
        return gioKhoiHanh;
    }

    public void setGioKhoiHanh(String gioKhoiHanh) {
        this.gioKhoiHanh = gioKhoiHanh;
    }




	@Override
	 public String toString() {
        return "VeTau [maVe=" + maVe + ", hoTen=" + hoTen + ", maChuyen=" + maChuyen + ", soDienThoai=" + soDienThoai
                + ", cmnd=" + cmnd + ", soGhe=" + soGhe + ", gaDi=" + gaDi + ", gaDen=" + gaDen + ", loaiGhe=" + loaiGhe
                + ", khuHoi=" + khuHoi + ", ngayDi=" + ngayDi + ", giaTien=" + giaTien + ", gioKhoiHanh=" + gioKhoiHanh + "]";
    }

	public void setLoaiTau(String loaiTau) {
		// TODO Auto-generated method stub
		
	}

	public String getLoaiTau() {
		// TODO Auto-generated method stub
		return null;
	}

  
}
