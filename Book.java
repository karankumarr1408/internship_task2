import java.io.Serializable;

public class Book implements Serializable {
    private static final long serialVersionUID = 1L;

    private int bookId;
    private String title;
    private String author;
    private String genre;
    private int totalCopies;
    private int availableCopies;
    private boolean isIssued;
    private String issuedTo;   

    public Book(int bookId, String title, String author, String genre, int totalCopies) {
        this.bookId        = bookId;
        this.title         = title;
        this.author        = author;
        this.genre         = genre;
        this.totalCopies   = totalCopies;
        this.availableCopies = totalCopies;
        this.isIssued      = false;
        this.issuedTo      = "None";
    }

    public boolean issueBook(String studentName) {
        if (availableCopies <= 0) {
            System.out.println(" No copies available for \"" + title + "\".");
            return false;
        }
        availableCopies--;
        isIssued  = (availableCopies == 0);
        issuedTo  = studentName;
        System.out.println(" Book \"" + title + "\" issued to " + studentName + " successfully.");
        System.out.println("   Remaining copies: " + availableCopies);
        return true;
    }

    public boolean returnBook() {
        if (availableCopies >= totalCopies) {
            System.out.println(" All copies already returned for \"" + title + "\".");
            return false;
        }
        availableCopies++;
        isIssued = false;
        issuedTo = "None";
        System.out.println(" Book \"" + title + "\" returned successfully.");
        System.out.println("   Available copies: " + availableCopies);
        return true;
    }

    public void displayBook() {
        System.out.println("\n┌──────────────────────────────────────────┐");
        System.out.printf("│  Book ID        : %-23d│%n", bookId);
        System.out.printf("│  Title          : %-23s│%n", truncate(title, 23));
        System.out.printf("│  Author         : %-23s│%n", truncate(author, 23));
        System.out.printf("│  Genre          : %-23s│%n", genre);
        System.out.printf("│  Total Copies   : %-23d│%n", totalCopies);
        System.out.printf("│  Available      : %-23d│%n", availableCopies);
        System.out.printf("│  Status         : %-23s│%n", availableCopies > 0 ? "Available" : "All Issued");
        System.out.printf("│  Issued To      : %-23s│%n", issuedTo);
        System.out.println("└──────────────────────────────────────────┘");
    }

    public void displaySummary() {
        String status = availableCopies > 0 ? "Available" : "All Issued";
        System.out.printf("│ %-4d │ %-22s │ %-15s │ %-3d/%-3d │ %-10s │%n",
                bookId, truncate(title, 22), truncate(author, 15),
                availableCopies, totalCopies, status);
    }

    private String truncate(String s, int max) {
        return s.length() > max ? s.substring(0, max - 2) + ".." : s;
    }

     public int    getBookId()          { return bookId; }
    public String getTitle()           { return title; }
    public String getAuthor()          { return author; }
    public String getGenre()           { return genre; }
    public int    getTotalCopies()     { return totalCopies; }
    public int    getAvailableCopies() { return availableCopies; }
    public boolean isIssued()          { return isIssued; }
    public String getIssuedTo()        { return issuedTo; }
}