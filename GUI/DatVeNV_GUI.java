	package GUI;
	
	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.EventQueue;
	import java.awt.FlowLayout;
	import java.awt.Font;
	import java.awt.GridBagConstraints;
	import java.awt.GridBagLayout;
import java.awt.Insets;
	import java.awt.event.ActionEvent;
	import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

	import javax.swing.JButton;
	import javax.swing.JComboBox;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JMenu;
	import javax.swing.JMenuBar;
	import javax.swing.JMenuItem;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JScrollPane;
	import javax.swing.JSpinner;
	import javax.swing.JTable;
	import javax.swing.JTextField;
	import javax.swing.SpinnerDateModel;
	import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
	import javax.swing.table.DefaultTableModel;
	import javax.swing.DefaultListCellRenderer;
	import javax.swing.JList;
	import java.awt.Component;

	
	
	import DAO.BanVe_DAO;
	import DAO.HoaDon_DAO;
	import Entity.HoaDon;
	import Entity.VeTau;
	
	
	public class DatVeNV_GUI extends JFrame {
		
	    private static final long serialVersionUID = 1L;
	    private JPanel contentPane;
	    private JTable table;
	    private DefaultTableModel tableModel;
	    private JComboBox<String> cboMaChuyen;
		private JComboBox<String> cboSoGhe;
		private BanVe_DAO dao = new BanVe_DAO();

	    public static void main(String[] args) {
	        EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                try {
	                    DatVeNV_GUI frame = new DatVeNV_GUI();
	                    frame.setVisible(true);
	                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
	                } catch (Exception e) {
	                    e.printStackTrace();
	                }
	            }
	        });
	    }
	    public DatVeNV_GUI() {
	        setTitle("Đặt Vé");
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setBounds(100, 100, 1600, 900);
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
	     // Main Content Panel
	        JPanel mainPanel = new JPanel();
	        mainPanel.setLayout(new BorderLayout());
	        contentPane.add(mainPanel, BorderLayout.CENTER);
	
	        // Form Input Panel
	        JPanel inputPanel = new JPanel();
	        inputPanel.setLayout(new GridBagLayout());  // Thay GridLayout bằng GridBagLayout
	        inputPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
	        mainPanel.add(inputPanel, BorderLayout.NORTH);
	
	        // Khởi tạo GridBagConstraints
	        GridBagConstraints gbc = new GridBagConstraints();
	        gbc.insets = new Insets(5, 5, 5, 5);  // Thêm khoảng cách giữa các phần tử
	
	        // Dòng 1
	        gbc.gridx = 0; gbc.gridy = 0; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Họ và tên:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 0; gbc.fill = GridBagConstraints.HORIZONTAL;
	        JTextField txtHoTen = new JTextField();
	        inputPanel.add(txtHoTen, gbc);
	
	        // Dòng 2
	        gbc.gridx = 0; gbc.gridy = 1; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Mã chuyến:"), gbc);
	
	        // ComboBox để chọn mã chuyến
	        gbc.gridx = 1; gbc.gridy = 1;
	        cboMaChuyen = new JComboBox<>();
	        inputPanel.add(cboMaChuyen, gbc);
	
	     // Thay JTextField thành JComboBox cho số ghế
	        cboSoGhe = new JComboBox<>();
	        inputPanel.add(cboSoGhe, gbc);
	        cboSoGhe = new JComboBox<>();
	        inputPanel.add(cboSoGhe, gbc);
	   
	        // Dòng 3
	        gbc.gridx = 0; gbc.gridy = 2; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("CMND:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 2;
	        JTextField txtCMND = new JTextField();
	        inputPanel.add(txtCMND, gbc);
	
	        // Dòng 4
	        gbc.gridx = 0; gbc.gridy = 3; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Ga đi:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 3;
	        JComboBox<String> cboGaDi = new JComboBox<>(new String[]{"Sài Gòn", "Huế", "Hà Nội", "Đà Nẵng"});
	        inputPanel.add(cboGaDi, gbc);
	
	        // Dòng 5
	        gbc.gridx = 0; gbc.gridy = 4; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Ghế:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 4;
	        JComboBox<String> cboGhe = new JComboBox<>(new String[]{"Nằm", "Ngồi"});
	        inputPanel.add(cboGhe, gbc);
	
	        // Dòng 7
	        gbc.gridx = 0; gbc.gridy = 6; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("SĐT:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 6;
	        JTextField txtSDT = new JTextField();
	        inputPanel.add(txtSDT, gbc);
	
	     // Dòng 8 - Số ghế
	        gbc.gridx = 0; gbc.gridy = 7; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Số ghế"), gbc);
	        gbc.gridx = 1; gbc.gridy = 7;
	        // Thay JTextField thành JComboBox
	        cboSoGhe = new JComboBox<>();
	        inputPanel.add(cboSoGhe, gbc);
	        // Dòng 9
	        gbc.gridx = 0; gbc.gridy = 8; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Ga đến:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 8;
	        JComboBox<String> cboGaDen = new JComboBox<>(new String[]{"Hà Nội", "Huế", "Sài Gòn", "Đà Nẵng"});
	        inputPanel.add(cboGaDen, gbc);
	
	        // Dòng 10
	        gbc.gridx = 0; gbc.gridy = 9; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Khứ hồi:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 9;
	        JComboBox<String> cboKhuHoi = new JComboBox<>(new String[]{"Có", "Không"});
	        inputPanel.add(cboKhuHoi, gbc);



	
	        // Dòng 11
	        gbc.gridx = 0; gbc.gridy = 10; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Ngày đi:"), gbc);
	        gbc.gridx = 1; gbc.gridy = 10;
	        JSpinner spnNgayDi = new JSpinner(new SpinnerDateModel(new java.util.Date(), null, null, java.util.Calendar.DAY_OF_MONTH));
	        JSpinner.DateEditor dateEditor = new JSpinner.DateEditor(spnNgayDi, "dd/MM/yyyy");
	        spnNgayDi.setEditor(dateEditor);
	        inputPanel.add(spnNgayDi, gbc);
	
	     // Dòng 12
	        gbc.gridx = 0; gbc.gridy = 11; gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Giá tiền:"), gbc);
	        
	        

	        // Thay đổi thành JLabel để hiển thị giá tiền
	        gbc.gridx = 1; gbc.gridy = 11;
	        JLabel lblGiaTien = new JLabel("0 VND"); // Khởi tạo giá trị ban đầu là 0 VND
	        inputPanel.add(lblGiaTien, gbc);
	        
	        
	     // Dòng mới dưới "Giá tiền": Mã khuyến mãi
	        gbc.gridx = 0;
	        gbc.gridy = 12;
	        gbc.gridwidth = 1;
	        gbc.anchor = GridBagConstraints.WEST;
	        inputPanel.add(new JLabel("Mã khuyến mãi:"), gbc);

	        gbc.gridx = 1;
	        gbc.gridy = 12;
	        JTextField txtMaKhuyenMai = new JTextField();
	        inputPanel.add(txtMaKhuyenMai, gbc);
	        
	     // Dòng mới dưới "Mã khuyến mãi": Loại tàu
	        gbc.gridx = 0;
	        gbc.gridy = 13; // Cập nhật vị trí dòng tiếp theo
	        gbc.gridwidth = 1;
	        gbc.anchor = GridBagConstraints.WEST;

	        gbc.gridx = 1;
	        gbc.gridy = 13;


	     // Dòng 14 - Các nút bấm
	        gbc.gridx = 0;
	        gbc.gridy = 14; // ✅ Đẩy xuống một dòng
	        gbc.gridwidth = 2;
	        gbc.fill = GridBagConstraints.HORIZONTAL;
	        JPanel buttonPanel = new JPanel();
	        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

	        JButton btnThem = new JButton("Thêm");
	        buttonPanel.add(btnThem);

	        JButton btnXoaTrang = new JButton("Xóa trắng");
	        buttonPanel.add(btnXoaTrang);

	        // Nút xuất hóa đơn
	        JButton btnXuatHoaDon = new JButton("Xuất hóa đơn");
	        buttonPanel.add(btnXuatHoaDon);

	        inputPanel.add(buttonPanel, gbc);

	
	        inputPanel.add(buttonPanel, gbc);
	        String giaTienText = lblGiaTien.getText().replace(" VND", "").replace(",", "");
	        try {
	            double giaTien = Double.parseDouble(giaTienText);
	            if (giaTien != -1) {
	                lblGiaTien.setText(String.format("%.0f VND", giaTien));
	            } else {
	                lblGiaTien.setText("Không hợp lệ");
	            }
	        } catch (NumberFormatException ex) {
	            lblGiaTien.setText("Không hợp lệ"); // Nếu không thể chuyển đổi, đặt thành "Không hợp lệ"
	            ex.printStackTrace(); // Log lỗi ra console để kiểm tra
	        }

	
	
	        // Table Panel
	        JPanel tablePanel = new JPanel();
	        tablePanel.setLayout(new BorderLayout());
	        tablePanel.setBorder(new EmptyBorder(20, 20, 20, 20));
	        mainPanel.add(tablePanel, BorderLayout.CENTER);
	
	        JLabel lblThongTinDonVe = new JLabel("THÔNG TIN ĐƠN VÉ");
	        lblThongTinDonVe.setFont(new Font("Segoe UI", Font.BOLD, 16));
	        lblThongTinDonVe.setHorizontalAlignment(SwingConstants.CENTER);
	        tablePanel.add(lblThongTinDonVe, BorderLayout.NORTH);
	        
	        tableModel = new DefaultTableModel(
	        		new Object[]{"Mã vé", "Họ và tên", "Mã chuyến", "SĐT", "CMND", "Số ghế", "Ga đi", "Ga đến", "Loại ghế", "Khứ hồi", "Ngày đi", "Giá Tiền","Khuyến Mãi"},
	        	    0
	        	);

	        table = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(table);
	        tablePanel.add(scrollPane, BorderLayout.CENTER);
	        loadDataToTable();
	
	        // Button Actions
	     // Listener để tính giá tiền tự động khi các lựa chọn thay đổi
	        ActionListener tinhGiaTienListener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String gaDi = (String) cboGaDi.getSelectedItem();
	                String gaDen = (String) cboGaDen.getSelectedItem();
	                String loaiGhe = (String) cboGhe.getSelectedItem();
	                boolean khuHoi = cboKhuHoi.getSelectedItem().equals("Có");

	                // Gọi DAO để tính giá tiền
	                BanVe_DAO dao = new BanVe_DAO();
	                double giaTien = dao.tinhGiaTien(gaDi, gaDen, loaiGhe, khuHoi);

	                // Hiển thị giá tiền
	                if (giaTien != -1) {
	                    lblGiaTien.setText(String.format("%.0f VND", giaTien));
	                } else {
	                    lblGiaTien.setText("Không hợp lệ");
	                }
	            }
	        };

	        // Gắn sự kiện vào các thành phần liên quan
	        cboGaDi.addActionListener(tinhGiaTienListener);
	        cboGaDen.addActionListener(tinhGiaTienListener);
	        cboGhe.addActionListener(tinhGiaTienListener);
	        cboKhuHoi.addActionListener(tinhGiaTienListener);
	        ActionListener capNhatGheListener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String maChuyen = (String) cboMaChuyen.getSelectedItem();
	                String loaiGhe = (String) cboGhe.getSelectedItem();

	                if (maChuyen != null && loaiGhe != null) {
	                    BanVe_DAO dao = new BanVe_DAO();
	                    List<String> danhSachGhe = dao.layDanhSachGheTrong(maChuyen.split("_")[0], loaiGhe);

	                    cboSoGhe.removeAllItems(); // Xóa các mục cũ
	                    for (String ghe : danhSachGhe) {
	                        cboSoGhe.addItem(ghe); // Thêm ghế mới
	                    }

	                    if (danhSachGhe.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "Không còn ghế trống!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                    }
	                }
	            }
	        };


	        cboGhe.addActionListener(capNhatGheListener);
	        cboMaChuyen.addActionListener(capNhatGheListener);

	        // Gắn sự kiện vào ComboBox loại ghế và mã chuyến
	        cboMaChuyen.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String maChuyen = (String) cboMaChuyen.getSelectedItem(); // Lấy mã chuyến đã chọn
	                String loaiGhe = (String) cboGhe.getSelectedItem();       // Lấy loại ghế đã chọn

	                if (maChuyen == null || loaiGhe == null) {
	                    return; // Nếu chưa chọn mã chuyến hoặc loại ghế, không làm gì
	                }

	                // Gọi DAO để lấy danh sách ghế trống
	                BanVe_DAO dao = new BanVe_DAO();
	                List<String> danhSachGhe = dao.layDanhSachGheTrong(maChuyen, loaiGhe);

	                // Xóa các mục hiện tại trong ComboBox số ghế
	                cboSoGhe.removeAllItems();

	                // Thêm danh sách ghế trống vào ComboBox
	                for (String ghe : danhSachGhe) {
	                    cboSoGhe.addItem(ghe);
	                }
	            }
	        });
	        cboGhe.addActionListener(capNhatGheListener);
	        ActionListener capNhatMaChuyenListener = new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String gaDi = (String) cboGaDi.getSelectedItem();
	                String gaDen = (String) cboGaDen.getSelectedItem();
	                java.util.Date utilDate = (java.util.Date) spnNgayDi.getValue();
	                java.sql.Date ngayDi = new java.sql.Date(utilDate.getTime());

	                // ⚠️ Debug in ra console để kiểm tra dữ liệu đầu vào
	                System.out.println(">>> Ga đi: " + gaDi);
	                System.out.println(">>> Ga đến: " + gaDen);
	                System.out.println(">>> Ngày đi: " + ngayDi);

	                if (gaDi != null && gaDen != null && !gaDi.equals(gaDen)) {
	                    BanVe_DAO dao = new BanVe_DAO();
	                    List<String> danhSachMaChuyen = dao.layDanhSachMaChuyenTheoNgayVaGa(ngayDi, gaDi, gaDen);

	                    cboMaChuyen.removeAllItems();

	                    if (danhSachMaChuyen.isEmpty()) {
	                        JOptionPane.showMessageDialog(null, "Không có chuyến nào phù hợp với điều kiện!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
	                    } else {
	                        for (String maChuyen : danhSachMaChuyen) {
	                            cboMaChuyen.addItem(maChuyen);  // ✅ Ví dụ CT001_00:00
	                        }
	                    }
	                } else {
	                    cboMaChuyen.removeAllItems(); // Nếu chưa chọn đúng ga hoặc bị trùng, thì clear
	                }
	            }
	        };




	        cboGaDi.addActionListener(capNhatMaChuyenListener);
	        cboGaDen.addActionListener(capNhatMaChuyenListener);
	        spnNgayDi.addChangeListener(e -> capNhatMaChuyenListener.actionPerformed(null));

	        // Gắn sự kiện vào ComboBox mã chuyến và loại ghế
	        cboMaChuyen.addActionListener(e -> {
	            String maChuyen = (String) cboMaChuyen.getSelectedItem();
	            String loaiGhe = (String) cboGhe.getSelectedItem();

	            if (maChuyen != null && loaiGhe != null) {
	                List<String> gheTrong = dao.layDanhSachGheTrong(maChuyen, loaiGhe);

	                cboSoGhe.removeAllItems(); // Xóa các mục cũ
	                for (String ghe : gheTrong) {
	                    cboSoGhe.addItem(ghe); // Thêm ghế trống
	                }
	            }
	        });

	        cboGhe.addActionListener(e -> {
	            String maChuyen = (String) cboMaChuyen.getSelectedItem();
	            String loaiGhe = (String) cboGhe.getSelectedItem();

	            // Nếu các giá trị chưa được cập nhật, không làm gì
	            if (maChuyen == null || loaiGhe == null) {
	                return;
	            }

	           
	        });
	        btnThem.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                try {
	                    String hoTen = txtHoTen.getText().trim();
	                    String cmnd = txtCMND.getText().trim();
	                    String sdt = txtSDT.getText().trim();
	                    String gaDi = (String) cboGaDi.getSelectedItem();
	                    String gaDen = (String) cboGaDen.getSelectedItem();
	                    String loaiGhe = (String) cboGhe.getSelectedItem();
	                    String maChuyenDayDu = (String) cboMaChuyen.getSelectedItem();
	                    String gheChon = (String) cboSoGhe.getSelectedItem();
	                    String maKM = txtMaKhuyenMai.getText().trim();
	                    boolean khuHoi = cboKhuHoi.getSelectedItem().equals("Có");
	                    Date ngayDi = new java.sql.Date(((java.util.Date) spnNgayDi.getValue()).getTime());

	                    if (maChuyenDayDu == null || !maChuyenDayDu.contains("_")) {
	                        JOptionPane.showMessageDialog(null, "Chưa chọn mã chuyến hợp lệ!");
	                        return;
	                    }

	                    String[] parts = maChuyenDayDu.split("_");
	                    String maChuyen = parts[0];
	                    String gioKhoiHanh = parts[1];
	                    String loaiTau = parts[2];
	                    String soGhe = gheChon;

	                    BanVe_DAO dao = new BanVe_DAO();
	                    double giaTien = dao.tinhGiaTien(gaDi, gaDen, loaiGhe, khuHoi);

	                    // Xử lý khuyến mãi
	                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	                    String ngayDiStr = sdf.format(ngayDi);
	                    double giamGia = 0.0;
	                    if (maKM.equalsIgnoreCase("1thang6") &&
	                        List.of("2025-05-31", "2025-06-01", "2025-06-02").contains(ngayDiStr)) {
	                        giamGia = 0.2;
	                    } else if (maKM.equalsIgnoreCase("tauprovip123")) {
	                        giamGia = 0.1;
	                    } else if (maKM.equals("khachvipT5@34123455124") && ngayDiStr.startsWith("2025-05")) {
	                        giamGia = 0.15;
	                    }

	                    // Nếu không nhập mã KM, hỏi xác nhận
	                    if (maKM.isEmpty()) {
	                        int chon = JOptionPane.showConfirmDialog(null,
	                            "Bạn chưa nhập mã khuyến mãi. Bạn có chắc không muốn sử dụng mã khuyến mãi?",
	                            "Xác nhận không dùng mã KM", JOptionPane.YES_NO_OPTION);
	                        if (chon != JOptionPane.YES_OPTION) {
	                            return; // dừng nếu không xác nhận
	                        }
	                    }

	                    // Áp dụng giảm giá nếu có
	                    if (giamGia > 0) {
	                        giaTien *= (1 - giamGia);
	                    } else if (!maKM.isEmpty()) {
	                        int luaChon = JOptionPane.showConfirmDialog(
	                            null,
	                            "Mã khuyến mãi không có giá trị. Bạn có muốn chọn lại không?",
	                            "Xác nhận",
	                            JOptionPane.YES_NO_OPTION
	                        );

	                        if (luaChon == JOptionPane.YES_OPTION) {
	                            // Cho người dùng chọn lại mã khuyến mãi return để dừng thêm vé
	                            return;
	                        } else {
	                            // Không dùng mã KM để giá tiền giữ nguyên và không hiển thị mã KM trong bảng
	                            maKM = "";
	                        }
	                    }

	                    // Tạo vé
	                    VeTau ve = new VeTau(hoTen, maChuyen, sdt, cmnd, soGhe, gaDi, gaDen, loaiGhe, khuHoi, ngayDi, giaTien);
	                    ve.setGioKhoiHanh(gioKhoiHanh);
	                    ve.setLoaiTau(loaiTau);

	                    boolean thanhCong = dao.themVe(ve);
	                    if (thanhCong) {
	                        JOptionPane.showMessageDialog(null, "Đặt vé thành công!");

	                        // ✅ Chỉ hiển thị mã KM nếu người dùng nhập
	                        String maKMThucTe = maKM.isEmpty() ? "" : maKM;

	                        tableModel.addRow(new Object[]{
	                            ve.getMaVe(),
	                            ve.getHoTen(),
	                            maChuyen + "_" + gioKhoiHanh + "_" + loaiTau,
	                            sdt,
	                            cmnd,
	                            soGhe,
	                            gaDi,
	                            gaDen,
	                            loaiGhe,
	                            khuHoi ? "Có" : "Không",
	                            new SimpleDateFormat("dd/MM/yyyy").format(ngayDi),
	                            ve.getGiaTien(),
	                            maKMThucTe
	                        });

	                        // Nếu bạn không dùng addRow mà load lại toàn bộ thì gọi:
	                        // loadDataToTable();
	                    } else {
	                        JOptionPane.showMessageDialog(null, "Đặt vé thất bại!");
	                    }
	                    
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                    JOptionPane.showMessageDialog(null, "Lỗi: " + ex.getMessage());
	                }
	            }
	        });



	        cboGhe.addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                String maChuyen = (String) cboMaChuyen.getSelectedItem(); // Lấy mã chuyến đã chọn
	                String loaiGhe = (String) cboGhe.getSelectedItem();       // Lấy loại ghế đã chọn

	                if (maChuyen == null || loaiGhe == null) {
	                    return; // Nếu chưa chọn mã chuyến hoặc loại ghế, không làm gì
	                }

	                // Gọi DAO để lấy danh sách ghế trống
	                BanVe_DAO dao = new BanVe_DAO();
	                List<String> danhSachGhe = dao.layDanhSachGheTrong(maChuyen, loaiGhe);

	                // Xóa các mục hiện tại trong ComboBox số ghế
	                cboSoGhe.removeAllItems();

	                // Thêm danh sách ghế trống vào ComboBox
	                for (String ghe : danhSachGhe) {
	                    cboSoGhe.addItem(ghe);
	                }
	            }
	        });
	        btnXuatHoaDon.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                int selectedRow = table.getSelectedRow();

	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một vé để xuất hóa đơn!");
	                    return;
	                }

	                // Lấy thông tin từ dòng được chọn
	                String maVe = tableModel.getValueAt(selectedRow, 0).toString(); // Cột "Mã vé"
	                String hoTen = tableModel.getValueAt(selectedRow, 1).toString(); // Cột "Họ và tên"
	                String soGhe = tableModel.getValueAt(selectedRow, 5).toString(); // Cột "Số ghế"
	                String gaDi = tableModel.getValueAt(selectedRow, 6).toString(); // Cột "Ga đi"
	                String gaDen = tableModel.getValueAt(selectedRow, 7).toString(); // Cột "Ga đến"
	                String loaiGhe = tableModel.getValueAt(selectedRow, 8).toString(); // Cột "Loại ghế"
	                double giaTien;
	                Object valueGiaTien = tableModel.getValueAt(selectedRow, 11);
	                if (valueGiaTien == null) {
	                    JOptionPane.showMessageDialog(null, "Giá trị cột 'Giá Tiền' không được để trống!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	                try {
	                    double giaTienGoc = Double.parseDouble(valueGiaTien.toString());
	                    giaTien = giaTienGoc * 1.08;  // Cộng thêm 8% VAT
	                } catch (NumberFormatException ex) {
	                    JOptionPane.showMessageDialog(null, "Giá trị cột 'Giá Tiền' không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
	                    return;
	                }
	

	                // Tạo mã hóa đơn và ngày xuất
	                String maHoaDon = "HD" + System.currentTimeMillis();
	                Date ngayXuat = new java.sql.Date(new java.util.Date().getTime());

	                // Tạo đối tượng hóa đơn
	                HoaDon hoaDon = new HoaDon(maHoaDon, maVe, ngayXuat, giaTien);

	                // Lưu hóa đơn vào database
	                HoaDon_DAO hoaDonDAO = new HoaDon_DAO();
	                if (hoaDonDAO.themHoaDon(hoaDon)) {
	                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!");

	                    // Xuất hóa đơn ra hình ảnh (thay vì PDF)
	                    hoaDonDAO.xuatHoaDonHinhAnh(hoaDon, hoTen, soGhe, gaDi, gaDen, loaiGhe);
	                } else {
	                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thất bại!");
	                }
	            }
	        });

	        spnNgayDi.addChangeListener(e -> {
	            try {
	                // Lấy dòng được chọn trong bảng
	                int selectedRow = table.getSelectedRow();
	                if (selectedRow == -1) {
	                    JOptionPane.showMessageDialog(null, "Đã chuyển!");
	                    return;
	                }

	                // Lấy giá trị chuỗi ngày từ bảng (định dạng dd/MM/yyyy)
	                String ngayDiStr = tableModel.getValueAt(selectedRow, 10).toString();

	                // Chuyển đổi chuỗi từ dd/MM/yyyy sang yyyy-MM-dd
	                SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
	                SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	                String formattedDate = outputFormat.format(inputFormat.parse(ngayDiStr));

	                // Chuyển thành java.sql.Date
	                java.sql.Date ngayDi = java.sql.Date.valueOf(formattedDate);

	                // Tiếp tục sử dụng biến ngayDi để lấy danh sách mã chuyến
	                List<String> danhSachMaChuyenMoi = dao.layDanhSachMaChuyenTheoNgay(ngayDi);

	                // Xóa tất cả các item hiện tại trong ComboBox mã chuyến
	                cboMaChuyen.removeAllItems();

	                // Thêm các mã chuyến mới vào ComboBox
	                for (String maChuyen : danhSachMaChuyenMoi) {
	                    cboMaChuyen.addItem(maChuyen);
	                }
	            } catch (Exception ex) {
	                ex.printStackTrace();
	                JOptionPane.showMessageDialog(null, "Lỗi định dạng ngày tháng: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
	            }
	        });

	        cboSoGhe.setRenderer(new DefaultListCellRenderer() {
	            private static final long serialVersionUID = 1L;

	            @Override
	            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
	                JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

	                if (value != null && value.toString().contains("(Đã đặt)")) {
	                    label.setForeground(Color.RED); // Màu đỏ cho ghế đã đặt
	                } else {
	                    label.setForeground(Color.BLACK); // Màu đen cho ghế trống
	                }
	                return label;
	            }
	        });
	        btnXoaTrang.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                // Xóa tất cả trường nhập liệu
	                txtHoTen.setText("");
	                cboMaChuyen.setSelectedIndex(-1);
	                txtCMND.setText("");
	                cboGaDi.setSelectedIndex(0);
	                cboGhe.setSelectedIndex(0);
	                txtSDT.setText("");
	                cboSoGhe.setToolTipText("");
	                cboGaDen.setSelectedIndex(0);
	                cboKhuHoi.setSelectedIndex(0);
	                spnNgayDi.setValue(new java.util.Date());
	                lblGiaTien.setText("0 VND"); // Reset giá tiền về giá trị mặc định
	            }
	        });


	    }
	    
	    private void loadDataToTable() {
	        tableModel.setRowCount(0);
	        BanVe_DAO dao = new BanVe_DAO();
	        List<VeTau> danhSachVe = dao.layDanhSachVe();

	        for (VeTau ve : danhSachVe) {
	            String maKM = "";
	            String ngayStr = new SimpleDateFormat("yyyy-MM-dd").format(ve.getNgayDi());

	            // ❗ CHỈ gán mã khuyến mãi nếu thỏa điều kiện thực sự
	            if (List.of("2025-05-31", "2025-06-01", "2025-06-02").contains(ngayStr)) {
	                maKM = "1thang6";
	            } else if (ngayStr.startsWith("2025-05")) {
	                maKM = "khachvipT5@34123455124";
	            } else {
	                // ❗ Nếu vé đó không có khuyến mãi thì để trống
	                maKM = "";
	            }

	            // Tạo chuỗi đầy đủ cho mã chuyến
	            String maChuyenFull = ve.getMaChuyen() + "_" + ve.getGioKhoiHanh() + "_" + ve.getLoaiTau();

	            tableModel.addRow(new Object[]{
	                ve.getMaVe(),
	                ve.getHoTen(),
	                maChuyenFull,
	                ve.getSoDienThoai(),
	                ve.getCmnd(),
	                ve.getSoGhe(),
	                ve.getGaDi(),
	                ve.getGaDen(),
	                ve.getLoaiGhe(),
	                ve.isKhuHoi() ? "Có" : "Không",
	                new SimpleDateFormat("dd/MM/yyyy").format(ve.getNgayDi()),
	                ve.getGiaTien(),
	                maKM // ✅ Chỉ hiển thị nếu thực sự có KM
	            });
	        }
	    }








	}
