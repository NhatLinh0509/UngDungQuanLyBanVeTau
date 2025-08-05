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
import java.io.IOException;
import java.awt.BorderLayout;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;
import DAO.QLKH_DAO;
import Entity.KhachHang;
import connectDB.ConnectDB;

public class QLKH_NV_GUI extends JFrame {

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
                    QLKH_NV_GUI frame = new QLKH_NV_GUI();
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
    public QLKH_NV_GUI() throws IOException {
        setAutoRequestFocus(false);
        setTitle("Trang chủ Nhân Viên ");
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
					QLCT_GUI trangquanly=new QLCT_GUI();
					trangquanly.setVisible(true);
					trangquanly.setExtendedState(MAXIMIZED_BOTH);
					dispose();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
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

		// Đặt Vé
					JMenuItem mniDatVe = new JMenuItem("Đặt Vé");
					mniDatVe.setFont(new Font("Segoe UI", Font.BOLD, 15));
					mniDatVe.setBackground(new Color(178, 34, 34));
					mniDatVe.setForeground(Color.WHITE);
					mniDatVe.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
					        try {
					            DatVeNV_GUI datVeNVGUI = new DatVeNV_GUI();
					            datVeNVGUI.setVisible(true);
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
						            TimVe_NV_GUI timve_NV_GUI = new TimVe_NV_GUI();
						            timve_NV_GUI.setVisible(true);
						            dispose(); // ✅ Đóng giao diện hiện tại
						        } catch (Exception ex) {
						            ex.printStackTrace();
						        }
						    }
						});
						mnVe.add(mniTimVe);
					mnVe.add(mniTimVe);

					// Đổi Trả Vé
					JMenuItem mniDoiTra = new JMenuItem("Đổi Trả Vé");
					mniDoiTra.setFont(new Font("Segoe UI", Font.BOLD, 15));
					mniDoiTra.setBackground(new Color(178, 34, 34));
					mniDoiTra.setForeground(Color.WHITE);
					mniDoiTra.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
					        try {
					            Trade_NV_GUI trade_NV_GUI = new Trade_NV_GUI();
					            trade_NV_GUI.setVisible(true);
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
					mnChuyenTau.setEnabled(false); // disable menu
					menuBar.add(mnChuyenTau);

					JMenuItem mntmQuanLyChuyenTau = new JMenuItem("Quản lý chuyến tàu");
					mntmQuanLyChuyenTau.setEnabled(false); // disable menu
					mntmQuanLyChuyenTau.setBackground(new Color(178, 34, 34));
					mntmQuanLyChuyenTau.setFont(new Font("Segoe UI", Font.BOLD, 15));
			
					mnChuyenTau.add(mntmQuanLyChuyenTau);

					// Menu Nhân viên
								JMenu mnNhanVien = new JMenu("Nhân viên");
								mnNhanVien.setFont(new Font("Segoe UI", Font.BOLD, 15));
								mnNhanVien.setBackground(new Color(178, 34, 34));
								mnNhanVien.setEnabled(false); // disable menu
								mnNhanVien.setForeground(new Color(169, 169, 169)); // màu xám tối để nhìn như disabled
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
								            QLKH_NV_GUI qlkhNV = new QLKH_NV_GUI();
								            qlkhNV.setVisible(true);
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
								mnThongKe.setEnabled(false); // disable menu


								JMenuItem mntmThongKeDoanhThu = new JMenuItem("Thống kê doanh thu");
								mntmThongKeDoanhThu.setFont(new Font("Segoe UI", Font.BOLD, 15));
								mntmThongKeDoanhThu.setForeground(new Color(255, 255, 255));
								mntmThongKeDoanhThu.setBackground(new Color(178, 34, 34));
								mnThongKe.add(mntmThongKeDoanhThu);


		JMenu mnNewMenu_7 = new JMenu("Tài Khoản");
		mnNewMenu_7.setBackground(new Color(178, 34, 34));
		mnNewMenu_7.setForeground(new Color(255, 255, 255));
		mnNewMenu_7.setFont(new Font("Segoe UI", Font.BOLD, 15));
		menuBar.add(mnNewMenu_7);

		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Đăng xuất");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TrangDangNhap_GUI dangnhap=new TrangDangNhap_GUI();
				dangnhap.setVisible(true);
				dispose(); // Đóng cửa sổ hiện tại
				

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
		mnNewMenu_11.setEnabled(false); // disable menu

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));

        // Header panel with animated label
        JPanel panel_1 = new JPanel();
        panel_1.setForeground(new Color(0, 0, 0));
        panel_1.setBackground(new Color(255, 255, 255));
        contentPane.add(panel_1, BorderLayout.NORTH);

        JLabel lblNewLabel = new JLabel("Quản Lý Khách Hàng");
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

        // Center panel for form
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBackground(new Color(240, 240, 240));
        contentPane.add(centerPanel, BorderLayout.CENTER);

        // Main form panel
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        mainPanel.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(178, 34, 34), 2),
            BorderFactory.createEmptyBorder(20, 20, 20, 20)
        ));
        mainPanel.setPreferredSize(new Dimension(600, 350));
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;
        centerPanel.add(mainPanel, gbc);

        // Font definitions
        Font fontLabel = new Font("Segoe UI", Font.BOLD, 16);
        Font fontField = new Font("Segoe UI", Font.PLAIN, 14);
        Font fontButton = new Font("Segoe UI", Font.BOLD, 16);

        // Search panel
        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(new Color(255, 255, 255));
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));
        searchPanel.setMaximumSize(new Dimension(500, 50));
        searchPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel lblTimSDT = new JLabel("Nhập SĐT:");
        lblTimSDT.setFont(fontLabel);
        lblTimSDT.setPreferredSize(new Dimension(100, 30));
        searchPanel.add(lblTimSDT);
        searchPanel.add(Box.createHorizontalStrut(10));

        JTextField txtSDTTim = new JTextField();
        txtSDTTim.setFont(fontField);
        txtSDTTim.setPreferredSize(new Dimension(200, 35));
        txtSDTTim.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        searchPanel.add(txtSDTTim);
        searchPanel.add(Box.createHorizontalStrut(10));

        JButton btnTim = new JButton("Tìm");
        btnTim.setFont(fontButton);
        btnTim.setBackground(new Color(178, 34, 34));
        btnTim.setForeground(Color.WHITE);
        btnTim.setPreferredSize(new Dimension(100, 35));
        btnTim.setFocusPainted(false);
        searchPanel.add(btnTim);
        mainPanel.add(searchPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Form panel for customer info
        JPanel formPanel = new JPanel();
        formPanel.setBackground(new Color(245, 245, 245));
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(178, 34, 34), 1),
            "Thông tin khách hàng",
            javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
            javax.swing.border.TitledBorder.DEFAULT_POSITION,
            new Font("Segoe UI", Font.PLAIN, 14)
        ));
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setMaximumSize(new Dimension(500, 200));
        formPanel.setAlignmentX(Component.LEFT_ALIGNMENT);

        JTextField txtHoTen = new JTextField();
        JTextField txtSoDT = new JTextField();
        JTextField txtCMND = new JTextField();
        JLabel lblTongVe = new JLabel("0");

        txtHoTen.setFont(fontField);
        txtSoDT.setFont(fontField);
        txtCMND.setFont(fontField);
        lblTongVe.setFont(fontField);
        txtHoTen.setPreferredSize(new Dimension(300, 35));
        txtSoDT.setPreferredSize(new Dimension(300, 35));
        txtCMND.setPreferredSize(new Dimension(300, 35));
        txtHoTen.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        txtSoDT.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));
        txtCMND.setBorder(BorderFactory.createLineBorder(new Color(178, 34, 34), 1));

        // Name field
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(245, 245, 245));
        namePanel.setLayout(new BoxLayout(namePanel, BoxLayout.X_AXIS));
        JLabel lblHoTen = new JLabel("Họ tên:");
        lblHoTen.setFont(fontLabel);
        lblHoTen.setPreferredSize(new Dimension(100, 30));
        namePanel.add(lblHoTen);
        namePanel.add(Box.createHorizontalStrut(10));
        namePanel.add(txtHoTen);
        formPanel.add(namePanel);
        formPanel.add(Box.createVerticalStrut(10));

        // Phone field
        JPanel phonePanel = new JPanel();
        phonePanel.setBackground(new Color(245, 245, 245));
        phonePanel.setLayout(new BoxLayout(phonePanel, BoxLayout.X_AXIS));
        JLabel lblSDT = new JLabel("Số điện thoại:");
        lblSDT.setFont(fontLabel);
        lblSDT.setPreferredSize(new Dimension(100, 30));
        phonePanel.add(lblSDT);
        phonePanel.add(Box.createHorizontalStrut(10));
        phonePanel.add(txtSoDT);
        formPanel.add(phonePanel);
        formPanel.add(Box.createVerticalStrut(10));

        // ID field
        JPanel idPanel = new JPanel();
        idPanel.setBackground(new Color(245, 245, 245));
        idPanel.setLayout(new BoxLayout(idPanel, BoxLayout.X_AXIS));
        JLabel lblCMND = new JLabel("CMND:");
        lblCMND.setFont(fontLabel);
        lblCMND.setPreferredSize(new Dimension(100, 30));
        idPanel.add(lblCMND);
        idPanel.add(Box.createHorizontalStrut(10));
        idPanel.add(txtCMND);
        formPanel.add(idPanel);
        formPanel.add(Box.createVerticalStrut(10));

        // Total tickets field
        JPanel ticketPanel = new JPanel();
        ticketPanel.setBackground(new Color(245, 245, 245));
        ticketPanel.setLayout(new BoxLayout(ticketPanel, BoxLayout.X_AXIS));
        JLabel lblTongVeLabel = new JLabel("Tổng vé:");
        lblTongVeLabel.setFont(fontLabel);
        lblTongVeLabel.setPreferredSize(new Dimension(100, 30));
        ticketPanel.add(lblTongVeLabel);
        ticketPanel.add(Box.createHorizontalStrut(10));
        ticketPanel.add(lblTongVe);
        formPanel.add(ticketPanel);
        mainPanel.add(formPanel);
        mainPanel.add(Box.createVerticalStrut(20));

        // Update button
        JPanel btnPanel = new JPanel();
        btnPanel.setBackground(new Color(255, 255, 255));
        btnPanel.setLayout(new BoxLayout(btnPanel, BoxLayout.X_AXIS));
        btnPanel.setMaximumSize(new Dimension(500, 50));
        btnPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        JButton btnCapNhat = new JButton("Cập nhật");
        btnCapNhat.setFont(fontButton);
        btnCapNhat.setBackground(new Color(178, 34, 34));
        btnCapNhat.setForeground(Color.WHITE);
        btnCapNhat.setPreferredSize(new Dimension(150, 40));
        btnCapNhat.setFocusPainted(false);
        btnPanel.add(btnCapNhat);
        mainPanel.add(btnPanel);

        // Action listeners
        btnTim.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                QLKH_DAO dao = new QLKH_DAO();
                KhachHang kh = dao.getKhachHangTheoSDT(txtSDTTim.getText().trim());

                if (kh != null) {
                    txtHoTen.setText(kh.getTenKH());
                    txtSoDT.setText(kh.getSdt());
                    txtCMND.setText(kh.getDiaChi());
                    lblTongVe.setText("0"); // Giữ giá trị mặc định như code gốc
                } else {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng!");
                }
            }
        });

        btnCapNhat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sdt = txtSoDT.getText().trim();
                String hoTen = txtHoTen.getText().trim();
                String cmnd = txtCMND.getText().trim();

                // Kiểm tra ô trống
                if (hoTen.isEmpty() || sdt.isEmpty() || cmnd.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không được để trống các trường: Họ tên, SĐT, CMND!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return; // Không thực hiện cập nhật nếu có ô trống
                }

                QLKH_DAO dao = new QLKH_DAO();
                boolean success = dao.capNhatKhachHang(sdt, hoTen, cmnd);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Cập nhật thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Cập nhật thất bại!");
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