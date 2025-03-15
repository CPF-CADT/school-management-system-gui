// package gui;

// import javax.swing.*;
// import java.awt.*;

// public class InputTypeText extends JTextField {
    
//     // Constructor for customizing the text field
//     public InputTypeText(int columns,Color fontCol,Color bg) {
//         super(columns);
//         setFont(new Font("Arial", Font.PLAIN, 24)); 
//         setBackground(bg); 
//         setForeground(fontCol); 
//         setBorder(BorderFactory.createLineBorder(Color.GRAY)); // Set border
//         setCaretColor(Color.RED); // Set the cursor color
//         setToolTipText("Enter your text here..."); // Set tooltips
//     }

//     public static void main(String[] args) {
//         // Create a JFrame to test the custom text field
//         JFrame frame = new JFrame("Custom JTextField Example");
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new FlowLayout());
        
//         // Add a customized text field
//         InputTypeText customTextField = new InputTypeText(20,new Color(0,0,0),new Color(255,255,255));
        
//         // Add it to the frame
//         RoundedButton ok = new RoundedButton("OK",Color.WHITE, new Color(50, 90, 255), new Dimension(200, 80));
//         ok.addActionListener(e->{
//             System.out.println(customTextField.getText());
//             customTextField.setText("");
//         });
//         frame.add(customTextField); 
//         frame.add(ok);
//         frame.setSize(700, 400);
//         frame.setVisible(true);
//     }
// }

