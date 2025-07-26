//// Student.java (Inheritance + Polymorphism)

public class Student extends User {
    String regNo;
    public Student(String name , String regNo ){
        super(name);
        this.regNo = regNo;
    }

    @Override
    public void displayInfo(){
        System.out.println("Student: " + name + ", RegNo: " + regNo);
    }
}
