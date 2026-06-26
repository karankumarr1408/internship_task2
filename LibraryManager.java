import java.util.ArrayList;
import java.util.List;

public class LibraryManager {
    private List<Book> books;
    private int nextId;

    public LibraryManager() {
        books  = FileHandler.loadBooks();   
        nextId = computeNextId();
    }

    private int computeNextId() {
        int max = 0;
        for (Book b : books) {
            if (b.getBookId() > max) max = b.getBookId();
        }
        return max + 1;
    }

    public void addBook(String title, String author, String genre, int copies) {
        Book b = new Book(nextId++, title, author, genre, copies);
        books.add(b);
        FileHandler.saveBooks(books);
        System.out.println(" Book added! ID: " + (nextId - 1));
    }

    public void viewAllBooks() {
        if (books.isEmpty()) { System.out.println("  No books in library."); return; }
        System.out.println("\n╔══════════════════════════════════════════════════════════════╗");
        System.out.println("║                     ALL LIBRARY BOOKS                        ║");
        System.out.println("╠══════╦════════════════╦═════════════════╦═════════╦════════════╣");
        System.out.println("║  ID  ║ Title          ║ Author          ║ Copies  ║ Status     ║");
        System.out.println("╠══════╬════════════════╬═════════════════╬═════════╬════════════╣");
        for (Book b : books) b.displaySummary();
        System.out.println("╚══════╩════════════════╩═════════════════╩═════════╩════════════╝");
        System.out.println("  Total Books in Library: " + books.size());
    }

    public void searchByTitle(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books)
            if (b.getTitle().toLowerCase().contains(keyword.toLowerCase()))
                results.add(b);

        if (results.isEmpty()) {
            System.out.println("  No books found with title containing: \"" + keyword + "\"");
            return;
        }
        System.out.println("\n Search results for \"" + keyword + "\" (" + results.size() + " found):");
        for (Book b : results) b.displayBook();
    }

    public void searchByAuthor(String keyword) {
        List<Book> results = new ArrayList<>();
        for (Book b : books)
            if (b.getAuthor().toLowerCase().contains(keyword.toLowerCase()))
                results.add(b);

        if (results.isEmpty()) {
            System.out.println("  No books found by author: \"" + keyword + "\"");
            return;
        }
        System.out.println("\nBooks by \"" + keyword + "\" (" + results.size() + " found):");
        for (Book b : results) b.displayBook();
    }

    public void viewBook(int id) {
        Book b = findById(id);
        if (b == null) { System.out.println(" Book ID " + id + " not found."); return; }
        b.displayBook();
    }

    public void issueBook(int id, String studentName) {
        Book b = findById(id);
        if (b == null) { System.out.println(" Book ID " + id + " not found."); return; }
        if (b.issueBook(studentName)) FileHandler.saveBooks(books);
    }

    public void returnBook(int id) {
        Book b = findById(id);
        if (b == null) { System.out.println(" Book ID " + id + " not found."); return; }
        if (b.returnBook()) FileHandler.saveBooks(books);
    }

    public void deleteBook(int id) {
        Book b = findById(id);
        if (b == null) { System.out.println(" Book ID " + id + " not found."); return; }
        books.remove(b);
        FileHandler.saveBooks(books);
        System.out.println(" Book \"" + b.getTitle() + "\" deleted from library.");
    }

    public void viewIssuedBooks() {
        List<Book> issued = new ArrayList<>();
        for (Book b : books) if (!b.isIssued() == false || b.getAvailableCopies() < b.getTotalCopies())
            issued.add(b);

        if (issued.isEmpty()) { System.out.println("  No books are currently issued."); return; }
        System.out.println("\n Currently Issued Books:");
        for (Book b : issued) b.displayBook();
    }

    public void exportReport() { FileHandler.exportToText(books); }

    private Book findById(int id) {
        for (Book b : books) if (b.getBookId() == id) return b;
        return null;
    }

    public int getTotalBooks() { return books.size(); }
}