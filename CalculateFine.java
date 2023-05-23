package Library;

import java.util.Scanner;

public class CalculateFine implements IOOperation {
    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter the book's title:");
        Scanner s = new Scanner(System.in);
        String bookName = s.next();


        boolean g = true;

        for (Borrowing b : database.getBrws()) {
            if (b.getBook().getName().matches(bookName) && b.getUser().getName().matches(user.getName())) {

                if (b.getDaysleft() > 0) {
                   System.out.println("You are late! You have to pay" + b.getDaysleft() * 2 + " lei as fine\n");
                } else {
                    System.out.println("You do not have to pay a fine!\n");
                }
                g = false;
                break;
            }
        }

        if(g){
            System.out.println("You did not borrow this book!");

        }        user.menu(database, user);
    }
}