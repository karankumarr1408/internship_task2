import java.util.InputMismatchException;
import java.util.Scanner;

public class LibraryManagementSystem {

    private static Scanner sc      = new Scanner(System.in);
    private static LibraryManager manager;

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║    FILE-BASED LIBRARY MANAGEMENT SYSTEM      ║");
        System.out.println("╚══════════════════════════════════════════════╝");

        manager = new LibraryManager();   
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = getIntInput("Enter your choice: ");
            switch (choice) {
                case 1:  addBook();          break;
                case 2:  viewAllBooks();     break;
                case 3:  viewBookById();     break;
                case 4:  searchByTitle();    break;
                case 5:  searchByAuthor();   break;
                case 6:  issueBook();        break;
                case 7:  returnBook();       break;
                case 8:  viewIssuedBooks();  break;
                case 9:  deleteBook();       break;
                case 10: exportReport();     break;
                case 11:
                    System.out.println("\n All data saved. Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println(" Invalid choice. Enter 1-11.");
            }
        }
        sc.close();
    }

    private static void displayMenu() {
        System.out.println("\n──────── MAIN MENU ────────────────");
        System.out.println("  1.  Add New Book");
        System.out.println("  2.  View All Books");
        System.out.println("  3.  View Book by ID");
        System.out.println("  4.  Search by Title");
        System.out.println("  5.  Search by Author");
        System.out.println("  6.  Issue a Book");
        System.out.println("  7.  Return a Book");
        System.out.println("  8.  View Issued Books");
        System.out.println("  9.  Delete a Book");
        System.out.println("  10. Export Library Report (.txt)");
        System.out.println("  11. Exit");
        System.out.println("────────────────────────────────────");
    }


    private static void addBook() {
        System.out.println("\n──── Add New Book ────");

        System.out.print("Enter Book Title  : ");
        String title = sc.nextLine().trim();
        if (title.isEmpty()) { System.out.println(" Title cannot be empty."); return; }

        System.out.print("Enter Author Name : ");
        String author = sc.nextLine().trim();
        if (author.isEmpty()) { System.out.println(" Author cannot be empty."); return; }

        System.out.print("Enter Genre       : ");
        String genre = sc.nextLine().trim();
        if (genre.isEmpty()) { System.out.println(" Genre cannot be empty."); return; }

        int copies = getIntInput("Enter No. of Copies: ");
        if (copies <= 0) { System.out.println(" Copies must be at least 1."); return; }

        manager.addBook(title, author, genre, copies);
    }

    private static void viewAllBooks()   { manager.viewAllBooks(); }

    private static void viewBookById() {
        System.out.println("\n──── View Book ────");
        int id = getIntInput("Enter Book ID: ");
        manager.viewBook(id);
    }

    private static void searchByTitle() {
        System.out.println("\n──── Search by Title ────");
        System.out.print("Enter title keyword: ");
        String kw = sc.nextLine().trim();
        if (kw.isEmpty()) { System.out.println(" Keyword cannot be empty."); return; }
        manager.searchByTitle(kw);
    }

    private static void searchByAuthor() {
        System.out.println("\n──── Search by Author ────");
        System.out.print("Enter author name: ");
        String kw = sc.nextLine().trim();
        if (kw.isEmpty()) { System.out.println(" Keyword cannot be empty."); return; }
        manager.searchByAuthor(kw);
    }

    private static void issueBook() {
        System.out.println("\n──── Issue a Book ────");
        int id = getIntInput("Enter Book ID to issue: ");
        System.out.print("Enter Student Name   : ");
        String name = sc.nextLine().trim();
        if (name.isEmpty()) { System.out.println(" Student name cannot be empty."); return; }
        manager.issueBook(id, name);
    }

    private static void returnBook() {
        System.out.println("\n──── Return a Book ────");
        int id = getIntInput("Enter Book ID to return: ");
        manager.returnBook(id);
    }

    private static void viewIssuedBooks() { manager.viewIssuedBooks(); }

    private static void deleteBook() {
        System.out.println("\n──── Delete a Book ────");
        int id = getIntInput("Enter Book ID to delete: ");
        System.out.print("Confirm delete? (yes/no): ");
        String confirm = sc.nextLine().trim();
        if (confirm.equalsIgnoreCase("yes")) manager.deleteBook(id);
        else System.out.println("  Delete cancelled.");
    }

    private static void exportReport() { manager.exportReport(); }

    private static int getIntInput(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                int val = sc.nextInt();
                sc.nextLine();
                return val;
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
}