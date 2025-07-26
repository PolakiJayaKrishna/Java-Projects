package StudentReportCard;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DBConnection {

    public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/Student_Connection";
        String user = "root";
        String password = "0000";

        try {
            return DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("Failed to connect to database.");
            e.printStackTrace();
            return null;
        }
    }
    public static void updateStudent(String regNo, String newName, int totalMarks) {
        String sql = "UPDATE students SET name = ?, total_marks = ? WHERE reg_no = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, newName);
            ps.setInt(2, totalMarks);
            ps.setString(3, regNo);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("✅ Student record updated successfully.");
            } else {
                System.out.println("❌ No student found with RegNo: " + regNo);
            }

        } catch (SQLException e) {
            System.out.println("❌ Error updating student.");
            e.printStackTrace();
        }
    }


    public static void insertStudent(String name, String regNo, int totalMarks) {
        String sql = "INSERT INTO students (name, reg_no, total_marks) VALUES (?, ?, ?)";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);     // ✅ First: name
            ps.setString(2, regNo);    // ✅ Second: reg_no
            ps.setInt(3, totalMarks);  // ✅ Third: total_marks

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("📥 Student inserted into MySQL database.");
            } else {
                System.out.println("⚠️ Insert failed.");
            }

        } catch (SQLException e) {
            System.out.println("❌ Error inserting student.");
            e.printStackTrace();
        }
    }

    public static void getAllStudents() {
        String sql = "SELECT * FROM students";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();

            System.out.println("\n======= STUDENTS FROM DATABASE =======");
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("reg_no");
                String regNo = rs.getString("name");
                int totalMarks = rs.getInt("total_marks");

                System.out.println("ID          : " + id);
                System.out.println("Reg Number  : " + name);
                System.out.println("Name        : " + regNo);
                System.out.println("Total Marks : " + totalMarks);
                System.out.println("--------------------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Failed to fetch data.");
            e.printStackTrace();
        }
    }
    public static void searchStudentByRegNo(String regNo) {
        String sql = "SELECT * FROM students WHERE reg_no = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, regNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                System.out.println("\n Student Found!");
                System.out.println("Reg Number  : " + rs.getString("reg_no"));
                System.out.println("Name        : " + rs.getString("name"));
                System.out.println("Total Marks : " + rs.getInt("total_marks"));
            } else {
                System.out.println("No student found with RegNo: " + regNo);
            }

        } catch (SQLException e) {
            System.out.println("Error while searching student.");
            e.printStackTrace();
        }
    }
    public static void calculateAverageFromDB(String regNo, int numberOfSubjects) {
        String sql = "SELECT total_marks FROM students WHERE reg_no = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, regNo);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                int totalMarks = rs.getInt("total_marks");
                double average = (double) totalMarks / numberOfSubjects;

                System.out.printf("Total Marks : %d\n", totalMarks);
                System.out.printf("Average     : %.2f\n", average);

                if (average >= 35) {
                    System.out.println("Result      : ✅ PASS");
                } else {
                    System.out.println("Result      : ❌ FAIL");
                }

            } else {
                System.out.println(" Student with RegNo " + regNo + " not found.");
            }

        } catch (SQLException e) {
            System.out.println(" Error while calculating average.");
            e.printStackTrace();
        }
    }
    public static void updateStudentDetails(Scanner sc) {
        sc.nextLine(); // flush
        System.out.print("Enter Reg Number of student to update: ");
        String regNo = sc.nextLine();

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int noOfSubjects = sc.nextInt();

        ArrayList<Integer> updatedMarks = new ArrayList<>();
        int total = 0;

        for (int i = 1; i <= noOfSubjects; i++) {
            System.out.print("Enter Subject " + i + " Marks: ");
            int mark = sc.nextInt();
            updatedMarks.add(mark);
            total += mark;
        }

        DBConnection.updateStudent(regNo, newName, total);
    }
    public static void deleteStudent(String regNo) {
        String sql = "DELETE FROM students WHERE reg_no = ?";

        try (Connection conn = getConnection(); PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, regNo);

            int rowsAffected = ps.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("🗑 Student with RegNo " + regNo + " has been deleted.");
            } else {
                System.out.println(" No student found with RegNo: " + regNo);
            }

        } catch (SQLException e) {
            System.out.println("Error while deleting student.");
            e.printStackTrace();
        }
    }


}
