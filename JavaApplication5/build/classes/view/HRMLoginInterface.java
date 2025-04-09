package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HRMLoginInterface extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton cancelButton;
    private JCheckBox rememberMeCheckbox;
    
    public HRMLoginInterface() {
        // Thiết lập frame
        setTitle("HRM System - Đăng Nhập");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        
        // Tạo panel chính
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        // Panel tiêu đề
        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("HRM SYSTEM");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(0, 102, 204));
        titlePanel.add(titleLabel);
        
        // Panel thông tin đăng nhập
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new GridLayout(3, 2, 10, 10));
        
        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameField = new JTextField(20);
        
        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordField = new JPasswordField(20);
        
        rememberMeCheckbox = new JCheckBox("Ghi nhớ đăng nhập");
        
        loginPanel.add(usernameLabel);
        loginPanel.add(usernameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(rememberMeCheckbox);
        loginPanel.add(new JLabel(""));  // Placeholder
        
        // Panel nút
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));
        
        loginButton = new JButton("Đăng Nhập");
        cancelButton = new JButton("Huỷ");
        
        loginButton.setPreferredSize(new Dimension(100, 30));
        cancelButton.setPreferredSize(new Dimension(100, 30));
        
        loginButton.setBackground(new Color(0, 102, 204));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        
        buttonPanel.add(loginButton);
        buttonPanel.add(cancelButton);
        
        // Thêm các panel vào panel chính
        mainPanel.add(titlePanel, BorderLayout.NORTH);
        mainPanel.add(loginPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        // Thêm panel chính vào frame
        add(mainPanel);
        
        // Thêm sự kiện cho các nút
        loginButton.addActionListener((ActionEvent e) -> {
            login();
        });
        
        cancelButton.addActionListener((ActionEvent e) -> {
            System.exit(0);
        });
        
        // Thiết lập sự kiện khi nhấn Enter ở field mật khẩu
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    login();
                }
            }
        });
    }
    
    private void login() {
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());
        
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Vui lòng nhập đầy đủ thông tin đăng nhập!", 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Ở đây sẽ thêm code kiểm tra đăng nhập thực tế
        // Đây chỉ là mã mẫu hiển thị thành công
        JOptionPane.showMessageDialog(this, 
            "Đăng nhập thành công!", 
            "Thông báo", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    
    public static void main(String[] args) {
    // Sử dụng look and feel của hệ điều hành
    try {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | UnsupportedLookAndFeelException e) {
        e.printStackTrace();
    }
    
    // Hiển thị giao diện
    SwingUtilities.invokeLater(() -> {
        HRMLoginInterface frame = new HRMLoginInterface();
        frame.setVisible(true);
        // Thêm dòng debug
        System.out.println("Đã tạo và hiển thị giao diện");
    });
}
}