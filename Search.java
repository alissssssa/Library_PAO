package Library;

import java.util.Scanner;

public class Search implements IOOperation{
    @Override
    public void oper(Database database, User user) {
        System.out.println("\nEnter book's title");
        Scanner s = new Scanner(System.in);
        String name = s.next();
        int i = database.getBook(name);
        if(i > -1){
            System.out.println("\n"+ database.getBook(i).toString()+"\n");
        } else {
            System.out.println("Book does not exist\n");
        }
        user.menu(database, user);
    }
}
