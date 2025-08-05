package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.Date;
import DAO.QLNV_DAO;
import Entity.NhanVien;

public class themNV_GUI extends JFrame {

    private JTextField txtMaNV, txtTenNV, txtSdtNV, txtNamSinh, txtCMND, txtChucVu, txtLuong, txtNgayLam;
    private JComboBox<String> cbPhai;
    private JButton btnLuu, btnHuy;

    public themNV_GUI() {
        setTitle("Thêm Nhân Viên Mới");
        setSize(400, 450);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridLayout(9, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        txtMaNV = new JTextField();
        txtTenNV = new JTextField();
        txtSdtNV = new JTextField();
        txtNamSinh = new JTextField();
        cbPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        txtCMND = new JTextField();
        txtChucVu = new JTextField();
        txtLuong = new JTextField();
        txtNgayLam = new JTextField();

        formPanel.add(new JLabel("Mã nhân viên:"));
        formPanel.add(txtMaNV);
        formPanel.add(new JLabel("Tên nhân viên:"));
        formPanel.add(txtTenNV);
        formPanel.add(new JLabel("SĐT:"));
        formPanel.add(txtSdtNV);
        formPanel.add(new JLabel("Năm sinh (yyyy-mm-dd):"));
        formPanel.add(txtNamSinh);
        formPanel.add(new JLabel("Phái:"));
        formPanel.add(cbPhai);
        formPanel.add(new JLabel("CMND:"));
        formPanel.add(txtCMND);
        formPanel.add(new JLabel("Chức vụ:"));
        formPanel.add(txtChucVu);
        formPanel.add(new JLabel("Lương:"));
        formPanel.add(txtLuong);
        formPanel.add(new JLabel("Ngày làm việc (yyyy-mm-dd):"));
        formPanel.add(txtNgayLam);

        add(formPanel, BorderLayout.CENTER);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnLuu = new JButton("Lưu");
        btnHuy = new JButton("Hủy");
        btnPanel.add(btnLuu);
        btnPanel.add(btnHuy);

        add(btnPanel, BorderLayout.SOUTH);

     // Xử lý nút Lưu
        btnLuu.addActionListener(e -> {
            String maNV = txtMaNV.getText().trim();
            String tenNV = txtTenNV.getText().trim();
            String sdtNV = txtSdtNV.getText().trim();
            String namSinhStr = txtNamSinh.getText().trim();
            boolean phai = cbPhai.getSelectedItem().equals("Nam");
            String cmnd = txtCMND.getText().trim();
            String chucVu = txtChucVu.getText().trim();
            String luongStr = txtLuong.getText().trim();
            String ngayLamStr = txtNgayLam.getText().trim();

            // Kiểm tra dữ liệu trống
            if (maNV.isEmpty() || tenNV.isEmpty() || sdtNV.isEmpty() || namSinhStr.isEmpty() || cmnd.isEmpty()
                    || chucVu.isEmpty() || luongStr.isEmpty() || ngayLamStr.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Date namSinh = Date.valueOf(namSinhStr);
                Date ngayLam = Date.valueOf(ngayLamStr);
                float luong = Float.parseFloat(luongStr);

                NhanVien nv = new NhanVien(maNV, tenNV, 0, sdtNV, namSinh, phai, cmnd, chucVu, luong, ngayLam, null);

                QLNV_DAO dao = new QLNV_DAO();
                boolean success = dao.themNhanVien(nv);
                if (success) {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thành công!");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Ngày sinh hoặc ngày làm việc không đúng định dạng (yyyy-mm-dd) hoặc lương không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });


        // Nút Hủy đóng cửa sổ
        btnHuy.addActionListener(e -> dispose());
    }
}
