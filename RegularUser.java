package Library;

import java.util.Scanner;

public class RegularUser extends User{
    public RegularUser(String name){
        super(name);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }

    public RegularUser(String name, String email, String phoneNumber){
        super(name, email, phoneNumber);
        this.operations = new IOOperation[]{
                new ViewBooks(),
                new Search(),
                new BorrowBook(),
                new CalculateFine(),
                new ReturnBook(),
                new Exit()
        };
    }
    @Override
    public void menu(Database database, User user){
        System.out.println("1. View Books");
        System.out.println("2. Search");
        System.out.println("3. Borrow");
        System.out.println("4. Calculate fine");
        System.out.println("5. Return book");
        System.out.println("6. Exit");

        Scanner s = new Scanner(System.in);
        int n = s.nextInt();
        this.operations[n-1].oper(database, user);
        s.close();
    }
    public String toString() {
        return name + "," + email+ "," + phoneNumber + ","+"Regular User";
    }
}
