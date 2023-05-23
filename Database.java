package Library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Database {
    private ArrayList<User> users = new ArrayList<User>();
    private ArrayList<String> usernames = new ArrayList<String>();
    private ArrayList<Book> books = new ArrayList<Book>();
    private ArrayList<String> bookNames = new ArrayList<String>();
    private ArrayList<Borrowing> borrowings = new ArrayList<Borrowing>();

    private File usersfile = new File("//Users//alisadraghici//IdeaProjects//Library//src//data//Users");
    private File booksfile = new File("//Users//alisadraghici//IdeaProjects//Library//src//data//Books");
    private File borrowingsfile = new File("//Users//alisadraghici//IdeaProjects//Library//src//data//Borrowings");
    private File folder = new File("//Users//alisadraghici//IdeaProjects//Library//src//data//");

    public Database(){
        if (!folder.exists()){
            folder.mkdirs();
        }
        if (!usersfile.exists()){
            try{
            usersfile.createNewFile();
        }catch (Exception e){

            }
        }
        if (!booksfile.exists()){
            try{
            booksfile.createNewFile();}
            catch (Exception e){

            }
        }

        if (!borrowingsfile.exists()){
            try{
                borrowingsfile.createNewFile();}
            catch (Exception e){

            }
        }

        getUsers();
        getBooks();
        getBorrowings();
    }
    public void AddUser (User s){
        users.add(s);
        usernames.add(s.getName());
        saveUsers();

        Audit audit = new Audit("Add User");
        audit.saveAuditAction();
    }
    public int login(String phoneNumber, String email){
        int n = -1;
        for (User s: users){
            if(s.getPhoneNumber().matches(phoneNumber)&&s.getEmail().matches(email)){
                n = users.indexOf(s);
                break;
            }
            Audit audit = new Audit("Log in");
            audit.saveAuditAction();
        }
        return n;
    }
    public User getUser(int n){
        return users.get(n);

    }

    public void AddBook(Book book){
        books.add(book);
        bookNames.add(book.getName());
        saveBooks();
        Audit audit = new Audit("Add book");
        audit.saveAuditAction();
    }
    private void getUsers(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(usersfile));
            String s1;
            while ((s1 = br1.readLine())!= null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.matches("")|| !text1.isEmpty()){
            String[] a1 = text1.split("<NewUser/>");
            for (String s : a1)
            {
                String[] a2 = s.split(",");
                if(a2[3].matches("Admin")){
                    User user = new Admin(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());
                } else{
                    User user = new RegularUser(a2[0], a2[1], a2[2]);
                    users.add(user);
                    usernames.add(user.getName());
                }
            }
        }
    }

    private void saveUsers(){
        String text1 = "";
        for(User user : users){
            text1 = text1 + user.toString() + "<NewUser/>\n";
        }
    try {
        PrintWriter pw = new PrintWriter(usersfile);
        pw.print(text1);
        pw.close();
    }    catch (Exception e){
        System.err.println(e.toString());
    }
    }

    private void saveBooks(){
        String text1 = "";
        for(Book book : books){
            text1 = text1 + book.toString2() + "<NewBook/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(booksfile);
            pw.print(text1);
            pw.close();
        }    catch (Exception e){
            System.err.println(e.toString());
        }
    }

    public boolean userExists (String name){
        boolean f = false;
        for (User user:users){
            if(user.getName().toLowerCase().matches(name.toLowerCase())){
                f = true;
                break;
            }
        }
        return f;
    }

    private User getUserByName(String name){
    User u = new RegularUser("");
    for(User user: users){
        if(user.getName().matches(name)){
            u = user;
            break;
        }
    }
    return u;
    }

    private void getBooks(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(booksfile));
            String s1;
            while ((s1 = br1.readLine())!= null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.matches("")|| !text1.isEmpty()){
            String[] a1 = text1.split("<NewBook/>");
            for (String s : a1){
                Book book = parseBook(s);
                books.add(book);
                bookNames.add(book.getName());

            }

        }
    }

    public Book parseBook (String s){
        String[] a = s.split(",");
        Book book = new Book();
        book.setName(a[0]);
        book.setAuthor(a[1]);
        book.setDate(a[2]);
        book.setCategory(a[3]);
        book.setBrwcopies(Integer.parseInt(a[4]));
        return book;
    }

    public ArrayList<Book> getAllBooks(){
        return books;
    }

    public int getBook(String bookName){
        int i = -1;
        for(Book book :books){
            if(book.getName().matches(bookName)){
            i = books.indexOf(book);}

        }
        return i;
    }
    public Book getBook( int i){
        return books.get(i);
    }
    public void deleteBook(int i){
        books.remove(i);
        bookNames.remove(i);
        saveBooks();
        Audit audit = new Audit("Book deleted");
        audit.saveAuditAction();
    }

    public void deleteAllData(){
        if (usersfile.exists()) {
            try {
                usersfile.delete();

            } catch (Exception e) {
            }
        }
        if (booksfile.exists()){
            try{
                booksfile.delete();
              }
            catch (Exception e){

            }
        }

        if (borrowingsfile.exists()){
            try{
                borrowingsfile.delete();}
            catch (Exception e){
            }
        }

        Audit audit = new Audit("All data deleted");
        audit.saveAuditAction();

    }


    private void saveBorrowings(){
        String text1 = "";
        for(Borrowing borrowing : borrowings){
            text1 = text1 + borrowing.toString2() + "<NewBorrowing/>\n";
        }
        try {
            PrintWriter pw = new PrintWriter(borrowingsfile);
            pw.print(text1);
            pw.close();
        }    catch (Exception e){
            System.err.println(e.toString());
        }
    }

    private void getBorrowings(){
        String text1 = "";
        try{
            BufferedReader br1 = new BufferedReader(new FileReader(borrowingsfile));
            String s1;
            while ((s1 = br1.readLine())!= null) {
                text1 = text1 + s1;
            }
            br1.close();
        } catch (Exception e){
            System.err.println(e.toString());
        }

        if (!text1.matches("")|| !text1.isEmpty()){
            String[] a1 = text1.split("<NewBorrowing/>");
            for (String s : a1){
                Borrowing borrowing = parseBorrowing(s);
                borrowings.add(borrowing);
            }

        }
    }

    private Borrowing parseBorrowing(String s){
        String[] a = s.split(",");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("uuuu-MM-dd");
        LocalDate start = LocalDate.parse(a[0],formatter);
        LocalDate finish = LocalDate.parse(a[1],formatter);
        Book book = getBook(getBook(a[3]));
        User user = getUserByName(a[4]);
        Borrowing brw = new Borrowing(start, finish, book,user);

        return brw;
    }

    public void borrowBook(Borrowing brw, Book book, int bookIndex){
        borrowings.add(brw);
        books.set(bookIndex, book);
        saveBorrowings();
        saveBooks();
        Audit audit = new Audit("Book borrowed");
        audit.saveAuditAction();
    }

    public  ArrayList<Borrowing> getBrws(){
        return borrowings;

    }

    public void returnBook(Borrowing b, Book book, int bookIndex) {
        borrowings.remove(b);
        books.set(bookIndex,book);
        saveBorrowings();
        saveBooks();
        Audit audit = new Audit("Book returned");
        audit.saveAuditAction();
    }

    public void updateBookInfo(int i, Book updatedBook) {
        books.set(i, updatedBook);
        saveBooks();
        Audit audit = new Audit("Book info updated");
        audit.saveAuditAction();
    }

}
