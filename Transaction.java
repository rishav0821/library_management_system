import java.time.LocalDateTime;

public class Transaction {
    private String transactionId;
    private Member member;
    private Book book;
    private LocalDateTime issueDate;
    private LocalDateTime returnDate;

    public Transaction(String transactionId, Member member, Book book) {
        this.transactionId = transactionId;
        this.member = member;
        this.book = book;
        this.issueDate = LocalDateTime.now();
    }

    public String getTransactionId() {
        return transactionId;
    }

    public Member getMember() {
        return member;
    }

    public Book getBook() {
        return book;
    }

    public LocalDateTime getIssueDate() {
        return issueDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }
}
