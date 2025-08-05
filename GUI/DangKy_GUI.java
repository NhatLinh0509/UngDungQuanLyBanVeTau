package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.DangKy_DAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DangKy_GUI extends JFrame {
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField txtTenTK, txtMatKhau;
    private JComboBox<String> cboLoaiTK;
    private JButton btnDangKy;

    public DangKy_GUI() {
        setTitle("Đăng Ký Tài Khoản");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 600, 400);
        setLayout(new BorderLayout());

        // Table setup
        tableModel = new DefaultTableModel(new Object[]{"Tên tài khoản", "Mật khẩu", "Loại tài khoản"}, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));

        JLabel lblTenTK = new JLabel("Tên tài khoản:");
        txtTenTK = new JTextField();

        JLabel lblMatKhau = new JLabel("Mật khẩu:");
        txtMatKhau = new JTextField();

        JLabel lblLoaiTK = new JLabel("Loại tài khoản:");
        cboLoaiTK = new JComboBox<>(new String[]{"Tài khoản nhân viên", "Tài khoản quản lý"});

        btnDangKy = new JButton("Đăng Ký");

        inputPanel.add(lblTenTK);
        inputPanel.add(txtTenTK);
        inputPanel.add(lblMatKhau);
        inputPanel.add(txtMatKhau);
        inputPanel.add(lblLoaiTK);
        inputPanel.add(cboLoaiTK);
        inputPanel.add(new JLabel()); // Empty cell
        inputPanel.add(btnDangKy);

        add(inputPanel, BorderLayout.SOUTH);

        // Load existing data into table
        loadTaiKhoanData();

        // Button action
        btnDangKy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tenTK = txtTenTK.getText().trim();
                String matKhau = txtMatKhau.getText().trim();
                String loaiTK = (String) cboLoaiTK.getSelectedItem();

                if (tenTK.isEmpty() || matKhau.isEmpty() || loaiTK == null) {
                    JOptionPane.showMessageDialog(DangKy_GUI.this, "Vui lòng điền đầy đủ thông tin!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                boolean result = DangKy_DAO.themTaiKhoan(tenTK, matKhau, loaiTK);
                if (result) {
                    JOptionPane.showMessageDialog(DangKy_GUI.this, "Đăng ký thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    tableModel.addRow(new Object[]{tenTK, matKhau, loaiTK});
                    txtTenTK.setText("");
                    txtMatKhau.setText("");
                } else {
                    JOptionPane.showMessageDialog(DangKy_GUI.this, "Đăng ký thất bại. Tài khoản đã tồn tại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void loadTaiKhoanData() {
        List<Entity.DangKy> danhSachTaiKhoan = DangKy_DAO.layTatCaTaiKhoan();
        for (Entity.DangKy taiKhoan : danhSachTaiKhoan) {
            tableModel.addRow(new Object[]{taiKhoan.getTenTK(), taiKhoan.getMatKhau(), taiKhoan.getLoaiTK()});
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new DangKy_GUI().setVisible(true);
        });
    }
}
