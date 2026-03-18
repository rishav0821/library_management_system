import java.util.ArrayList;
import java.util.List;

public class Member {
    private String memberId;
    private String name;
    private List<Book> issuedBooks;

    public Member(String memberId, String name) {
        this.memberId = memberId;
        this.name = name;
        this.issuedBooks = new ArrayList<>();
    }

    public String getMemberId() {
        return memberId;
    }

    public void setMemberId(String memberId) {
        this.memberId = memberId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getIssuedBooks() {
        return issuedBooks;
    }

    public void issueBook(Book book) {
        issuedBooks.add(book);
    }

    public void returnBook(Book book) {
        issuedBooks.remove(book);
    }

    public void displayMemberDetails() {
        System.out.printf("Member ID: %s | Name: %s | Issued Books Count: %d%n", 
                          memberId, name, issuedBooks.size());
        if (!issuedBooks.isEmpty()) {
            System.out.println("  Issued Books:");
            for (Book b : issuedBooks) {
                System.out.println("    - " + b.getTitle());
            }
        }
    }
}
