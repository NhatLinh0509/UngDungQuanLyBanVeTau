package Entity;

public class DangKy {
    private String tenTK; // Tên tài khoản
    private String matKhau; // Mật khẩu
    private String loaiTK; // Loại tài khoản

    // Constructor
    public DangKy(String tenTK, String matKhau, String loaiTK) {
        this.tenTK = tenTK;
        this.matKhau = matKhau;
        this.loaiTK = loaiTK;
    }

    // Getters và Setters
    public String getTenTK() {
        return tenTK;
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

    @Override
    public String toString() {
        return "DangKy [tenTK=" + tenTK + ", matKhau=" + matKhau + ", loaiTK=" + loaiTK + "]";
    }
}
