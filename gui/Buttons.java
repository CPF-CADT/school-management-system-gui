// package gui;

// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.MouseAdapter;
// import java.awt.event.MouseEvent;

// class RoundedButton extends JButton {
//     private Color defaultColor;
//     private Color hoverColor;
//     private Color clickColor;

//     public RoundedButton(String title, Color fontCol, Color buttonColor, Dimension size) {
//         super(title);
//         this.defaultColor = buttonColor;
//         this.hoverColor = buttonColor.brighter(); // Slightly lighter color for hover
//         this.clickColor = buttonColor.darker(); 
//         setContentAreaFilled(false);
//         setFocusPainted(false);
//         setBorderPainted(false);
//         setForeground(fontCol);
//         setBackground(buttonColor);
//         setFont(new Font("Arial", Font.BOLD, 24));
//         setPreferredSize(size);
//         addMouseListener(new MouseAdapter() {
//             @Override
//             public void mouseExited(MouseEvent e) {
//                 setBackground(defaultColor);
//             }

//             @Override
//             public void mousePressed(MouseEvent e) {
//                 setBackground(clickColor);
//             }

//             @Override
//             public void mouseReleased(MouseEvent e) {
//                 setBackground(hoverColor);
//             }
//         });
//     }

//     @Override
//     protected void paintComponent(Graphics g) {
//         Graphics2D g2 = (Graphics2D) g.create();
//         g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

//         // Paint rounded background
//         g2.setColor(getBackground());
//         g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

//         // Call the superclass method to paint text and icon
//         super.paintComponent(g);

//         g2.dispose();
//     }
// }

// public class Buttons {
//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Custom Buttons");
//         frame.setSize(400, 300);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new FlowLayout());

//         // Get Started Button with Icon
//         RoundedButton getStarted = new RoundedButton("Get Started", Color.WHITE, new Color(50, 90, 255),
//                 new Dimension(200, 80));

//         getStarted.addActionListener(e ->{
//             System.out.println("Buuton Is press");
//         });
//         frame.add(getStarted);

//         frame.setVisible(true);
//     }
// }
