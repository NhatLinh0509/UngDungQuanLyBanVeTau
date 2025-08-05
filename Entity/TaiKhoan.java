package Entity;

public class TaiKhoan {
	@Override
	public String toString() {
		return "TaiKhoan [tenTK=" + tenTK + ", matKhau=" + matKhau + ", loaiTK=" + loaiTK + "]";
	}



	private String tenTK;
	private String matKhau;
	private String loaiTK;
	
	
	
	public String getTenTK() {
		return tenTK;
	}



	public TaiKhoan(String tenTK, String matKhau) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
	}



	public void setTenTK(String tenTK) {
		this.tenTK = tenTK;
	}



	public String getMatKhau() {
		return matKhau;
	}



	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}



	public String getLoaiTK() {
		return loaiTK;
	}



	public void setLoaiTK(String loaiTK) {
		this.loaiTK = loaiTK;
	}

	

	public TaiKhoan() {
		super();
	}

	

	public TaiKhoan(String tenTK) {
		super();
		this.tenTK = tenTK;
	}



	public TaiKhoan(String tenTK, String matKhau, String loaiTK) {
		super();
		this.tenTK = tenTK;
		this.matKhau = matKhau;
		this.loaiTK = loaiTK;
	}
	
	

}
