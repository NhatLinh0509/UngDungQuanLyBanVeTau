package GUI;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.imageio.ImageIO;
import javax.swing.Box;
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
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import DAO.Trade_DAO;
import Entity.VeTau;
import connectDB.ConnectDB;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;

public class Trade_GUI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ImageIcon icon;
    private Timer timer;
    private Trade_DAO tradeDAO = new Trade_DAO();
    private VeTau veTimDuoc = null;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Trade_GUI frame = new Trade_GUI();
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
    public Trade_GUI() throws IOException {
        setAutoRequestFocus(false);
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

        // Header panel with animated label
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(0, 0, 0));
        panel_1.setBackground(new Color(255, 255, 255));
        contentPane.add(panel_1, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Đổi trả ");
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

        // Main content panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(255, 255, 255));
        formPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(178, 34, 34), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        formPanel.setPreferredSize(new Dimension(800, 400));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(formPanel, gbc);

        // Ticket code input section
        JPanel ticketPanel = new JPanel();
        ticketPanel.setBackground(new Color(255, 255, 255));
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.X_AXIS));
        ticketPanel.setMaximumSize(new Dimension(700, 50));
        ticketPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblMaVe = new JLabel("Nhập mã vé:");
        lblMaVe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblMaVe.setPreferredSize(new Dimension(100, 30));
        ticketPanel.add(lblMaVe);
        ticketPanel.add(Box.createHorizontalStrut(10));

        JTextField txtMaVe = new JTextField();
        txtMaVe.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtMaVe.setPreferredSize(new Dimension(200, 35));
        txtMaVe.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        ticketPanel.add(txtMaVe);
        ticketPanel.add(Box.createHorizontalStrut(10));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(new Font("Segoe UI", Font.BOLD, 14));
        btnTim.setBackground(new Color(178, 34, 34));
        btnTim.setForeground(Color.WHITE);
        btnTim.setPreferredSize(new Dimension(100, 35));
        btnTim.setFocusPainted(false);
        ticketPanel.add(btnTim);
        formPanel.add(ticketPanel);
        formPanel.add(Box.createVerticalStrut(20));

        // Action type selection
        JPanel actionPanel = new JPanel();
        actionPanel.setBackground(new Color(255, 255, 255));
        actionPanel.setLayout(new BoxLayout(actionPanel, BoxLayout.X_AXIS));
        actionPanel.setMaximumSize(new Dimension(700, 50));
        actionPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblHinhThuc = new JLabel("Chọn hình thức:");
        lblHinhThuc.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblHinhThuc.setPreferredSize(new Dimension(120, 30));
        actionPanel.add(lblHinhThuc);
        actionPanel.add(Box.createHorizontalStrut(10));

        String[] hinhThucOptions = {"Đổi vé", "Trả vé"};
        JComboBox<String> cbHinhThuc = new JComboBox<>(hinhThucOptions);
        cbHinhThuc.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbHinhThuc.setPreferredSize(new Dimension(200, 35));
        actionPanel.add(cbHinhThuc);
        formPanel.add(actionPanel);
        formPanel.add(Box.createVerticalStrut(20));

        // Exchange ticket panel
        JPanel panelDoiVe = new JPanel();
        panelDoiVe.setBackground(new Color(245, 245, 245));
        panelDoiVe.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(178, 34, 34), 1), 
            "Thông tin đổi vé", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new Font("Segoe UI", Font.PLAIN, 14)
        ));
        panelDoiVe.setLayout(new BoxLayout(panelDoiVe, BoxLayout.Y_AXIS));
        panelDoiVe.setMaximumSize(new Dimension(700, 150));
        panelDoiVe.setAlignmentX(Component.LEFT_ALIGNMENT);

        JPanel stationPanel = new JPanel();
        stationPanel.setBackground(new Color(245, 245, 245));
        stationPanel.setLayout(new BoxLayout(stationPanel, BoxLayout.X_AXIS));
        JLabel lblGaDi = new JLabel("Ga đi:");
        lblGaDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGaDi.setPreferredSize(new Dimension(60, 30));
        stationPanel.add(lblGaDi);
        stationPanel.add(Box.createHorizontalStrut(10));

        JComboBox<String> cbGaDi = new JComboBox<>(new String[]{"Sài Gòn", "Đà Nẵng", "Hà Nội"});
        cbGaDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbGaDi.setPreferredSize(new Dimension(150, 35));
        stationPanel.add(cbGaDi);
        stationPanel.add(Box.createHorizontalStrut(20));

        JLabel lblGaDen = new JLabel("Ga đến:");
        lblGaDen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGaDen.setPreferredSize(new Dimension(60, 30));
        stationPanel.add(lblGaDen);
        stationPanel.add(Box.createHorizontalStrut(10));

        JComboBox<String> cbGaDen = new JComboBox<>(new String[]{"Sài Gòn", "Đà Nẵng", "Hà Nội"});
        cbGaDen.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        cbGaDen.setPreferredSize(new Dimension(150, 35));
        stationPanel.add(cbGaDen);
        panelDoiVe.add(stationPanel);
        panelDoiVe.add(Box.createVerticalStrut(10));

        JPanel timePanel = new JPanel();
        timePanel.setBackground(new Color(245, 245, 245));
        timePanel.setLayout(new BoxLayout(timePanel, BoxLayout.X_AXIS));
        JLabel lblGioDi = new JLabel("Giờ đi:");
        lblGioDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblGioDi.setPreferredSize(new Dimension(60, 30));
        timePanel.add(lblGioDi);
        timePanel.add(Box.createHorizontalStrut(10));

        JTextField txtGioDi = new JTextField();
        txtGioDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtGioDi.setPreferredSize(new Dimension(150, 35));
        txtGioDi.setToolTipText("Định dạng: HH:mm (VD: 14:30)");
        txtGioDi.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        timePanel.add(txtGioDi);
        timePanel.add(Box.createHorizontalStrut(20));

        JLabel lblNgayDi = new JLabel("Ngày đi:");
        lblNgayDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblNgayDi.setPreferredSize(new Dimension(60, 30));
        timePanel.add(lblNgayDi);
        timePanel.add(Box.createHorizontalStrut(10));

        JTextField txtNgayDi = new JTextField();
        txtNgayDi.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtNgayDi.setPreferredSize(new Dimension(150, 35));
        txtNgayDi.setToolTipText("Định dạng: dd/MM/yy (VD: 31/03/25)");
        txtNgayDi.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        timePanel.add(txtNgayDi);
        panelDoiVe.add(timePanel);
        panelDoiVe.setVisible(true);
        formPanel.add(panelDoiVe);
        formPanel.add(Box.createVerticalStrut(20));

        // Return ticket panel
        JPanel panelTraVe = new JPanel();
        panelTraVe.setBackground(new Color(245, 245, 245));
        panelTraVe.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(178, 34, 34), 1), 
            "Thông tin trả vé", 
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
            javax.swing.border.TitledBorder.DEFAULT_POSITION, 
            new Font("Segoe UI", Font.PLAIN, 14)
        ));
        panelTraVe.setLayout(new BoxLayout(panelTraVe, BoxLayout.X_AXIS));
        panelTraVe.setMaximumSize(new Dimension(700, 50));
        panelTraVe.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblLyDo = new JLabel("Lý do trả vé:");
        lblLyDo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lblLyDo.setPreferredSize(new Dimension(100, 30));
        panelTraVe.add(lblLyDo);
        panelTraVe.add(Box.createHorizontalStrut(10));

        JTextField txtLyDo = new JTextField();
        txtLyDo.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtLyDo.setPreferredSize(new Dimension(450, 35));
        txtLyDo.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        panelTraVe.add(txtLyDo);
        panelTraVe.setVisible(false);
        formPanel.add(panelTraVe);
        formPanel.add(Box.createVerticalStrut(20));

        // Confirm button
        JButton btnXacNhan = new JButton("Xác nhận");
        btnXacNhan.setFont(new Font("Segoe UI", Font.BOLD, 16));
        btnXacNhan.setBackground(new Color(178, 34, 34));
        btnXacNhan.setForeground(Color.WHITE);
        btnXacNhan.setPreferredSize(new Dimension(150, 40));
        btnXacNhan.setFocusPainted(false);
        btnXacNhan.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(btnXacNhan);

        // Action listeners
        cbHinhThuc.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String selected = (String) cbHinhThuc.getSelectedItem();
                if ("Đổi vé".equals(selected)) {
                    panelDoiVe.setVisible(true);
                    panelTraVe.setVisible(false);
                } else {
                    panelDoiVe.setVisible(false);
                    panelTraVe.setVisible(true);
                }
            }
        });
        
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String maVe = txtMaVe.getText().trim();
                if (maVe.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập mã vé cần tìm.");
                    return;
                }

                veTimDuoc = tradeDAO.timVeTheoMa(maVe);
                if (veTimDuoc != null) {
                    JOptionPane.showMessageDialog(null, "Đã tìm thấy vé!");
                    cbGaDi.setSelectedItem(veTimDuoc.getGaDi());
                    cbGaDen.setSelectedItem(veTimDuoc.getGaDen());
                    txtGioDi.setText(veTimDuoc.getGioKhoiHanh());
                    txtNgayDi.setText(new SimpleDateFormat("dd/MM/yyyy").format(veTimDuoc.getNgayDi()));
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy vé nào với mã: " + maVe);
                }
            }
        });

        btnXacNhan.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (veTimDuoc == null) {
                    JOptionPane.showMessageDialog(null, "Bạn cần tìm vé trước khi xác nhận.");
                    return;
                }

                String hinhThuc = cbHinhThuc.getSelectedItem().toString();
                if (hinhThuc.equals("Đổi vé")) {
                    try {
                        veTimDuoc.setGaDi(cbGaDi.getSelectedItem().toString());
                        veTimDuoc.setGaDen(cbGaDen.getSelectedItem().toString());
                        veTimDuoc.setGioKhoiHanh(txtGioDi.getText().trim());
                        Date ngayDi = new SimpleDateFormat("dd/MM/yyyy").parse(txtNgayDi.getText().trim());
                        veTimDuoc.setNgayDi(ngayDi);

                        boolean kq = tradeDAO.capNhatVe(veTimDuoc);
                        tradeDAO.luuVeDoiTra(veTimDuoc.getMaVe(), "Đổi vé", null);
                        JOptionPane.showMessageDialog(null, kq ? "Đổi vé thành công!" : "Đổi vé thất bại.");
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày hoặc giờ.");
                        ex.printStackTrace();
                    }
                } else if (hinhThuc.equals("Trả vé")) {
                    String lyDo = txtLyDo.getText().trim();
                    if (lyDo.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Bạn phải nhập lý do trả vé.");
                        return;
                    }

                    int choice = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn trả vé này?", "Xác nhận", JOptionPane.YES_NO_OPTION);
                    if (choice == JOptionPane.YES_OPTION) {
                        boolean kq = tradeDAO.xoaVe(veTimDuoc.getMaVe());
                        tradeDAO.luuVeDoiTra(veTimDuoc.getMaVe(), "Đổi vé", null);
                        JOptionPane.showMessageDialog(null, kq ? "Trả vé thành công!" : "Trả vé thất bại.");
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