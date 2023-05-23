package Library;

public class Book {

    private String name;
    private String author;
    private String date; // the year the book was written in
    private String category;
    private String status; //borrowing status
    private int brwcopies; //copies for borrowing

    public Book(){
    };

    public Book (String name, String author, String date, String category, int brwcopies){
        this.name = name;
        this.author = author;
        this.date = date;
        this.category = category;
        this.brwcopies = brwcopies;
    }

    public String toString() {
        String text = "Book name: " + name + " author: " + author + " date: " + date + " category: "
                + category + " copies to borrow: " + String.valueOf(brwcopies);
        return text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBrwcopies() {
        return brwcopies;
    }

    public void setBrwcopies(int brwcopies) {
        this.brwcopies = brwcopies;
    }

    public String toString2(){
        String text = name + "," + author + "," + date + "," +
                category + "," + String.valueOf(brwcopies);
        return text;
    }

}
