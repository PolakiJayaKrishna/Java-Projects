// Library.java (Implements Abstraction)

import java.util.ArrayList;

public class Library extends LibraryAction {
    ArrayList<Book> books = new ArrayList<>();

    @Override
    void addBook(Book book){
        books.add(book);
        System.out.println("Book added: " + book.getTitle());
    }
    @Override
    void displayBooks(){
        if(books.isEmpty()) {
            System.out.println("No Books in Library..");
            return;
        }
        for(Book b : books){
            System.out.println(b.getId() + " - " + b.getTitle() + " by " + b.getAuthor());
        }
    }
}
