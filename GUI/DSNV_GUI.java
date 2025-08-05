package GUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import DAO.QLNV_DAO;
import Entity.NhanVien;
import java.sql.Date;
import java.text.DecimalFormat;

public class DSNV_GUI extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;

    public DSNV_GUI() {
        setTitle("Danh Sách Nhân Viên");
        setSize(900, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"Mã NV", "Tên NV", "SĐT", "Năm sinh", "Phái", "CMND", "Chức vụ", "Lương", "Ngày làm việc"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane, BorderLayout.CENTER);

        loadData();
    }

    private void loadData() {
        try {
            QLNV_DAO dao = new QLNV_DAO();
            List<NhanVien> list = dao.layDanhSachNhanVien();

            tableModel.setRowCount(0); // xóa dữ liệu cũ

            DecimalFormat df = new DecimalFormat("#,###");

            for (NhanVien nv : list) {
                String phaiStr = nv.isPhai() ? "Nam" : "Nữ";
                String luongFormatted = df.format(nv.getLuong()); // định dạng lương
                tableModel.addRow(new Object[]{
                        nv.getMaNV(),
                        nv.getTenNV(),
                        nv.getSdtNV(),
                        nv.getNamSinh(),
                        phaiStr,
                        nv.getCMND(),
                        nv.getChucVu(),
                        luongFormatted + " VNĐ",  // Hiển thị kèm đơn vị
                        nv.getNgayLamViec()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi khi tải danh sách nhân viên!", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }
}
