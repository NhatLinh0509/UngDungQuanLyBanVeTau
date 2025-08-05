package GUI;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Time;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import DAO.QLNV_DAO;
import Entity.NhanVien;
import connectDB.ConnectDB;
import java.text.DecimalFormat;
import javax.swing.border.TitledBorder;
import java.awt.Insets;
import java.awt.GridBagConstraints;

public class QLNV_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ImageIcon icon;
    private Timer timer;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    QLNV_GUI frame = new QLNV_GUI();
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
    public QLNV_GUI() throws IOException {
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

     // Menu Nhân viên
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

     			// Menu Khách hàng
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

        JLabel lblNewLabel = new JLabel("Nhà Ga Sài Gòn - Quản Lý Nhân Viên ");
        lblNewLabel.setIcon(new ImageIcon(
                "C:\\JavaProject\\QuanLyBanVeTau\\icons\\11334214_train_cargo_freight_railway_transportation_icon.png"));
        lblNewLabel.setForeground(new Color(0, 0, 0));
        lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
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

        // === Panel trung tâm chứa toàn bộ quản lý nhân viên ===
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        TitledBorder titledBorder = BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(178, 34, 34), 2),
                "Quản lý Nhân Viên",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Segoe UI", Font.BOLD, 18),
                new Color(178, 34, 34)
        );
        centerPanel.setBorder(titledBorder);
        centerPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // === Panel tìm kiếm nhân viên ===
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        searchPanel.setBackground(centerPanel.getBackground());
        searchPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));

        JLabel lblSdt = new JLabel("SĐT:");
        lblSdt.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lblSdt.setForeground(new Color(50, 50, 50));

        JTextField txtSdtTim = new JTextField(20);
        txtSdtTim.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtSdtTim.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(150, 150, 150), 1),
                BorderFactory.createEmptyBorder(5, 5, 5, 5)));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTim.setBackground(new Color(178, 34, 34));
        btnTim.setForeground(Color.WHITE);
        btnTim.setFocusPainted(false);
        btnTim.setBorder(BorderFactory.createEmptyBorder(8, 20, 8, 20));

        searchPanel.add(lblSdt);
        searchPanel.add(txtSdtTim);
        searchPanel.add(btnTim);

        // === Panel form thông tin nhân viên ===
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(centerPanel.getBackground());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JTextField txtMaNV = new JTextField(20);
        JTextField txtTenNV = new JTextField(20);
        JTextField txtSdtNV = new JTextField(20);
        JTextField txtNamSinh = new JTextField(20);
        JComboBox<String> cbPhai = new JComboBox<>(new String[]{"Nam", "Nữ"});
        JTextField txtCMND = new JTextField(20);
        JTextField txtChucVu = new JTextField(20);
        JTextField txtLuong = new JTextField(20);
        JTextField txtNgayLam = new JTextField(20);

        // Style for text fields and combo box
        Font fieldFont = new Font("Segoe UI", Font.PLAIN, 14);
        txtMaNV.setFont(fieldFont);
        txtTenNV.setFont(fieldFont);
        txtSdtNV.setFont(fieldFont);
        txtNamSinh.setFont(fieldFont);
        cbPhai.setFont(fieldFont);
        txtCMND.setFont(fieldFont);
        txtChucVu.setFont(fieldFont);
        txtLuong.setFont(fieldFont);
        txtNgayLam.setFont(fieldFont);

        cbPhai.setBackground(Color.WHITE);
        cbPhai.setBorder(BorderFactory.createLineBorder(new Color(150, 150, 150), 1));

        // Style for labels
        Font labelFont = new Font("Segoe UI", Font.BOLD, 14);
        Color labelColor = new Color(50, 50, 50);

        JLabel[] labels = {
                new JLabel("Mã nhân viên:"),
                new JLabel("Tên nhân viên:"),
                new JLabel("SĐT:"),
                new JLabel("Năm sinh (yyyy-mm-dd):"),
                new JLabel("Phái:"),
                new JLabel("CMND:"),
                new JLabel("Chức vụ:"),
                new JLabel("Lương:"),
                new JLabel("Ngày làm việc (yyyy-mm-dd):")
        };

        Component[] fields = {txtMaNV, txtTenNV, txtSdtNV, txtNamSinh, cbPhai, txtCMND, txtChucVu, txtLuong, txtNgayLam};

        for (int i = 0; i < labels.length; i++) {
            labels[i].setFont(labelFont);
            labels[i].setForeground(labelColor);
            gbc.gridx = 0;
            gbc.gridy = i;
            gbc.weightx = 0.0;
            formPanel.add(labels[i], gbc);
            gbc.gridx = 1;
            gbc.weightx = 1.0;
            formPanel.add(fields[i], gbc);
        }

        // === Panel nút chức năng ===
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnPanel.setBackground(centerPanel.getBackground());
        btnPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 20, 20));

        JButton btnThem = new JButton("Thêm");
        JButton btnXoa = new JButton("Xóa");
        JButton btnDanhSach = new JButton("Danh sách");

        JButton[] buttons = { btnThem, btnXoa, btnDanhSach};
        for (JButton btn : buttons) {
            btn.setFont(new Font("Segoe UI", Font.BOLD, 14));
            btn.setBackground(new Color(178, 34, 34));
            btn.setForeground(Color.WHITE);
            btn.setFocusPainted(false);
            btn.setBorder(BorderFactory.createEmptyBorder(10, 25, 10, 25));
            btnPanel.add(btn);
        }

        // === Thêm tất cả vào centerPanel ===
        centerPanel.add(searchPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(formPanel);
        centerPanel.add(Box.createVerticalStrut(10));
        centerPanel.add(btnPanel);

        // === XỬ LÝ SỰ KIỆN CHO NÚT TÌM NHÂN VIÊN ===
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sdt = txtSdtTim.getText().trim();
                if (sdt.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số điện thoại!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                QLNV_DAO dao = new QLNV_DAO();
                NhanVien nv = dao.timNhanVienTheoSDT(sdt);
                if (nv != null) {
                    txtMaNV.setText(nv.getMaNV());
                    txtTenNV.setText(nv.getTenNV());
                    txtSdtNV.setText(nv.getSdtNV());
                    txtNamSinh.setText(nv.getNamSinh().toString());
                    cbPhai.setSelectedItem(nv.isPhai() ? "Nam" : "Nữ");
                    txtCMND.setText(nv.getCMND());
                    txtChucVu.setText(nv.getChucVu());
                    DecimalFormat df = new DecimalFormat("#,###");
                    txtLuong.setText(df.format(nv.getLuong()) + " VNĐ");
                    txtNgayLam.setText(nv.getNgayLamViec().toString());
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy nhân viên!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        btnThem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    themNV_GUI themNV = new themNV_GUI();
                    themNV.setVisible(true);
                    themNV.setLocationRelativeTo(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnDanhSach.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    DSNV_GUI dsnv = new DSNV_GUI();
                    dsnv.setVisible(true);
                    dsnv.setLocationRelativeTo(null);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maNV = txtMaNV.getText().trim();
                if (maNV.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập hoặc chọn nhân viên để xóa!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                int confirm = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa nhân viên này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    QLNV_DAO dao = new QLNV_DAO();
                    boolean success = dao.xoaNhanVien(maNV);
                    if (success) {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
                        txtMaNV.setText("");
                        txtTenNV.setText("");
                        txtSdtNV.setText("");
                        txtNamSinh.setText("");
                        cbPhai.setSelectedIndex(0);
                        txtCMND.setText("");
                        txtChucVu.setText("");
                        txtLuong.setText("");
                        txtNgayLam.setText("");
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa nhân viên thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    // Phương thức để thiết lập JFrame toàn màn hình
    public void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }
}