package Entity;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

public class ChuyenTau {
	private String maChuyenTau;
	private String tenChuyenTau;
	private Date ngayDi;
	private Date ngayDen;
	private Time gioKhoiHanh;
	private Time gioDen;
	private String gaDi;
	private String gaDen;
	private int soLuongVe;
	private String loaiTau; // Loai tau: VEX, ECO, CRN, LUX, TRX

	public ChuyenTau() {
		super();
	}

	public ChuyenTau(String maChuyenTau) {
		super();
		this.maChuyenTau = maChuyenTau;
	}

	public ChuyenTau(String maChuyenTau, String tenChuyenTau, String gaDi, String gaDen) {
		super();
		this.maChuyenTau = maChuyenTau;
		this.tenChuyenTau = tenChuyenTau;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
	}

	public ChuyenTau(String maChuyenTau, String tenChuyenTau, Date ngayDi, Date ngayDen, Time gioKhoiHanh,
			Time gioDen, String gaDi, String gaDen, int soLuongVe, String loaiTau) {
		super();
		this.maChuyenTau = maChuyenTau;
		this.tenChuyenTau = tenChuyenTau;
		this.ngayDi = ngayDi;
		this.ngayDen = ngayDen;
		this.gioKhoiHanh = gioKhoiHanh;
		this.gioDen = gioDen;
		this.gaDi = gaDi;
		this.gaDen = gaDen;
		this.soLuongVe = soLuongVe;
		this.loaiTau = loaiTau;
	}

	public String getMaChuyenTau() {
		return maChuyenTau;
	}

	public void setMaChuyenTau(String maChuyenTau) {
		this.maChuyenTau = maChuyenTau;
	}

	public String getTenChuyenTau() {
		return tenChuyenTau;
	}

	public void setTenChuyenTau(String tenChuyenTau) {
		this.tenChuyenTau = tenChuyenTau;
	}

	public Date getNgayDi() {
		return ngayDi;
	}

	public void setNgayDi(Date ngayDi) {
		this.ngayDi = ngayDi;
	}

	public Date getNgayDen() {
		return ngayDen;
	}

	public void setNgayDen(Date ngayDen) {
		this.ngayDen = ngayDen;
	}

	public Time getGioKhoiHanh() {
		return gioKhoiHanh;
	}

	public void setGioKhoiHanh(Time gioKhoiHanh) {
		this.gioKhoiHanh = gioKhoiHanh;
	}

	public Time getGioDen() {
		return gioDen;
	}

	public void setGioDen(Time gioDen) {
		this.gioDen = gioDen;
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

	public int getSoLuongVe() {
		return soLuongVe;
	}

	public void setSoLuongVe(int soLuongVe) {
		this.soLuongVe = soLuongVe;
	}

	public String getLoaiTau() {
		return loaiTau;
	}

	public void setLoaiTau(String loaiTau) {
		this.loaiTau = loaiTau;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maChuyenTau);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChuyenTau other = (ChuyenTau) obj;
		return Objects.equals(maChuyenTau, other.maChuyenTau);
	}

	@Override
	public String toString() {
		return "ChuyenTau [maChuyenTau=" + maChuyenTau + ", tenChuyenTau=" + tenChuyenTau + ", ngayDi=" + ngayDi
				+ ", ngayDen=" + ngayDen + ", gioKhoiHanh=" + gioKhoiHanh + ", gioDen=" + gioDen + ", gaDi=" + gaDi
				+ ", gaDen=" + gaDen + ", soLuongVe=" + soLuongVe + ", loaiTau=" + loaiTau + "]";
	}
}
