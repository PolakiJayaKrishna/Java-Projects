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
            System.out.println("5. Exit");
            System.out.println("===========================================");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent(sc);
                    break;
                case 2:
                    displayStudentDetails();
                    break;
                case 3:
                    searchRegNo(sc);
                    break;
                case 4:
                    average(sc);
                    break;

                default:
                    break;
            }

        } while (choice != 5);
    }

    public static void addStudent(Scanner sc) {
        Student obj = new Student();
        System.out.print("Enter number of subjects: ");
        int noOfSubjects = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Roll Number:");
        obj.setRegNo(sc.nextLine());

        System.out.print("Enter Name: ");
        obj.setName(sc.nextLine());

        ArrayList<Integer> markArrayList = new ArrayList<>();

        for (int i = 1; i <= noOfSubjects; i++) {
            System.out.print("Enter Subject " + i + " Marks: ");
            int marks = sc.nextInt();
            markArrayList.add(marks);
        }

        obj.setMarks(markArrayList);
        students.add(obj);

        System.out.println("Student added successfully!");

    }

    public static void displayStudentDetails() {
        if (students.isEmpty())
            System.out.println("No students to display.");
        System.out.println("======= STUDENT DETAILS =======\n");
        for (Student student : students) {
            System.out.println("RegNumber: " + student.getRegNo());
            System.out.println("Student Name: " + student.getName());
            System.out.println("Marks: " + student.getMarks());
            System.out.println("-----------------------");
        }
    }

    public static void searchRegNo(Scanner sc) {
        System.out.print("Enter Reg Number to search: ");
        sc.nextLine();
        String regNo = sc.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getRegNo().equalsIgnoreCase(regNo)) {
                System.out.println("Student Found..!");
                System.out.println("Name: " + student.getName());
                System.out.println("RegNo: " + student.getRegNo());
                System.out.println("Marks: " + student.getMarks());
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student With RegNo " + regNo + " Not Found..");
        }
    }

    public static void average(Scanner sc) {
        System.out.print("Enter Reg Number to calculate result: ");
        sc.nextLine();
        String regNo = sc.nextLine();
        boolean found = false;
        for (Student student : students) {
            if (student.getRegNo().equalsIgnoreCase(regNo)) {
                calculateAverage(student.getMarks());
                found = true;
                break;
            }
        }
        if (!found)
            System.out.println("Student with Reg Number " + regNo + " Not Found..");
    }

    public static void calculateAverage(ArrayList<Integer> marksList) {

        int total = 0;
        for (int mark : marksList) {
            total += mark;
        }
        double average = (double) total / marksList.size();
        System.out.printf("Average Marks: %.2f\n", average);

        if (average >= 35)
            System.out.println("Result: Pass");
        else
            System.out.println("Result: Fail");

    }
}

class Student {
    Scanner sc = new Scanner(System.in);
    private String name;
    private String regNo;
    private ArrayList<Integer> marks;

    public void setName(String name) {
        this.name = name;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public void setMarks(ArrayList<Integer> marks) {
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public String getRegNo() {
        return regNo;
    }

    public ArrayList<Integer> getMarks() {
        return marks;
    }
}