package Entity;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNV;
	private String tenNV;
	private int tuoiNV;
	private String sdtNV;
	private Date namSinh;
	private boolean phai;
	private String CMND;
	private String chucVu;
	private float luong;
	private Date ngayLamViec;
	private TaiKhoan taiKhoan;

	public NhanVien(String maNV, String tenNV, int tuoiNV, String sdtNV, Date namSinh, boolean phai, String cMND,
			String chucVu, float luong, Date ngayLamViec, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.tuoiNV = tuoiNV;
		this.sdtNV = sdtNV;
		this.namSinh = namSinh;
		this.phai = phai;
		CMND = cMND;
		this.chucVu = chucVu;
		this.luong = luong;
		this.ngayLamViec = ngayLamViec;
		this.taiKhoan = taiKhoan;
	}

	public NhanVien(String maNV) {
		super();
		this.maNV = maNV;
	}

	public NhanVien(String maNV, String tenNV, Date namSinh, String chucVu, TaiKhoan taiKhoan) {
		super();
		this.maNV = maNV;
		this.tenNV = tenNV;
		this.namSinh = namSinh;
		this.chucVu = chucVu;
		this.taiKhoan = taiKhoan;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getTenNV() {
		return tenNV;
	}

	public void setTenNV(String tenNV) {
		this.tenNV = tenNV;
	}

	public int getTuoiNV() {
		return tuoiNV;
	}

	public void setTuoiNV(int tuoiNV) {
		this.tuoiNV = tuoiNV;
	}

	public String getSdtNV() {
		return sdtNV;
	}

	public void setSdtNV(String sdtNV) {
		this.sdtNV = sdtNV;
	}

	public Date getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(Date namSinh) {
		this.namSinh = namSinh;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public String getCMND() {
		return CMND;
	}

	public void setCMND(String cMND) {
		CMND = cMND;
	}

	public String getChucVu() {
		return chucVu;
	}

	public void setChucVu(String chucVu) {
		this.chucVu = chucVu;
	}

	public float getLuong() {
		return luong;
	}

	public void setLuong(float luong) {
		this.luong = luong;
	}

	public Date getNgayLamViec() {
		return ngayLamViec;
	}

	public void setNgayLamViec(Date ngayLamViec) {
		this.ngayLamViec = ngayLamViec;
	}

	public TaiKhoan getTaiKhoan() {
		return taiKhoan;
	}

	public void setTaiKhoan(TaiKhoan taiKhoan) {
		this.taiKhoan = taiKhoan;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}

	@Override
	public String toString() {
		return "NhanVien [maNV=" + maNV + ", tenNV=" + tenNV + ", tuoiNV=" + tuoiNV + ", sdtNV=" + sdtNV + ", namSinh="
				+ namSinh + ", phai=" + phai + ", CMND=" + CMND + ", chucVu=" + chucVu + ", luong=" + luong
				+ ", ngayLamViec=" + ngayLamViec + ", taiKhoan=" + taiKhoan + "]";
	}

}
