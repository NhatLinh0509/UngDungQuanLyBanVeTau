package Entity;

public class CT_HoaDon {
	private HoaDon hoaDon;
	private VeTau veTau;
	private int soLuong;
	private double donGiaTruocThue;
	private double donGiaSauThue;
	
	public HoaDon getHoaDon() {
		return hoaDon;
	}
	public void setHoaDon(HoaDon hoaDon) {
		this.hoaDon = hoaDon;
	}
	public VeTau getVeTau() {
		return veTau;
	}
	public void setVeTau(VeTau veTau) {
		this.veTau = veTau;
	}
	public CT_HoaDon(HoaDon hoaDon, VeTau veTau, double donGiaTruocThue, double donGiaSauThue) {
		super();
		this.hoaDon = hoaDon;
		this.veTau = veTau;
		this.donGiaTruocThue = donGiaTruocThue;
		this.donGiaSauThue = donGiaSauThue;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public CT_HoaDon(HoaDon hoaDon) {
		super();
		this.hoaDon = hoaDon;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGiaTruocThue() {
		return donGiaTruocThue;
	}
	public void setDonGiaTruocThue(double donGiaTruocThue) {
		this.donGiaTruocThue = donGiaTruocThue;
	}
	public double getDonGiaSauThue() {
		return donGiaSauThue;
	}
	public void setDonGiaSauThue(double donGiaSauThue) {
		this.donGiaSauThue = donGiaSauThue;
	}
	
	public CT_HoaDon(HoaDon hoaDon, VeTau veTau, int soLuong, double donGiaTruocThue, double donGiaSauThue) {
		super();
		this.hoaDon = hoaDon;
		this.veTau = veTau;
		this.soLuong = soLuong;
		this.donGiaTruocThue = donGiaTruocThue;
		this.donGiaSauThue = donGiaSauThue;
	}
	public CT_HoaDon() {
		super();
	}
	public double TinhThanhTien() {
		return soLuong*donGiaSauThue;
	}
	public CT_HoaDon(VeTau veTau, int soLuong, double donGiaTruocThue, double donGiaSauThue) {
		super();
		this.veTau = veTau;
		this.soLuong = soLuong;
		this.donGiaTruocThue = donGiaTruocThue;
		this.donGiaSauThue = donGiaSauThue;
	}
	public CT_HoaDon(VeTau veTau, double donGiaTruocThue, double donGiaSauThue) {
		super();
		this.veTau = veTau;
		this.donGiaTruocThue = donGiaTruocThue;
		this.donGiaSauThue = donGiaSauThue;
	}
	@Override
	public String toString() {
		return "CT_HoaDon [hoaDon=" + hoaDon + ", veTau=" + veTau + ", soLuong=" + soLuong + ", donGiaTruocThue="
				+ donGiaTruocThue + ", donGiaSauThue=" + donGiaSauThue + "]";
	}

	
	
	
	

}
