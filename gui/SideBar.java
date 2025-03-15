import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SideBar extends JPanel {
    private boolean isCollapsed = false; // Track sidebar collapse state
    private JPanel menuPanel;

    public SideBar(String[] menuItems) {
        setPreferredSize(new Dimension(250, 700)); // Sidebar width
        setBackground(Color.decode("#1F3A93")); // Dark blue background
        setLayout(new BorderLayout());

        // **TOP SECTION (Logo + Profile)**
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));
        topPanel.setBackground(Color.decode("#1F3A93"));
        topPanel.setBorder(new EmptyBorder(20, 0, 20, 0));

        // **Hamburger Menu Button**
        JButton menuButton = new JButton("\u2630"); // Unicode for ☰
        menuButton.setFont(new Font("Arial", Font.BOLD, 18));
        menuButton.setFocusPainted(false);
        menuButton.setBackground(Color.decode("#1F3A93"));
        menuButton.setForeground(Color.WHITE);
        menuButton.setBorderPainted(false);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        menuButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // **Toggle Sidebar on Click**
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSidebar();
            }
        });

        // **Welcome + Profile Label**
        JLabel welcomeLabel = new JLabel("Teacher", JLabel.CENTER);
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // **Profile Picture Placeholder**
        JLabel profileIcon = new JLabel(new ImageIcon("path/to/profile_icon.png")); // Replace with actual icon path
        profileIcon.setAlignmentX(Component.CENTER_ALIGNMENT);

        topPanel.add(menuButton);
        topPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Spacing
        topPanel.add(profileIcon);
        topPanel.add(Box.createRigidArea(new Dimension(0, 5))); // Spacing
        topPanel.add(welcomeLabel);

        // **MENU PANEL**
        menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        menuPanel.setBackground(Color.decode("#1F3A93"));

        // **Add Menu Buttons**
        for (String menuItem : menuItems) {
            menuPanel.add(createMenuButton(menuItem));
        }

        // **Add panels to Sidebar**
        add(topPanel, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);
    }

    // **Helper Method: Create a Styled Button**
    private JButton createMenuButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(Color.decode("#1F3A93"));
        button.setBorder(new EmptyBorder(15, 20, 15, 20));
        button.setFocusPainted(false);
        button.setHorizontalAlignment(SwingConstants.LEFT);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // **Hover Effect**
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#34495E")); // Slightly darker on hover
            }

            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(Color.decode("#1F3A93")); // Restore original color
            }
        });

        // **Click Event**
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleMenuClick(text); // Handle menu item click
            }
        });

        return button;
    }

    // **Handle Menu Item Clicks**
    private void handleMenuClick(String menuItem) {
        switch (menuItem) {
            case "Home":
                System.out.println("Home clicked");
                break;
            case "Attendance":
                System.out.println("Attendance clicked");
                break;
            case "Teaching Course":
                System.out.println("Teaching Course clicked");
                break;
            case "Drop Lesson":
                System.out.println("Drop Lesson clicked");
                break;
            case "Quiz":
                System.out.println("Quiz clicked");
                break;
            case "Check Assignment":
                System.out.println("Check Assignment clicked");
                break;
            case "Grade":
                System.out.println("Grade clicked");
                break;
            default:
                System.out.println("Unknown menu item clicked");
                break;
        }
    }

    // **Toggle Sidebar Collapse**
    private void toggleSidebar() {
        isCollapsed = !isCollapsed;
        if (isCollapsed) {
            setPreferredSize(new Dimension(60, 700)); // Shrink sidebar
            menuPanel.setVisible(false);
        } else {
            setPreferredSize(new Dimension(250, 700)); // Expand sidebar
            menuPanel.setVisible(true);
        }
        revalidate();
        repaint();
    }

    // **Test Sidebar in a JFrame**
    public static void main(String[] args) {
        JFrame frame = new JFrame("Sidebar Test");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] menuItems = {"Home", "Attendance", "Teaching Course", "Drop Lesson", "Quiz", "Check Assignment", "Grade"};
        SideBar sidebar = new SideBar(menuItems);

        frame.add(sidebar, BorderLayout.WEST);
        frame.setVisible(true);
    }
}