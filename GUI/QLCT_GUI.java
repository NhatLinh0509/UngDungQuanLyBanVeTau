package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.List;
import java.util.Map;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import DAO.QLChuyenTau_DAO;
import Entity.ChuyenTau;
import connectDB.ConnectDB;
import java.awt.Insets;

public class QLCT_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ImageIcon icon;
    private Timer timer;
    private JTable table;
    private DefaultTableModel dtm;
    private static final String ID_COLUMN = "maChuyen";
    private final Map<String, String> columnNames = Map.of(
            "maChuyen", "Mã chuyến",
            "ngayDi", "Ngày khởi hành",
            "gaDi", "Ga đi",
            "gaDen", "Ga đến",
            "gioKhoiHanh", "Giờ khởi hành"
    );

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QLCT_GUI frame = new QLCT_GUI();
                    frame.setVisible(true);
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     * 
     * @throws IOException
     */
    public QLCT_GUI() throws IOException {
        setAutoRequestFocus(false);
        setTitle("Trang chủ người quản lý ");
        setName("\r\n");
        setIconImage(Toolkit.getDefaultToolkit()
                .getImage("C:\\JavaProject\\QuanLyBanVeTau\\icons\\5452470_high_speed_train_tram_vehicle_icon.png"));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1619, 739);

        JMenuBar menuBar = new JMenuBar();
        menuBar.setFont(new Font("Segoe UI", Font.BOLD, 14));
        menuBar.setBackground(new Color(178, 34, 34));
        menuBar.setForeground(new Color(255, 255, 255));
        menuBar.setUI(null);
        setJMenuBar(menuBar);

        UIManager.put("NewMenu.selectionBackground", new Color(105, 105, 105));
        JMenu mnNewMenu_9 = new JMenu("Trang Chủ");
        mnNewMenu_9.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    QLCT_GUI trangquanly = new QLCT_GUI();
                    trangquanly.setVisible(true);
                    trangquanly.setExtendedState(MAXIMIZED_BOTH);
                    dispose();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        });
        mnNewMenu_9.setForeground(new Color(255, 255, 255));
        mnNewMenu_9.setBackground(new Color(178, 34, 34));
        mnNewMenu_9.setFont(new Font("Segoe UI", Font.BOLD, 15));
        menuBar.add(mnNewMenu_9);

        JMenu mnVe = new JMenu("Vé");
        mnVe.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnVe.setForeground(Color.WHITE);
        mnVe.setBackground(new Color(178, 34, 34));
        menuBar.add(mnVe);

        JMenuItem mniDatVe = new JMenuItem("Đặt Vé");
        mniDatVe.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mniDatVe.setBackground(new Color(178, 34, 34));
        mniDatVe.setForeground(Color.WHITE);
        mniDatVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DatVe_GUI datVeGUI = new DatVe_GUI();
                    datVeGUI.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mnVe.add(mniDatVe);

        JMenuItem mniTimVe = new JMenuItem("Tìm Vé");
        mniTimVe.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mniTimVe.setBackground(new Color(178, 34, 34));
        mniTimVe.setForeground(Color.WHITE);
        mniTimVe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    TimVe_GUI timveGUI = new TimVe_GUI();
                    timveGUI.setVisible(true);
                    dispose(); // ✅ Đóng giao diện hiện tại
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mnVe.add(mniTimVe);

        JMenuItem mniDoiTra = new JMenuItem("Đổi Trả Vé");
        mniDoiTra.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mniDoiTra.setBackground(new Color(178, 34, 34));
        mniDoiTra.setForeground(Color.WHITE);
        mniDoiTra.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    Trade_GUI tradeGUI = new Trade_GUI();
                    tradeGUI.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mnVe.add(mniDoiTra);

        JMenu mnChuyenTau = new JMenu("Chuyến Tàu");
        mnChuyenTau.setBackground(new Color(178, 34, 34));
        mnChuyenTau.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnChuyenTau.setForeground(Color.WHITE);
        menuBar.add(mnChuyenTau);

        JMenuItem mntmQuanLyChuyenTau = new JMenuItem("Quản lý chuyến tàu");
        mntmQuanLyChuyenTau.setForeground(Color.WHITE);
        mntmQuanLyChuyenTau.setBackground(new Color(178, 34, 34));
        mntmQuanLyChuyenTau.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mntmQuanLyChuyenTau.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    QLCT_GUI qlctGUI = new QLCT_GUI();
                    qlctGUI.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mnChuyenTau.add(mntmQuanLyChuyenTau);

        JMenu mnNhanVien = new JMenu("Nhân viên");
        mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnNhanVien.setBackground(new Color(178, 34, 34));
        mnNhanVien.setForeground(Color.WHITE);
        mnNhanVien.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    QLNV_GUI qlynv = new QLNV_GUI();
                    qlynv.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuBar.add(mnNhanVien);

        JMenu mnKhachHang = new JMenu("Khách hàng");
        mnKhachHang.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnKhachHang.setBackground(new Color(178, 34, 34));
        mnKhachHang.setForeground(Color.WHITE);
        mnKhachHang.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    QLKH_GUI qlkh = new QLKH_GUI();
                    qlkh.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        menuBar.add(mnKhachHang);

        JMenu mnThongKe = new JMenu("Doanh Thu");
        mnThongKe.setBackground(new Color(178, 34, 34));
        mnThongKe.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnThongKe.setForeground(new Color(255, 255, 255));
        menuBar.add(mnThongKe);

        JMenuItem mntmThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
        mntmThongKeDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mntmThongKeDoanhThu.setForeground(new Color(255, 255, 255));
        mntmThongKeDoanhThu.setBackground(new Color(178, 34, 34));
        mntmThongKeDoanhThu.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    ThongKe_GUI tkdt = new ThongKe_GUI();
                    tkdt.setVisible(true);
                    dispose();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        mnThongKe.add(mntmThongKeDoanhThu);

        JMenu mnNewMenu_7 = new JMenu("Tài Khoản");
        mnNewMenu_7.setBackground(new Color(178, 34, 34));
        mnNewMenu_7.setForeground(new Color(255, 255, 255));
        mnNewMenu_7.setFont(new Font("Segoe UI", Font.BOLD, 15));
        menuBar.add(mnNewMenu_7);

        JMenuItem mntmNewMenuItem_5 = new JMenuItem("Đăng xuất");
        mntmNewMenuItem_5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TrangDangNhap_GUI dangnhap = new TrangDangNhap_GUI();
                dangnhap.setVisible(true);
                dispose();
            }
        });
        mntmNewMenuItem_5.setBackground(new Color(178, 34, 34));
        mntmNewMenuItem_5.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mntmNewMenuItem_5.setForeground(new Color(255, 255, 255));
        mnNewMenu_7.add(mntmNewMenuItem_5);

        JMenu mnNewMenu_11 = new JMenu("Đăng Ký");
        mnNewMenu_11.setBackground(new Color(178, 34, 34));
        mnNewMenu_11.setFont(new Font("Segoe UI", Font.BOLD, 15));
        mnNewMenu_11.setForeground(new Color(255, 255, 255));
        menuBar.add(mnNewMenu_11);

        mnNewMenu_11.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                EventQueue.invokeLater(() -> {
                    try {
                        ConnectDB.getInstance().connect();
                        DangKy_GUI dangKy = new DangKy_GUI();
                        dangKy.setVisible(true);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
            }
        });

        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(0, 0, 0));
        panel_1.setBackground(new Color(255, 255, 255));
        contentPane.add(panel_1, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Nhà Ga Sài Gòn - Quản Lý Chuyến Tàu ");
        lblNewLabel.setIcon(new ImageIcon(
                "C:\\JavaProject\\QuanLyBanVeTau\\icons\\11334214_train_cargo_freight_railway_transportation_icon.png"));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 32)); // Changed from INTER to Segoe UI
        panel_1.add(lblNewLabel);

        timer = new Timer(60, new ActionListener() {
            private int xposition;

            @Override
            public void actionPerformed(ActionEvent e) {
                xposition += 8;
                if (xposition > getWidth()) {
                    xposition = -lblNewLabel.getWidth();
                }
                lblNewLabel.setBounds(xposition, 0, lblNewLabel.getPreferredSize().width,
                        lblNewLabel.getPreferredSize().height);
            }
        });
        timer.start();

        // === Center panel with GridBagLayout ===
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 2),
                "Quản lý Chuyến Tàu",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 18),
                new Color(178, 34, 34)
        );
        centerPanel.setBorder(titledBorder);
        contentPane.add(centerPanel, BorderLayout.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 20, 10, 20);
        gbc.fill = GridBagConstraints.BOTH;

        // === Table setup ===
        dtm = new DefaultTableModel(new Object[] { "maChuyen", "ngayDi", "gaDi", "gaDen", "gioKhoiHanh", "Loại Tàu" }, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return !getColumnName(column).equals(columnNames.get(ID_COLUMN));
            }

            @Override
            public String getColumnName(int col) {
                String dbColName = (String) super.getColumnName(col);
                return columnNames.getOrDefault(dbColName, dbColName);
            }
        };

        table = new JTable(dtm);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        table.setRowHeight(25);
        table.setGridColor(new Color(200, 200, 200));
        table.setShowGrid(true);
        table.setBackground(Color.WHITE);
        table.setSelectionBackground(new Color(178, 34, 34));
        table.setSelectionForeground(Color.WHITE);

        // Adjust column widths
        table.getColumnModel().getColumn(0).setPreferredWidth(100); // Mã chuyến
        table.getColumnModel().getColumn(1).setPreferredWidth(120); // Ngày khởi hành
        table.getColumnModel().getColumn(2).setPreferredWidth(150); // Ga đi
        table.getColumnModel().getColumn(3).setPreferredWidth(150); // Ga đến
        table.getColumnModel().getColumn(4).setPreferredWidth(100); // Giờ khởi
        table.getColumnModel().getColumn(5).setPreferredWidth(100); // Loại tàu
 


        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                    // editSelectedRow(); // Commented out as in original
                }
            }
        });

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 0.9;
        centerPanel.add(scrollPane, gbc);

        // === Buttons setup ===
        JPanel buttons = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        buttons.setBackground(centerPanel.getBackground());
        buttons.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 1),
                "Các tác vụ chính",
                TitledBorder.DEFAULT_JUSTIFICATION,
                TitledBorder.DEFAULT_POSITION,
                new Font("Segoe UI", Font.BOLD, 14),
                new Color(178, 34, 34)
        ));

        JButton themBtn = new JButton("Thêm mới");
        JButton xoaBtn = new JButton("Xóa");
        JButton lamMoiBtn = new JButton("Làm mới");

        JButton[] buttonsArray = {themBtn, xoaBtn, lamMoiBtn};
        for (JButton btn : buttonsArray) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setBackground(new Color(178, 34, 34));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
            buttons.add(btn);
        }
        themBtn.addActionListener(e -> {
            JTextField maChuyenField = new JTextField(20);
            JTextField ngayDiField = new JTextField(20);
            String[] gaOptions = {"Hà Nội", "Sài Gòn", "Huế", "Đà Nẵng"};
            JComboBox<String> gaDiComboBox = new JComboBox<>(gaOptions);
            JComboBox<String> gaDenComboBox = new JComboBox<>(gaOptions);
            gaDiComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            gaDenComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JTextField gioKHField = new JTextField(20);

            // Thêm JComboBox chọn loại tàu
            String[] loaiTauOptions = {"TRX", "VEX", "ECO", "CRN", "LUX"};
            JComboBox<String> loaiTauComboBox = new JComboBox<>(loaiTauOptions);
            loaiTauComboBox.setFont(new Font("Segoe UI", Font.PLAIN, 14));

            JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10)); // tăng lên 6 dòng
            inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            JLabel[] labels = {
                    new JLabel("Mã chuyến:"),
                    new JLabel("Ngày khởi hành (yyyy-mm-dd):"),
                    new JLabel("Ga đi:"),
                    new JLabel("Ga đến:"),
                    new JLabel("Giờ khởi hành (hh:mm:ss):"),
                    new JLabel("Loại tàu:")
            };
            Component[] fields = {maChuyenField, ngayDiField, gaDiComboBox, gaDenComboBox, gioKHField, loaiTauComboBox};

            Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
            Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);

            for (int i = 0; i < labels.length; i++) {
                labels[i].setFont(labelFont);
                labels[i].setForeground(new Color(50, 50, 50));
                fields[i].setFont(fieldFont);
                inputPanel.add(labels[i]);
                inputPanel.add(fields[i]);
            }

            int result = JOptionPane.showConfirmDialog(null, inputPanel, "Thêm chuyến tàu mới", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                try {
                    String maChuyen = maChuyenField.getText().trim();
                    Date ngayDi = Date.valueOf(ngayDiField.getText().trim());
                    Time gioKhoiHanh = Time.valueOf(gioKHField.getText().trim());
                    java.util.Date now = new java.util.Date();
                    java.sql.Date today = new java.sql.Date(now.getTime());

                    if (ngayDi.before(today)) {
                        JOptionPane.showMessageDialog(null, "Không thể thêm chuyến tàu với ngày khởi hành trong quá khứ!", "Lỗi dữ liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }

                    if (ngayDi.equals(today)) {
                        Time gioHienTai = new Time(now.getTime());
                        if (gioKhoiHanh.before(gioHienTai)) {
                            JOptionPane.showMessageDialog(null, "Không thể thêm chuyến tàu với giờ khởi hành trong quá khứ hôm nay!", "Lỗi dữ liệu", JOptionPane.WARNING_MESSAGE);
                            return;
                        }
                    }
                    String gaDi = (String) gaDiComboBox.getSelectedItem();
                    String gaDen = (String) gaDenComboBox.getSelectedItem();

                    if (gaDi.equals(gaDen)) {
                        JOptionPane.showMessageDialog(null, "Ga đi và ga đến không được trùng nhau!", "Lỗi dữ liệu", JOptionPane.WARNING_MESSAGE);
                        return;
                    }


                    ChuyenTau ct = new ChuyenTau();
                    ct.setMaChuyenTau(maChuyen);
                    ct.setNgayDi(ngayDi);
                    ct.setGaDi(gaDiComboBox.getSelectedItem().toString());
                    ct.setGaDen(gaDenComboBox.getSelectedItem().toString());
                    ct.setGioKhoiHanh(gioKhoiHanh);
                    ct.setLoaiTau((String) loaiTauComboBox.getSelectedItem());  // Gán loại tàu từ ComboBox

                    QLChuyenTau_DAO dao = new QLChuyenTau_DAO();
                    if (dao.themChuyenTau(ct)) {
                        JOptionPane.showMessageDialog(null, "Thêm thành công!");
                        loadData(); // reload dữ liệu lên bảng nếu có hàm này
                    } else {
                        JOptionPane.showMessageDialog(null, "Thêm thất bại! Mã chuyến có thể đã tồn tại.");
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Lỗi khi thêm chuyến tàu!");
                }
            }
        });


        xoaBtn.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow == -1) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn chuyến tàu cần xóa!");
                return;
            }

            String maChuyen = table.getValueAt(selectedRow, 0).toString();
            int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa chuyến " + maChuyen + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                QLChuyenTau_DAO dao = new QLChuyenTau_DAO();
                if (dao.xoaChuyenTau(maChuyen)) {
                    JOptionPane.showMessageDialog(null, "Xóa thành công!");
                    loadData();
                } else {
                    JOptionPane.showMessageDialog(null, "Xóa thất bại!");
                }
            }
        });

        lamMoiBtn.addActionListener(e -> loadData());

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weighty = 0.1;
        centerPanel.add(buttons, gbc);

        loadData();
    }

    /**
     * Load thông tin chuyến tàu lên bảng
     */
    public void loadData() {
        dtm.setRowCount(0); // CLEAR bảng cũ
        List<ChuyenTau> ds = QLChuyenTau_DAO.danhSachChuyenTau();
        for (ChuyenTau chuyenTau : ds) {
        	dtm.addRow(new Object[] {
        		    chuyenTau.getMaChuyenTau(),
        		    chuyenTau.getNgayDi(),
        		    chuyenTau.getGaDi(),
        		    chuyenTau.getGaDen(),
        		    chuyenTau.getGioKhoiHanh(),
        		    chuyenTau.getLoaiTau() // giá trị mới
        		});

        }
        JOptionPane.showMessageDialog(this, "Tải lại dữ liệu thành công!");
    }

    /**
     * Set JFrame to full screen
     */
    public void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }
}