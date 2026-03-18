import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Library {
    private List<Book> books;
    private List<Member> members;
    private List<Transaction> transactions;

    public Library() {
        this.books = new ArrayList<>();
        this.members = new ArrayList<>();
        this.transactions = new ArrayList<>();
    }

    public void addBook(Book book) {
        for (Book b : books) {
            if (b.getId().equals(book.getId())) {
                System.out.println("Book with this ID already exists.");
                return;
            }
        }
        books.add(book);
        System.out.println("Book added successfully.");
    }

    public void registerMember(Member member) {
        for (Member m : members) {
            if (m.getMemberId().equals(member.getMemberId())) {
                System.out.println("Member with this ID already exists.");
                return;
            }
        }
        members.add(member);
        System.out.println("Member registered successfully.");
    }

    public void displayAllBooks() {
        if (books.isEmpty()) {
            System.out.println("No books available in the library.");
            return;
        }
        System.out.println("--- Library Books ---");
        for (Book book : books) {
            book.displayDetails();
        }
    }

    public void displayAllMembers() {
        if (members.isEmpty()) {
            System.out.println("No members registered.");
            return;
        }
        System.out.println("--- Registered Members ---");
        for (Member member : members) {
            member.displayMemberDetails();
        }
    }

    public void issueBook(String memberId, String bookId) throws LibraryException {
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new LibraryException("Member not found with ID: " + memberId);
        }

        Book book = findBookById(bookId);
        if (book == null) {
            throw new LibraryException("Book not found with ID: " + bookId);
        }

        if (!book.isAvailable()) {
            throw new LibraryException("Book is currently not available for issue.");
        }

        // Proceed to issue
        book.setAvailable(false);
        member.issueBook(book);
        
        String transactionId = "TXN" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        Transaction transaction = new Transaction(transactionId, member, book);
        transactions.add(transaction);

        System.out.println("Book issued successfully! Transaction ID: " + transactionId);
    }

    public void returnBook(String memberId, String bookId) throws LibraryException {
        Member member = findMemberById(memberId);
        if (member == null) {
            throw new LibraryException("Member not found with ID: " + memberId);
        }

        Book book = findBookById(bookId);
        if (book == null) {
            throw new LibraryException("Book not found with ID: " + bookId);
        }

        if (!member.getIssuedBooks().contains(book)) {
            throw new LibraryException("This book was not issued to this member.");
        }

        // Proceed to return
        book.setAvailable(true);
        member.returnBook(book);

        // Find the active transaction and mark it returned
        for (Transaction txn : transactions) {
            if (txn.getMember().equals(member) && txn.getBook().equals(book) && txn.getReturnDate() == null) {
                txn.setReturnDate(LocalDateTime.now());
                break;
            }
        }

        System.out.println("Book returned successfully!");
    }

    private Member findMemberById(String memberId) {
        for (Member m : members) {
            if (m.getMemberId().equals(memberId)) {
                return m;
            }
        }
        return null;
    }

    private Book findBookById(String bookId) {
        for (Book b : books) {
            if (b.getId().equals(bookId)) {
                return b;
            }
        }
        return null;
    }
}
