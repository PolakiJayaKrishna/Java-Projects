package StudentReportCard;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentReportCard {

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n======== STUDENT REPORT CARD SYSTEM ========");
            System.out.println("1. Add Student Details");
            System.out.println("2. View All Students");
            System.out.println("3. Search by Roll Number");
            System.out.println("4. Calculate Average & Result");
            System.out.println("5. Update Student Record");
            System.out.println("6. Delete Student Record");
            System.out.println("7. Exit");
            System.out.println("============================================");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Enter a valid choice (1-7): ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    DBConnection.getAllStudents();
                    break;
                case 3:
                    searchRegNo(sc);
                    break;
                case 4:
                    average(sc);
                    break;
                case 5:
                    updateStudentDetails(sc);
                    break;
                case 6:
                    deleteStudent(sc);
                    break;
                case 7:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("❌ Invalid choice. Try again.");
            }

        } while (choice != 7);

        sc.close();
    }

    // 1. Add Student Details
    public static void addStudent(Scanner sc) {
        Student obj = new Student();

        System.out.print("Enter number of subjects (fixed No.Of Subjects): ");
        int noOfSubjects = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter Name: ");
        obj.setName(sc.nextLine());

        System.out.print("Enter Roll Number: ");
        obj.setRegNo(sc.nextLine());

        ArrayList<Integer> markArrayList = new ArrayList<>();
        int total = 0;

        for (int i = 1; i <= noOfSubjects; i++) {
            System.out.print("Enter Subject " + i + " Marks: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter valid integer marks: ");
                sc.next();
            }
            int marks = sc.nextInt();
            markArrayList.add(marks);
            total += marks;
        }

        obj.setMarks(markArrayList);
        students.add(obj);

        DBConnection.insertStudent(obj.getName(), obj.getRegNo(), total);
        System.out.println("✅ Student added successfully!");
    }

    // 2. Delete student
    public static void deleteStudent(Scanner sc) {
        sc.nextLine(); // flush
        System.out.print("Enter Reg Number to delete: ");
        String regNo = sc.nextLine();

        DBConnection.deleteStudent(regNo);
    }

    // 3. Search student by RegNo
    public static void searchRegNo(Scanner sc) {
        sc.nextLine(); // flush
        System.out.print("Enter Reg Number to search: ");
        String regNo = sc.nextLine();

        DBConnection.searchStudentByRegNo(regNo);
    }

    // 4. Calculate average and result
    public static void average(Scanner sc) {
        sc.nextLine(); // flush
        System.out.print("Enter Reg Number to calculate average: ");
        String regNo = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = sc.nextInt();

        DBConnection.calculateAverageFromDB(regNo, numberOfSubjects);
    }

    // 5. Update student (name + recalculate total marks)
    public static void updateStudentDetails(Scanner sc) {
        sc.nextLine(); // flush
        System.out.print("Enter Reg Number of student to update: ");
        String regNo = sc.nextLine();

        System.out.print("Enter new name: ");
        String newName = sc.nextLine();

        System.out.print("Enter number of subjects: ");
        int noOfSubjects = sc.nextInt();

        int total = 0;
        for (int i = 1; i <= noOfSubjects; i++) {
            System.out.print("Enter Subject " + i + " Marks: ");
            while (!sc.hasNextInt()) {
                System.out.print("Enter valid integer marks: ");
                sc.next();
            }
            int mark = sc.nextInt();
            total += mark;
        }

        DBConnection.updateStudent(regNo, newName, total);
    }
}
