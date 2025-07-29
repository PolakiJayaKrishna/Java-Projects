import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Library library = new Library();
        Scanner sc = new Scanner(System.in);

        Student student = new Student("Jay" , "99220040690");
        Librarian librarian = new Librarian("Priya");

        student.displayInfo();
        librarian.displayInfo();

        while(true){
            System.out.println("\n1. Add Book\n2. View Books\n3. Exit");
            int choice = sc.nextInt();
            sc.nextLine();
            switch (choice){
                case 1:
                    System.out.print("Enter Book ID: ");
                    int id = sc.nextInt();sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();

                    Book newBook = new Book(id , title , author); // Encapsulation
                    library.addBook(newBook);
                    break;
                case 2:
                    library.displayBooks();
                    break;
                case 3:
                    System.out.println("Exiting...");
                    return;
            }
        }
    }
}