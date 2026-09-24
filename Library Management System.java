class Book {
    int bookId;
    String title;
    String author;
    double price;

    public Book(int bookId, String title, String author, double price) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.price = price;
    }
}

public class LibraryManagementSystem {

    // Task 1: Remove duplicate books in-place
    public static int removeDuplicates(Book[] books, int n) {

        if (n == 0) {
            return 0;
        }

        int uniqueIndex = 1;

        for (int i = 1; i < n; i++) {

            if (books[i].bookId != books[uniqueIndex - 1].bookId) {
                books[uniqueIndex] = books[i];
                uniqueIndex++;
            }
        }

        return uniqueIndex;
    }

    // Task 2: Search books by partial title
    public static void searchByTitle(Book[] books, int count, String query) {

        System.out.println("Search Results for '" + query + "':");

        boolean found = false;

        for (int i = 0; i < count; i++) {

            if (books[i].title.toLowerCase().contains(query.toLowerCase())) {

                System.out.println("- Found: [" + books[i].bookId + "] "
                        + books[i].title + " (Rs. " + books[i].price + ")");

                found = true;
            }
        }

        if (!found) {
            System.out.println("No books found.");
        }
    }

    // Task 3: Sort books by price using Selection Sort
    public static void sortByPrice(Book[] books, int count) {

        int swaps = 0;

        for (int i = 0; i < count - 1; i++) {

            int minIndex = i;

            for (int j = i + 1; j < count; j++) {

                if (books[j].price < books[minIndex].price) {
                    minIndex = j;
                }
            }

            if (minIndex != i) {

                Book temp = books[i];
                books[i] = books[minIndex];
                books[minIndex] = temp;

                swaps++;
            }
        }

        System.out.println("Books Sorted by Price:");

        for (int i = 0; i < count; i++) {
            System.out.println((i + 1) + ". [" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }

        System.out.println("Total Swaps: " + swaps);
    }

    // Task 4: Search book by price using Binary Search
    public static int searchByPrice(Book[] books, int count, double targetPrice) {

        int left = 0;
        int right = count - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            if (books[mid].price == targetPrice) {
                return mid;
            }

            if (books[mid].price < targetPrice) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    // Task 5: Minimum consecutive books using Sliding Window
    public static int minBooksForTargetCost(
            Book[] books, int count, double targetCost) {

        int left = 0;
        double currentSum = 0;
        int minLength = Integer.MAX_VALUE;

        for (int right = 0; right < count; right++) {

            currentSum += books[right].price;

            while (currentSum >= targetCost) {

                int currentLength = right - left + 1;

                if (currentLength < minLength) {
                    minLength = currentLength;
                }

                currentSum -= books[left].price;
                left++;
            }
        }

        if (minLength == Integer.MAX_VALUE) {
            return 0;
        }

        return minLength;
    }

    // Display books
    public static void displayBooks(Book[] books, int count) {

        for (int i = 0; i < count; i++) {
            System.out.println("[" + books[i].bookId + "] "
                    + books[i].title + " - Rs. " + books[i].price);
        }
    }

    // Main method
    public static void main(String[] args) {

        Book[] books = {
                new Book(101, "Data Structures", "Mark", 400.0),
                new Book(101, "Data Structures", "Mark", 400.0),
                new Book(102, "Java Basics", "James", 300.0),
                new Book(103, "Python Guide", "Guido", 600.0),
                new Book(104, "Database Systems", "Raghu", 500.0),
                new Book(105, "Computer Networks", "Andrew", 700.0)
        };

        int count = books.length;

        // ---------------- TASK 1 ----------------
        System.out.println("===== TASK 1: REMOVE DUPLICATES =====");

        count = removeDuplicates(books, count);

        System.out.println("Unique Books Count: " + count);

        displayBooks(books, count);


        // ---------------- TASK 2 ----------------
        System.out.println("\n===== TASK 2: SEARCH BY TITLE =====");

        searchByTitle(books, count, "data");


        // ---------------- TASK 3 ----------------
        System.out.println("\n===== TASK 3: SORT BY PRICE =====");

        sortByPrice(books, count);


        // ---------------- TASK 4 ----------------
        System.out.println("\n===== TASK 4: SEARCH BY PRICE =====");

        double targetPrice = 500.0;

        System.out.println("Searching for Price Rs. " + targetPrice + "...");

        int index = searchByPrice(books, count, targetPrice);

        if (index != -1) {

            System.out.println("Result: Book found at index " + index
                    + ": [" + books[index].bookId + "] "
                    + books[index].title
                    + " (Rs. " + books[index].price + ")");

        } else {
            System.out.println("Book not found.");
        }


        // ---------------- TASK 5 ----------------
        System.out.println("\n===== TASK 5: SLIDING WINDOW =====");

        double targetCost = 1000.0;

        System.out.println(
                "Finding minimum consecutive books whose total price >= Rs. "
                        + targetCost + "...");

        int result = minBooksForTargetCost(
                books, count, targetCost);

        System.out.println(
                "Minimum Consecutive Books Needed: " + result);
    }
}