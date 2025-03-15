import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create the main frame
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Initialize FarmBackground (Main Layout)
                FarmBackground farm = new FarmBackground("Teacher Dashboard");

                // Sidebar menu items
                String[] menuItems = {"Home", "Attendance", "Teaching Course", "Drop Lesson", "Quiz", "Check Assignment", "Grade"};

                // Add SideBar to the sidebar panel in FarmBackground
                SideBar sideBar = new SideBar(menuItems);
                farm.sidebar.add(sideBar); // Attach sidebar

                // Add CourseBox to the main panel in FarmBackground
                CourseBox courseBox = new CourseBox();
                farm.mainPanel.add(courseBox); // Attach course grid

                // Refresh UI
                farm.frame.revalidate();
                // farm.frame.repaint();
            }
        });
    }
}

