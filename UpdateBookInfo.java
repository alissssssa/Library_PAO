package Library;

import java.util.Scanner;

public class UpdateBookInfo implements IOOperation {

    @Override
    public void oper(Database database, User user) {
        System.out.println("Enter the book's title:");
        Scanner s = new Scanner(System.in);
        String bookName = s.next();

        int i = database.getBook(bookName);
        if (i != -1) {
            Book book = database.getBook(i);
            System.out.println("What would you like to update?");
            System.out.println("1. Title\n2. Author\n3. Date\n4. Category\n5. Borrowing copies");
            int num = s.nextInt();

            switch (num) {
                case 1:
                    System.out.println("Enter the new title:");
                    String name = s.next();
                    book.setName(name);
                    break;
                case 2:
                    System.out.println("Enter the new author:");
                    String author = s.next();
                    book.setAuthor(author);
                    break;
                case 3:
                    System.out.println("Enter the new date:");
                    String date = s.next();
                    book.setDate(date);
                    break;
                case 4:
                    System.out.println("Enter the new category:");
                    String category = s.next();
                    book.setCategory(category);
                    break;
                case 5:
                    System.out.println("Enter the new number of borrowing copies:");
                    int brwcopies = s.nextInt();
                    book.setBrwcopies(brwcopies);
                    break;

                default:
                    System.out.println("Please enter a number between 1-5");
            }

            System.out.println("Book information updated successfully!");
            database.updateBookInfo(i , book);
        } else {
            System.out.println("Book not found!");
        }

        user.menu(database, user);
    }
}

