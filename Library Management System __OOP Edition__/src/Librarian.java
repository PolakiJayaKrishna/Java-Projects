// Librarian.java (Inheritance + Polymorphism)

public class Librarian extends User {
    public Librarian(String name){
        super(name);
    }

    @Override
    public void displayInfo(){
        System.out.println("Librarian: " + name);
    }
}
