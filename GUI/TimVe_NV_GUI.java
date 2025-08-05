package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
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
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.util.List;
import java.util.ArrayList;

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
import javax.swing.table.DefaultTableModel;

import DAO.TimVe_DAO;
import connectDB.ConnectDB;

public class TimVe_NV_GUI extends JFrame {

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
					TimVe_NV_GUI frame = new TimVe_NV_GUI();
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
	public TimVe_NV_GUI() throws IOException {
		setAutoRequestFocus(false);
		setTitle("Tìm Vé ");
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

		// banner ảnh
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		JPanel panel_1 = new JPanel();
		panel_1.setForeground(new Color(0, 0, 0));
		panel_1.setBackground(new Color(255, 255, 255));
		contentPane.add(panel_1, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("Nhà Ga Sài Gòn - Tìm Vé ");
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
		// === Panel chính giữa chứa tìm và bảng ===
		JPanel centerPanel = new JPanel();
		centerPanel.setBackground(Color.WHITE);
		centerPanel.setLayout(new BorderLayout(10, 10));
		centerPanel.setBorder(new EmptyBorder(20, 100, 30, 100)); // top, left, bottom, right

		// === Panel nhập SDT tìm vé ===
		JPanel searchPanel = new JPanel();
		searchPanel.setBackground(Color.WHITE);
		JLabel lblSdt = new JLabel("Nhập số điện thoại:");
		JTextField txtSdt = new JTextField(15);
		JButton btnTim = new JButton("Tìm");
		searchPanel.add(lblSdt);
		searchPanel.add(txtSdt);
		searchPanel.add(btnTim);

		// === Bảng hiển thị vé
		String[] columnNames = {"Mã vé", "Họ tên", "Mã chuyến", "SĐT", "CMND", "Số ghế", "Ga đi", "Ga đến", "Loại ghế", "Khứ hồi", "Ngày đi", "Giá tiền"};
		DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
		JTable table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);

		// === Gắn vào giao diện
		centerPanel.add(searchPanel, BorderLayout.NORTH);
		centerPanel.add(scrollPane, BorderLayout.CENTER);
		contentPane.add(centerPanel, BorderLayout.SOUTH);

		// === Xử lý tìm kiếm
		btnTim.addActionListener(new ActionListener() {
		    @Override
		    public void actionPerformed(ActionEvent e) {
		        String sdt = txtSdt.getText().trim();
		        TimVe_DAO dao = new TimVe_DAO();
		        List<String[]> danhSachVe = dao.timVeTheoSoDienThoai(sdt);

		        // Xóa dữ liệu cũ
		        tableModel.setRowCount(0);

		        if (danhSachVe.isEmpty()) {
		            JOptionPane.showMessageDialog(null, "Không tìm thấy vé nào với số điện thoại: " + sdt, "Thông báo", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		            for (String[] row : danhSachVe) {
		                tableModel.addRow(row);
		            }
		        }
		    }
		});



	}
	// Phương thức để thiết lập JFrame toàn màn hình
    public void setFullScreen() {
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Phóng to toàn màn hình
        GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        gd.setFullScreenWindow(this);
    }

}
