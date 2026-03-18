import java.util.Scanner;

public class Main {
    private static Library library = new Library();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;

        // Add some initial data so it's not totally empty (optional)
        library.addBook(new Book("B001", "Effective Java", "Joshua Bloch"));
        library.addBook(new Book("B002", "Clean Code", "Robert C. Martin"));
        library.registerMember(new Member("M001", "Alice Smith"));
        System.out.println("Initial dummy data loaded (2 books, 1 member).");

        while (!exit) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceStr = scanner.nextLine();
            
            try {
                int choice = Integer.parseInt(choiceStr);
                System.out.println(); // padding
                
                switch (choice) {
                    case 1:
                        addBook();
                        break;
                    case 2:
                        library.displayAllBooks();
                        break;
                    case 3:
                        registerMember();
                        break;
                    case 4:
                        library.displayAllMembers();
                        break;
                    case 5:
                        issueBook();
                        break;
                    case 6:
                        returnBook();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Thank you for using the Library Management System!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid number.");
            } catch (LibraryException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println(); // Print a blank line for readability
        }
        
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("===============================");
        System.out.println("   Library Management System   ");
        System.out.println("===============================");
        System.out.println("1. Add a Book");
        System.out.println("2. View All Books");
        System.out.println("3. Register a Member");
        System.out.println("4. View All Members");
        System.out.println("5. Issue a Book");
        System.out.println("6. Return a Book");
        System.out.println("7. Exit");
        System.out.println("===============================");
    }

    private static void addBook() {
        System.out.print("Enter Book ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Book Title: ");
        String title = scanner.nextLine();
        System.out.print("Enter Book Author: ");
        String author = scanner.nextLine();

        Book newBook = new Book(id, title, author);
        library.addBook(newBook);
    }

    private static void registerMember() {
        System.out.print("Enter Member ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Member Name: ");
        String name = scanner.nextLine();

        Member newMember = new Member(id, name);
        library.registerMember(newMember);
    }

    private static void issueBook() throws LibraryException {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        library.issueBook(memberId, bookId);
    }

    private static void returnBook() throws LibraryException {
        System.out.print("Enter Member ID: ");
        String memberId = scanner.nextLine();
        System.out.print("Enter Book ID: ");
        String bookId = scanner.nextLine();

        library.returnBook(memberId, bookId);
    }
}
