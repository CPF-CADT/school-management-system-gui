import java.awt.*;
import javax.swing.*;

public class CourseBox extends JPanel {

    private JLabel displayLabel; // Label to display selected course info

    public CourseBox() {
        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        // Panel for course buttons
        JPanel coursePanel = new JPanel(new GridLayout(2, 3, 15, 15));
        coursePanel.setBorder(BorderFactory.createEmptyBorder(20, 40, 40, 40));

        // Label to display course description
        displayLabel = new JLabel("Click a course to see details", JLabel.CENTER);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 18));
        displayLabel.setBorder(BorderFactory.createEmptyBorder(20, 10, 10, 10));

        // Course names
        String[] courses = {"Math", "Science", "History", "English", "Geography", "Physics"};

        for (String courseName : courses) {
            JPanel card = new JPanel();
            card.setPreferredSize(new Dimension(150, 100)); // Set card size
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2)); // Add border
            card.setBackground(Color.LIGHT_GRAY); // Set background color

            JLabel cardLabel = new JLabel(courseName, JLabel.CENTER);
            cardLabel.setFont(new Font("Arial", Font.BOLD, 14));
            card.add(cardLabel);

            // Add MouseListener to update label when clicked
            card.addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
                    String description = getCourseDescription(courseName); // Get description using switch
                    displayLabel.setText(description); // Update display label
                }
            });

            coursePanel.add(card); // Add course to the grid panel
        }

        add(displayLabel, BorderLayout.NORTH);
        add(coursePanel, BorderLayout.CENTER);
    }

    // Helper method to get course description using switch
    private String getCourseDescription(String courseName) {
        switch (courseName) {
            case "Math":
                return "Mathematics is the study of numbers, quantities, and shapes.";
            case "Science":
                return "Science explores the natural world through observation and experimentation.";
            case "History":
                return "History tells us about past events and how they shape the present.";
            case "English":
                return "English enhances communication skills and fosters creativity.";
            case "Geography":
                return "Geography teaches about the Earth's landscapes, environments, and people.";
            case "Physics":
                return "Physics explains the fundamental laws of nature and the universe.";
            default:
                return "No description available for this course.";
        }
    }
}