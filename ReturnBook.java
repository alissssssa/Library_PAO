package Library;

import java.util.Scanner;

public class ReturnBook implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter book title: ");
        Scanner s = new Scanner(System.in);
        String bookName = s.next();
        if (!database.getBrws().isEmpty()){
            for(Borrowing b : database.getBrws()){
                if(b.getBook().getName().matches(bookName)&&
                b.getUser().getName().matches(user.getName())){
                    Book book = b.getBook();
                    int i = database.getAllBooks().indexOf(book);
                    if(b.getDaysleft()>0){
                        System.out.println("You are late! You have to pay" + b.getDaysleft() * 2 + " lei as fine\n");
                    }
                    book.setBrwcopies(book.getBrwcopies()+1);
                    database.returnBook(b, book, i);
                    System.out.println("Book returned! Thank you!\n");
                    break;
                } else {
                    System.out.println("You did not borrow this book!\n");

                }
            }
        } else {
            System.out.println("You did not borrow this book!\n");
        }
    user.menu(database, user);
    }
}
