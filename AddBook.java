package Library;

import java.util.Scanner;

public class AddBook implements IOOperation {

    @Override
    public void oper(Database database, User user){

        Scanner s = new Scanner(System.in);
        Book book = new Book();

        System.out.println("\nBook's title: ");
        String name = s.next();
        if (database.getBook(name) >-1){
            System.out.println("There is a book with this name\n");
            user.menu(database, user);

        } else {   book.setName(name);
            System.out.println("Book's author");
            book.setAuthor(s.next());

            System.out.println("Book's date");
            book.setDate(s.next());

            System.out.println("Category");
            book.setCategory(s.next());

            System.out.println("Copies for borrowing");
            book.setBrwcopies(s.nextInt());

            database.AddBook(book);
            System.out.println("Book added successfully\n");
            user.menu (database,user);
            s.close();}

    }
}
