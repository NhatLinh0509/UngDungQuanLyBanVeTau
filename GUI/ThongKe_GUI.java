package GUI;

import java.awt.*;
import java.awt.event.*;
import java.util.Map;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import DAO.ThongKe_DAO;

public class ThongKe_GUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private ChartPanel pieChartPanel;
    private ChartPanel barChartPanel;
    private JLabel lblTongDoanhThu;
    private JLabel lblThongKeLoaiVe;
    private ChartPanel doiTraChartPanel;


    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                ThongKe_GUI frame = new ThongKe_GUI();
                frame.setVisible(true);
                frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public ThongKe_GUI() {
        setTitle("Thống kê doanh thu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1200, 700);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel panelMain = new JPanel(new BorderLayout());
        contentPane.add(panelMain, BorderLayout.CENTER);

        JPanel panelTop = new JPanel();
        panelTop.setBackground(Color.WHITE);
        panelTop.setLayout(new FlowLayout());

        JLabel lblMonth = new JLabel("Chọn tháng:");
        JComboBox<String> cbMonth = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cbMonth.addItem("Tháng " + i);
        }

        JLabel lblYear = new JLabel("Năm:");
        JComboBox<String> cbYear = new JComboBox<>();
        cbYear.addItem("2023");
        cbYear.addItem("2024");
        cbYear.addItem("2025");

        JButton btnLoc = new JButton("Lọc");
        btnLoc.setFont(new Font("Segoe UI", Font.BOLD, 14));

        panelTop.add(lblMonth);
        panelTop.add(cbMonth);
        panelTop.add(lblYear);
        panelTop.add(cbYear);
        panelTop.add(btnLoc);

        panelMain.add(panelTop, BorderLayout.NORTH);

        JPanel panelCharts = new JPanel(new GridLayout(1, 3, 10, 10));
        pieChartPanel = new ChartPanel(null);
        barChartPanel = new ChartPanel(null);
        doiTraChartPanel = new ChartPanel(null);
        panelCharts.add(pieChartPanel);
        panelCharts.add(barChartPanel);
        panelCharts.add(doiTraChartPanel);
        panelMain.add(panelCharts, BorderLayout.CENTER);

        JPanel panelBottom = new JPanel(new GridLayout(2, 1));
        lblTongDoanhThu = new JLabel("Tổng doanh thu: ");
        lblThongKeLoaiVe = new JLabel("Thống kê loại vé: ");
        lblTongDoanhThu.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        lblThongKeLoaiVe.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        panelBottom.add(lblTongDoanhThu);
        panelBottom.add(lblThongKeLoaiVe);
        panelMain.add(panelBottom, BorderLayout.SOUTH);

        btnLoc.addActionListener(e -> {
            int month = cbMonth.getSelectedIndex() + 1;
            int year = Integer.parseInt(cbYear.getSelectedItem().toString());
            updateCharts(month, year);
            
        });
    }

    private void updateCharts(int month, int year) {
        ThongKe_DAO dao = new ThongKe_DAO();
        Map<String, Double> doanhThu = dao.getDoanhThuTheoTuan(month, year);
        Map<String, Integer> tyLeVe = dao.getTiLeLoaiVe(month, year);

        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        double tongDoanhThu = 0;
        for (Map.Entry<String, Double> entry : doanhThu.entrySet()) {
            barDataset.addValue(entry.getValue(), "Doanh thu", entry.getKey());
            tongDoanhThu += entry.getValue();
        }
        JFreeChart barChart = ChartFactory.createBarChart("Doanh thu theo tuần", "Tuần", "VNĐ", barDataset);
        barChartPanel.setChart(barChart);

        DefaultPieDataset pieDataset = new DefaultPieDataset();
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, Integer> entry : tyLeVe.entrySet()) {
            pieDataset.setValue(entry.getKey(), entry.getValue());
            sb.append(entry.getKey()).append(": ").append(entry.getValue()).append(" vé  ");
        }
        JFreeChart pieChart = ChartFactory.createPieChart("Tỉ lệ doanh thu theo loại vé", pieDataset, true, true, false);
        pieChartPanel.setChart(pieChart);

        lblTongDoanhThu.setText("Tổng doanh thu: " + String.format("%,.0f VND", tongDoanhThu));
        lblThongKeLoaiVe.setText("Thống kê loại vé: " + sb.toString());
        // === BIỂU ĐỒ TRÒN VÉ ĐỔI/TRẢ ===
        Map<String, Integer> veDoiTra = dao.getThongKeVeDoiTra(month, year);
        DefaultPieDataset doiTraDataset = new DefaultPieDataset();
        for (Map.Entry<String, Integer> entry : veDoiTra.entrySet()) {
            doiTraDataset.setValue(entry.getKey(), entry.getValue());
        }
        JFreeChart doiTraChart = ChartFactory.createPieChart("Tỉ lệ vé đổi/trả", doiTraDataset, true, true, false);
        doiTraChartPanel.setChart(doiTraChart);

    }
}