package GUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import DAO.LogIn_DAO;
//import connectDB.ConnectDB;
import Entity.ChuyenTau;
import Entity.TaiKhoan;
import connectDB.ConnectDB;

import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.border.EtchedBorder;
import javax.swing.JButton;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

public class TrangDangNhap_GUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private DAO.LogIn_DAO dangnhap_DAO;
	private DefaultComboBoxModel cbx1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrangDangNhap_GUI frame = new TrangDangNhap_GUI();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TrangDangNhap_GUI() {
		setResizable(false);
		setLocationRelativeTo(null);
		setAutoRequestFocus(true);
		setTitle("Trang Đăng Nhập");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 792);
		setLocationRelativeTo(null);
		

		 //ket noi database
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dangnhap_DAO = new LogIn_DAO();

		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Nhà Ga Sài Gòn");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 22));
		lblNewLabel.setBounds(206, 11, 360, 33);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(
				"C:\\Users\\nhatl\\Desktop\\AD\\PTUD\\Image\\Login.JPG"));
		lblNewLabel_1.setBounds(173, 31, 318, 273);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tên đăng nhập:");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(67, 383, 124, 56);
		contentPane.add(lblNewLabel_2);

		JLabel lblNewLabel_2_1 = new JLabel("Mật khẩu:");
		lblNewLabel_2_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_2_1.setBounds(67, 471, 124, 56);
		contentPane.add(lblNewLabel_2_1);

		textField = new JTextField();
		textField.setBounds(197, 391, 392, 44);
		contentPane.add(textField);
		textField.setColumns(10);

		JButton btnNewButton = new JButton("Đăng nhập");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        String tentk = textField.getText().trim();
		        String matkhau = new String(passwordField.getPassword()).trim();
		        String role = dangnhap_DAO.CheckLogin(tentk, matkhau);

		        if (role == null) {
		            JOptionPane.showMessageDialog(null, "Tên đăng nhập hoặc mật khẩu không đúng!", "Lỗi", JOptionPane.ERROR_MESSAGE);
		            return;
		        }

		        switch (role.toLowerCase()) {
		            case "tài khoản quản lý":
		                try {
		                    new TrangChuQuanLy_GUI().setVisible(true);
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		                break;
		            case "tài khoản nhân viên":
		                try {
		                    new TrangChuNVien_GUI().setVisible(true);
		                } catch (IOException ex) {
		                    ex.printStackTrace();
		                }
		                break;
		            default:
		                JOptionPane.showMessageDialog(null, "Vai trò không hợp lệ: " + role);
		                return;
		        }

		        dispose(); // Tắt GUI đăng nhập sau khi hiển thị GUI chính
		    }
		});

		btnNewButton.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnNewButton.setBackground(Color.RED);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnNewButton.setBounds(67, 676, 124, 48);
		contentPane.add(btnNewButton);

		JButton btnngK = new JButton("Quên mật khẩu?");
		btnngK.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		btnngK.setBackground(Color.WHITE);
		btnngK.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnngK.setBounds(449, 676, 140, 48);
		contentPane.add(btnngK);
		passwordField = new JPasswordField();
		passwordField.setBounds(197, 477, 392, 49);
		contentPane.add(passwordField);
	}

	public void xoatrang() {
		textField.setText("");
		passwordField.setText("");
		textField.requestFocus();

	}
}