import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String FILE_PATH = "library_data.dat";

    public static void saveBooks(List<Book> books) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_PATH))) {
            oos.writeObject(books);
            System.out.println(" Data saved to file successfully.");
        } catch (IOException e) {
            System.out.println(" Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Book> loadBooks() {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            System.out.println(" No existing data found. Starting fresh library.");
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_PATH))) {
            List<Book> books = (List<Book>) ois.readObject();
            System.out.println(" Loaded " + books.size() + " book(s) from file.");
            return books;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(" Error loading data: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public static void exportToText(List<Book> books) {
        String txtPath = "library_report.txt";
        try (PrintWriter pw = new PrintWriter(new FileWriter(txtPath))) {
            pw.println("==============================");
            pw.println("   LIBRARY MANAGEMENT REPORT  ");
            pw.println("==============================");
            pw.println("Total Books: " + books.size());
            pw.println();
            for (Book b : books) {
                pw.println("Book ID   : " + b.getBookId());
                pw.println("Title     : " + b.getTitle());
                pw.println("Author    : " + b.getAuthor());
                pw.println("Genre     : " + b.getGenre());
                pw.println("Copies    : " + b.getAvailableCopies() + "/" + b.getTotalCopies());
                pw.println("Status    : " + (b.getAvailableCopies() > 0 ? "Available" : "All Issued"));
                pw.println("Issued To : " + b.getIssuedTo());
                pw.println("------------------------------");
            }
            System.out.println(" Report exported to: " + txtPath);
        } catch (IOException e) {
            System.out.println(" Error exporting report: " + e.getMessage());
        }
    }

    public static String getFilePath() { return FILE_PATH; }
}