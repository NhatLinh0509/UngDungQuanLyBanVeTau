package Entity;

import java.util.Objects;

public class KhachHang {
	private String maKH;
	private String tenKH;
	private String sdt;
	private String email;
	private boolean phai;
	private String diaChi;
	public KhachHang(String maKH) {
		super();
		this.maKH = maKH;
	}
	public KhachHang() {
		
	}
	public KhachHang(String maKH, String tenKH, String sdt, String email, boolean phai, String diaChi) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.email = email;
		this.phai = phai;
		this.diaChi = diaChi;
	}
	public KhachHang(String maKH, String tenKH, String sdt, boolean phai) {
		super();
		this.maKH = maKH;
		this.tenKH = tenKH;
		this.sdt = sdt;
		this.phai = phai;
	}
	public String getMaKH() {
		return maKH;
	}
	public void setMaKH(String maKH) {
		this.maKH = maKH;
	}
	public String getTenKH() {
		return tenKH;
	}
	public void setTenKH(String tenKH) {
		this.tenKH = tenKH;
	}
	public String getSdt() {
		return sdt;
	}
	public void setSdt(String sdt) {
		this.sdt = sdt;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isPhai() {
		return phai;
	}
	public void setPhai(boolean phai) {
		this.phai = phai;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	@Override
	public String toString() {
		return "KhachHang [maKH=" + maKH + ", tenKH=" + tenKH + ", sdt=" + sdt + ", email=" + email + ", phai=" + phai
				+ ", diaChi=" + diaChi + "]";
	}
	@Override
	public int hashCode() {
		return Objects.hash(maKH);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		KhachHang other = (KhachHang) obj;
		return Objects.equals(maKH, other.maKH);
	}
	
	
}
