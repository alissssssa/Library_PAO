package Library;

import java.util.Scanner;

public class DeleteBook implements IOOperation{
    @Override
    public void oper(Database database, User user){
        Scanner s = new Scanner(System.in);
        System.out.println("Book' title you want to delete");
        String bookName = s.next();

        int i = database.getBook(bookName);
        if(i > -1){
            database.deleteBook(i);
            System.out.println("Book deleted successfully\n");
        } else {
            System.out.println("Book does not exist\n");
        }
        user.menu(database, user);
    }
}
