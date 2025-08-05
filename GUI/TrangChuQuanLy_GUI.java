package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.border.LineBorder;

import connectDB.ConnectDB;

public class TrangChuQuanLy_GUI extends JFrame {

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
					TrangChuQuanLy_GUI frame = new TrangChuQuanLy_GUI();
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
	public TrangChuQuanLy_GUI() throws IOException {
		setAutoRequestFocus(false);
		setTitle("Trang chủ người quản lý ");
		setName("\r\n");
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

		// Đổi Trả Vé
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

		// Thêm ActionListener để mở giao diện đăng ký
		mnNewMenu_11.addMouseListener(new MouseAdapter() {
		    @Override
		    public void mouseClicked(MouseEvent e) {
		        EventQueue.invokeLater(() -> {
		            try {
		                ConnectDB.getInstance().connect(); // Đảm bảo kết nối được mở
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

		// banner ảnh
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Nhà Ga Sài Gòn - Quản Lý Chuyến Tàu ");
		lblNewLabel.setForeground(new Color(0, 0, 0));
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 32));
		panel_1.add(lblNewLabel);
		// label chạy
		timer = new Timer(60, new ActionListener() {
			private int xposition;

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				xposition += 8;

				if (xposition > getWidth()) {
					xposition = -lblNewLabel.getWidth(); // Đặt lại vị trí khi vượt quá chiều rộng của cửa sổ
				}
				lblNewLabel.setBounds(xposition, 0, lblNewLabel.getPreferredSize().width,
						lblNewLabel.getPreferredSize().height);

			}
		});
		timer.start();

	}
	// Phương thức để thiết lập JFrame toàn màn hình
    public void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Phóng to toàn màn hình
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }

}
