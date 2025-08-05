package Entity;

public class LoaiGhe {
	private String maLoaiGhe;
	private String tenLoaiGhe;
	public LoaiGhe(String maLoaiGhe, String tenLoaiGhe) {
		super();
		this.maLoaiGhe = maLoaiGhe;
		this.tenLoaiGhe = tenLoaiGhe;
	}
	public LoaiGhe(String maLoaiGhe) {
		super();
		this.maLoaiGhe = maLoaiGhe;
	}
	public LoaiGhe() {
		super();
	}
	public String getMaLoaiGhe() {
		return maLoaiGhe;
	}
	public void setMaLoaiGhe(String maLoaiGhe) {
		this.maLoaiGhe = maLoaiGhe;
	}
	public String getTenLoaiGhe() {
		return tenLoaiGhe;
	}
	public void setTenLoaiGhe(String tenLoaiGhe) {
		this.tenLoaiGhe = tenLoaiGhe;
	}
	@Override
	public String toString() {
		return "LoaiGhe [maLoaiGhe=" + maLoaiGhe + ", tenLoaiGhe=" + tenLoaiGhe + "]";
	}
	
	
	
	

}
