package Library;

import java.util.Scanner;

public class BorrowBook implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        Scanner s = new Scanner(System.in);
        System.out.println("Book's title you want to borrow: ");
        String bookName = s.next();

        int i = database.getBook(bookName);
        if (i > -1) {
            Book book = database.getBook(i);
            boolean n= true;
            for(Borrowing b : database.getBrws()){
                if(b.getBook().getName().matches(bookName) &&
                b.getUser().getName().matches(user.getName())){
                    n = false;
                    System.out.println("You have already borrowed this book.");
                }
            }
            if (n){
                if(book.getBrwcopies()>= 1 && n){
                    Borrowing borrowing = new Borrowing(book, user);
                    book.setBrwcopies(book.getBrwcopies()-1);
                    database.borrowBook(borrowing, book, i);
                    System.out.println("You must return the book on " + borrowing.getFinish() + ". Enjoy your reading!\n" );

                } else {
                    System.out.println("Sorry! Not available for borrowing!\n");
                }
            }
            } else {
            System.out.println("The book does not exist\n");
        }
        user.menu(database, user);
    }
}