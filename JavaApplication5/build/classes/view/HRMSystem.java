package viewtest;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class HRMSystem extends JFrame {
    // Main components
    private JPanel sidebarPanel;
    private JPanel contentPanel;
    private JPanel headerPanel;
    private JTabbedPane mainTabPane;
    
    // User info
    private String currentUser = "Admin";
    private String userRole = "Quản lý";
    
    // Menu items
    private String[] menuItems = {
        "Trang chủ", "Tuyển dụng", "Nhân viên", "Hợp đồng", 
        "Phòng ban", "Chấm công", "Lương thưởng", "Đánh giá", "Tài khoản"
    };
    
    
    public HRMSystem() {
        setTitle("HRMSystem - Hệ thống quản lý nhân công thời vụ");
        setSize(1200, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        
        // Initialize components
        createHeader();
        createSidebar();
        createContent();
        
        setVisible(true);
    }
    
    private void createHeader() {
        headerPanel = new JPanel();
        headerPanel.setLayout(new BorderLayout());
        headerPanel.setBackground(new Color(30, 144, 255));
        headerPanel.setPreferredSize(new Dimension(getWidth(), 40));
        
        // Logo and title
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(new Color(30, 144, 255));
        
        JLabel logoLabel = new JLabel("HRMSystem");
        logoLabel.setForeground(Color.WHITE);
        logoLabel.setFont(new Font("Arial", Font.BOLD, 16));
        
//        JLabel versionLabel = new JLabel("Trường ĐH Xây Dựng Hà Nội");
//        versionLabel.setForeground(Color.WHITE);
//        versionLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        
        logoPanel.add(logoLabel);
//        logoPanel.add(versionLabel);
        
        // Notification icons
        JPanel notificationPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        notificationPanel.setBackground(new Color(30, 144, 255));
        
        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(notificationPanel, BorderLayout.EAST);
        
        add(headerPanel, BorderLayout.NORTH);
    }
    
    private void createSidebar() {
        sidebarPanel = new JPanel();
        sidebarPanel.setLayout(new BoxLayout(sidebarPanel, BoxLayout.Y_AXIS));
        sidebarPanel.setBackground(Color.WHITE);
        sidebarPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 1, Color.LIGHT_GRAY));
        sidebarPanel.setPreferredSize(new Dimension(200, getHeight()));
        
        // User profile
        JPanel userPanel = new JPanel();
        userPanel.setLayout(new BoxLayout(userPanel, BoxLayout.Y_AXIS));
        userPanel.setBackground(Color.WHITE);
        userPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        userPanel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel avatarLabel = new JLabel("👤");
        avatarLabel.setFont(new Font("Arial", Font.PLAIN, 48));
        avatarLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel nameLabel = new JLabel(currentUser);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        JLabel roleLabel = new JLabel(userRole);
        roleLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        roleLabel.setForeground(Color.GRAY);
        roleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        userPanel.add(avatarLabel);
        userPanel.add(Box.createVerticalStrut(10));
        userPanel.add(nameLabel);
        userPanel.add(Box.createVerticalStrut(5));
        userPanel.add(roleLabel);
        
        sidebarPanel.add(userPanel);
        sidebarPanel.add(new JSeparator());
        
//         Menu items
        for (int i = 0; i < menuItems.length; i++) {
            JPanel menuPanel = createMenuItem("",menuItems[i], i == 0);
            sidebarPanel.add(menuPanel);
        }
        
        add(sidebarPanel, BorderLayout.WEST);
    }
    
    private JPanel createMenuItem(String icon, String text, boolean isSelected) {
        JPanel menuPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        menuPanel.setBackground(isSelected ? new Color(240, 240, 240) : Color.WHITE);
        menuPanel.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        menuPanel.setMaximumSize(new Dimension(200, 40));
        
        JLabel iconLabel = new JLabel(icon);
        iconLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        JLabel textLabel = new JLabel(text);
        textLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        menuPanel.add(iconLabel);
        menuPanel.add(Box.createHorizontalStrut(10));
        menuPanel.add(textLabel);
        
        menuPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!isSelected) {
                    menuPanel.setBackground(new Color(245, 245, 245));
                }
            }
            
            @Override
            public void mouseExited(MouseEvent e) {
                if (!isSelected) {
                    menuPanel.setBackground(Color.WHITE);
                }
            }
            
            @Override
            public void mouseClicked(MouseEvent e) {
                // Handle menu item click
            }
        });
        
        return menuPanel;
    }
    
    private void createContent() {
        contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());
        contentPanel.setBackground(new Color(245, 245, 245));
        
        // Create main dashboard content
        JPanel dashboardPanel = new JPanel();
        dashboardPanel.setLayout(new BorderLayout());
        dashboardPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        // Create tabbed content
        mainTabPane = new JTabbedPane();
        
        // Add dashboard tab
    }
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Start application
        SwingUtilities.invokeLater(() -> new HRMSystem());
    }
}
                