public class Book extends LibraryItem {
    private String author;
    private boolean isAvailable;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
        this.isAvailable = true; // newly added books are available by default
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public void displayDetails() {
        System.out.printf("Book ID: %s | Title: %s | Author: %s | Available: %s%n", 
                          getId(), getTitle(), author, isAvailable ? "Yes" : "No");
    }
}
