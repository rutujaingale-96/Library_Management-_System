import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Abstract class for common library items
abstract class LibraryItem {
    private String id;
    private String title;
    private boolean isAvailable;

    public LibraryItem(String id, String title) {
        this.id = id;
        this.title = title;
        this.isAvailable = true;
    }

    public String getId() { return id; }
    public String getTitle() { return title; }
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    public abstract void displayInfo();
}

// Book class extending LibraryItem
class Book extends LibraryItem {
    private String author;

    public Book(String id, String title, String author) {
        super(id, title);
        this.author = author;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book ID: " + getId() +
                ", Title: " + getTitle() +
                ", Author: " + author +
                ", Available: " + (isAvailable() ? "Yes" : "No"));
    }
}

// User class
class User {
    private String userId;
    private String name;

    public User(String userId, String name) {
        this.userId = userId;
        this.name = name;
    }

    public String getUserId() { return userId; }
    public String getName() { return name; }
}

// Library class to manage items and users
class Library {
    private List<LibraryItem> items = new ArrayList<>();
    private List<User> users = new ArrayList<>();

    public void addItem(LibraryItem item) {
        items.add(item);
        System.out.println("Item added successfully.");
    }

    public void registerUser(User user) {
        users.add(user);
        System.out.println("User registered successfully.");
    }

    public void displayAllItems() {
        if (items.isEmpty()) {
            System.out.println("No items in the library.");
            return;
        }
        for (LibraryItem item : items) {
            item.displayInfo();
        }
    }

    public void borrowItem(String itemId, String userId) {
        LibraryItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (!item.isAvailable()) {
            System.out.println("Item is already borrowed.");
            return;
        }
        if (findUserById(userId) == null) {
            System.out.println("User not found.");
            return;
        }
        item.setAvailable(false);
        System.out.println("Item borrowed successfully.");
    }

    public void returnItem(String itemId) {
        LibraryItem item = findItemById(itemId);
        if (item == null) {
            System.out.println("Item not found.");
            return;
        }
        if (item.isAvailable()) {
            System.out.println("Item was not borrowed.");
            return;
        }
        item.setAvailable(true);
        System.out.println("Item returned successfully.");
    }

    private LibraryItem findItemById(String id) {
        for (LibraryItem item : items) {
            if (item.getId().equalsIgnoreCase(id)) {
                return item;
            }
        }
        return null;
    }

    private User findUserById(String id) {
        for (User user : users) {
            if (user.getUserId().equalsIgnoreCase(id)) {
                return user;
            }
        }
        return null;
    }
}

// Main class
public class LibraryManagementSystem1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();
        int choice;

        do {
            System.out.println("\n=== Library Management System ===");
            System.out.println("1. Add Book");
            System.out.println("2. Register User");
            System.out.println("3. Display All Items");
            System.out.println("4. Borrow Item");
            System.out.println("5. Return Item");
            System.out.println("0. Exit");
            System.out.print("Enter choice: ");

            while (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Book ID: ");
                    String bookId = sc.nextLine();
                    System.out.print("Enter Title: ");
                    String title = sc.nextLine();
                    System.out.print("Enter Author: ");
                    String author = sc.nextLine();
                    library.addItem(new Book(bookId, title, author));
                    break;

                case 2:
                    System.out.print("Enter User ID: ");
                    String userId = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    library.registerUser(new User(userId, name));
                    break;

                case 3:
                    library.displayAllItems();
                    break;

                case 4:
                    System.out.print("Enter Item ID to borrow: ");
                    String borrowId = sc.nextLine();
                    System.out.print("Enter User ID: ");
                    String borrowUserId = sc.nextLine();
                    library.borrowItem(borrowId, borrowUserId);
                    break;

                case 5:
                    System.out.print("Enter Item ID to return: ");
                    String returnId = sc.nextLine();
                    library.returnItem(returnId);
                    break;

                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        } while (choice != 0);

        sc.close();
    }
}
