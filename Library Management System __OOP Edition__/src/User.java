// User.java (Base class)

public class User {
    String name;
    public User(String name){
        this.name = name;
    }
    public void displayInfo(){
        System.out.print("User Name: " + name);
    }
}
