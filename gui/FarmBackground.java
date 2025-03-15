import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class FarmBackground {
    public JFrame frame;
    public JPanel sidebar;
    public JPanel topBar;
    public JPanel mainPanel; // Main panel without CourseBox
    public JLabel profileLabel;
    public JLabel titleLabel;
    public JPanel burgerPanel;

    public FarmBackground() {
        this("Teacher Dashboard"); // Default constructor with a default title
    }

    public FarmBackground(String title) {
        frame = new JFrame(title);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700); // Set exact window size
        frame.setLayout(new BorderLayout());

        // Initialize Sidebar
        sidebar = new JPanel();
        sidebar.setBackground(new Color(15, 50, 120)); // Dark Blue
        sidebar.setPreferredSize(new Dimension(200, frame.getHeight()));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));

        // Initialize TopBar
        topBar = new JPanel();
        topBar.setLayout(new BorderLayout());
        topBar.setPreferredSize(new Dimension(frame.getWidth(), 50));
        topBar.setBackground(new Color(240, 240, 240)); // Light Gray

        // Burger Panel (for sidebar toggle)
        burgerPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLACK);
                int lineHeight = 5;
                int spacing = 10;
                int width = 30;
                int x = (getWidth() - width) / 2;
                int y = getHeight() / 2 - (lineHeight + spacing);

                g.fillRect(x, y, width, lineHeight);
                g.fillRect(x, y + lineHeight + spacing, width, lineHeight);
                g.fillRect(x, y + 2 * (lineHeight + spacing), width, lineHeight);
            }
        };
        burgerPanel.setPreferredSize(new Dimension(50, 50));
        burgerPanel.setBackground(new Color(240, 240, 240)); // Same as topBar
        burgerPanel.setBorder(BorderFactory.createEmptyBorder()); // Remove border

        // Add mouse listener for burger panel
        burgerPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                sidebar.setVisible(!sidebar.isVisible());
                frame.revalidate();
                frame.repaint();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                burgerPanel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                burgerPanel.setCursor(Cursor.getDefaultCursor());
            }
        });

        // Title Label
        titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 10));

        // Profile Label
        profileLabel = new JLabel("Profile");
        profileLabel.setFont(new Font("Arial", Font.BOLD, 14));
        profileLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 20));
        profileLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        profileLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                JOptionPane.showMessageDialog(frame, "Profile Clicked!");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                profileLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                profileLabel.setCursor(Cursor.getDefaultCursor());
            }
        });

        // Add components to TopBar
        topBar.add(burgerPanel, BorderLayout.WEST);
        topBar.add(titleLabel, BorderLayout.CENTER);
        topBar.add(profileLabel, BorderLayout.EAST);

        // Initialize Main Panel (Now Empty)
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.WHITE);

        // Add components to frame
        frame.add(sidebar, BorderLayout.WEST);
        frame.add(topBar, BorderLayout.NORTH);
        frame.add(mainPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
