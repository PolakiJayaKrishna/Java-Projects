package StudentReportCard;

import java.util.ArrayList;

public class Student {
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
