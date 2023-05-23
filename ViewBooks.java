package Library;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ViewBooks implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        ArrayList<Book> books = database.getAllBooks();

        // Sort books by name
        books.sort(new Comparator<Book>() {
            @Override
            public int compare(Book book1, Book book2) {
                return book1.getName().compareTo(book2.getName());
            }
        });

        System.out.println("Name\tAuthor\tDate\tCategory\tBorrowing copies");
        for (Book b : books) {
            System.out.println(
                    b.getName() + "\t" + b.getAuthor() + "\t" + b.getDate() + "\t" + b.getCategory() + "\t"
                            + "\t" + b.getBrwcopies()
            );
        }
        System.out.println();
        user.menu(database, user);
    }
}