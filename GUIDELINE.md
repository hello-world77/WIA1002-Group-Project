## 1. Bus Route and Schedule Tracker using GPS

### Title

**Bus Route and Schedule Tracker using GPS**

### Description

This project develops a system to monitor buses in real time using GPS, manage routes and schedules, and inform passengers about current location, next stop, and estimated arrival time. It can be designed for a campus shuttle, school bus, or city transport service.

### Problem Statement

Passengers often do not know where the bus is or when it will arrive. A GPS-based tracker solves this by combining live location updates with stored route and schedule data.

### Scenario

A student selects the campus shuttle route and stop. The system checks the bus GPS position, determines the next stop, calculates the remaining distance, and displays an estimated arrival time such as:

> “Bus T818 will arrive at the library in 4 minutes”.

### Team and Scope of the Project

Suitable for a group of up to 5 students. Work can be divided into:

- Route design
- Schedule handling
- GPS simulation
- Search / ETA calculation
- User interface / testing

### Data Structures That Can Be Used

| Data Structure | Purpose |
|---|---|
| Graph | Bus stops and routes |
| Queue | Upcoming stops |
| Linked List | Ordered route path |
| Hash Table | Fast bus / stop lookup |
| Priority Queue | Nearest-arrival buses |

---

## 2. Emergency Medical Dispatch

### Title

**Emergency Medical Dispatch**

### Brief Description

This project simulates an ambulance dispatch system for a city. When emergency calls arrive, the system assigns the nearest available ambulance using a **Graph** for routing, prioritizes calls using a **Priority Queue** by medical severity, and queues non-urgent calls using a regular **Queue** when all ambulances are busy.

### Problem Statement

During emergencies, dispatchers waste critical time manually locating ambulances and estimating routes. A system that combines graph-based pathfinding, severity-based prioritization, and fair handling of non-urgent calls can reduce response times and save lives.

### Scenario

A dispatcher receives three calls in quick succession:

1. Heart attack — Severity 1, highest — at Location A
2. Minor car accident — Severity 3, lowest — at Location B
3. House fire with burns — Severity 2, medium — at Location C

### System Behavior

- The Priority Queue automatically orders calls as: **Heart attack → Fire → Car accident**.
- The system checks available ambulances via the Graph, finds the closest ambulance to the heart attack, and computes the shortest route using **Dijkstra’s algorithm**.
- If both ambulances are busy, the fire call waits in the Priority Queue and the car accident waits in the regular Queue.
- When an ambulance becomes free, the system checks the Priority Queue first. If it is empty, it takes the next call from the regular Queue.

### Marking Rubric — Total: 15 Marks

| Criteria | Excellent (3) | Good (2) | Satisfactory (1) | Poor (0) | Marks |
|---|---|---|---|---|---:|
| Graph Implementation & Pathfinding | Graph correctly built with nodes, edges, and weights. Dijkstra works flawlessly for shortest path ETA. | Graph works. Dijkstra works but has minor bug. | Graph incomplete or Dijkstra only works for simple cases. | No graph or no pathfinding. | 3 |
| Priority Queue by Severity | Correct max-heap behavior. Severity 1 is always processed before 2 and 3. Insertion/removal is `O(log n)`. | Works but severity order has one mistake. | Priority exists but is not fully correct. | No priority queue or completely wrong. | 3 |
| Queue — FIFO for Non-Urgent Calls | Correct FIFO. Non-urgent calls are processed only when priority queue is empty. Works with dynamic ambulance availability. | FIFO works but integration with priority queue has minor flaw. | Queue works standalone but is not integrated. | No queue. | 3 |
| System Integration & Workflow | Calls flow correctly: Priority Queue → assign ambulance → route → free ambulance → next call from Priority Queue or regular Queue. | Major flow works but one step is broken. | Partial integration. | No integration. | 2 |
| Code Quality & Modularity | Clean, well-commented, separate files/modules for each data structure. | Good but some duplication. | Messy but readable. | Unreadable. | 2 |
| Demonstration & Report | Clear demo with test cases. Explains why Graph, Priority Queue, and Queue were chosen. Complexity analysis included. | Demo works. Missing complexity analysis. | Minimal demo or report. | No demo. | 2 |

### Deliverables

1. **Source code** in Java, including:
   - Graph class with Dijkstra method
   - Priority Queue class, or heap implementation
   - Queue class, or deque implementation
   - Main simulation loop
2. **Report** — 2 to 3 pages
3. **Demo video** — 5 minutes

---

## 3. Large Number Arithmetic Using Doubly Linked List

### Project Title

**Large Number Arithmetic Using Doubly Linked List**

### Description

This project focuses on the design and implementation of a system that performs arithmetic operations on very large integers using a doubly linked list data structure. Since such numbers may exceed the storage capacity of built-in numeric data types, each digit is stored individually in a node of the doubly linked list. The project supports arithmetic operations such as addition, subtraction, multiplication, and division.

### Goal

Through this project, students are expected to understand how doubly linked lists can be applied in practical computational problems involving large numbers, while also strengthening their knowledge of:

- Linked list traversal
- Dynamic memory management
- Digit-by-digit arithmetic processing

### Example 1

#### Input

```text
m = 123456789123456789123456789123456789123456789123456789
n = 456789123456789123456789123456789123456789123456789
```

#### Output

```text
addition = 123913578246913578246913578246913578246913578246913578
subtraction = 123000000000000000000000000000000000000000000000000000
multiplication = 563937184884934839205932493526930147847927802168925...3035101981191820046486820281054720515622620750190521
division = 270.27085975512599935528
```

### Example 2

#### Input

```text
m = 55
n = 2
```

#### Output

```text
addition = 57
subtraction = 53
multiplication = 110
division = 27.5
```

### Approach

The large number is first divided into individual digits and stored in a doubly linked list.

Addition and subtraction are performed digit by digit following the standard arithmetic method, including the handling of carries and borrows. These basic operations are then used to implement multiplication and division.

Multiplication is carried out by multiplying each digit step by step, followed by shifting and adding intermediate results. Division is performed by identifying suitable multiples of the divisor and subtracting them from the dividend progressively.

Finally, the computed results are presented through a display function.

---

## 4. Smart Library Project

### Title

**Smart Library Project**

### The Scenario

A university library system. Books are stored in a database using a **BST** for fast searching by ISBN. When a student borrows a book, it is added to their **Borrowing History** using a **Stack**, so they can see their most recent activity first.

### Goal

To understand how different data structures serve different access patterns:

- **LIFO** for borrowing history
- **Logarithmic search** for book searching

### Individual Tasks — 5 Members

1. **Catalogue Architect:** Build a BST to store book titles and authors indexed by ISBN.
2. **Borrowing History:** Implement a Stack to keep track of books checked out, with the most recent on top.
3. **Record Finder:** Implement a recursive Search function within the BST to find books by ISBN.
4. **ADT Designer:** Create the Interface for the Library System to ensure information hiding.
5. **Admin Logic:** Handle the borrowing and returning process by removing from the catalogue and pushing to the stack.

### Functional Console Interface Requirement

Students are required to deliver a **Functional Console Interface** that allows a user, such as a Librarian or Student, to interact with the system without modifying the source code.

The interface must provide a menu-driven experience including:

1. **Add Book:** Input ISBN, Title, and Author.
2. **Search Book:** Find details by ISBN with `O(log n)` efficiency.
3. **Borrow Book:** Move record to history stack.
4. **View History:** Display all borrowed books in LIFO order.
5. **Exit:** Terminate the program.

### Example Code Implementation

```java
import java.util.*;

// ==========================================
// THE INTERFACE & ENTITY
// ==========================================
interface LibraryADT {
    void addBook(int isbn, String title, String author);
    void borrowBook(int isbn);
    void viewLatestHistory();
    void searchBook(int isbn);
}

class Book {
    int isbn;
    String title, author;
    Book left, right;

    public Book(int isbn, String title, String author) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
    }
}

// ==========================================
// BST & STACK LOGIC
// ==========================================
class BookBST {
    private Book root;

    public void insert(int isbn, String t, String a) {
        root = ins(root, isbn, t, a);
    }

    private Book ins(Book r, int i, String t, String a) {
        if (r == null) return new Book(i, t, a);
        if (i < r.isbn) {
            r.left = ins(r.left, i, t, a);
        } else if (i > r.isbn) {
            r.right = ins(r.right, i, t, a);
        }
        return r;
    }

    public Book search(int i) {
        return sea(root, i);
    }

    private Book sea(Book r, int i) {
        if (r == null || r.isbn == i) return r;
        return (i < r.isbn) ? sea(r.left, i) : sea(r.right, i);
    }
}

class BorrowStack {
    private Stack<Book> stack = new Stack<>();

    public void push(Book b) {
        stack.push(b);
    }

    public void show() {
        if (stack.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            // Reverse iteration to show most recent first
            for (int i = stack.size() - 1; i >= 0; i--) {
                Book b = stack.get(i);
                System.out.println("[ISBN: " + b.isbn + "] " + b.title);
            }
        }
    }
}

// ==========================================
// REQUIREMENT A: FUNCTIONAL CONSOLE INTERFACE
// ==========================================
class SmartLibrary implements LibraryADT {
    private BookBST catalogue = new BookBST();
    private BorrowStack history = new BorrowStack();

    public void addBook(int i, String t, String a) {
        catalogue.insert(i, t, a);
    }

    public void searchBook(int i) {
        Book b = catalogue.search(i);
        if (b != null) {
            System.out.println("Found: " + b.title);
        } else {
            System.out.println("Not Found.");
        }
    }

    public void borrowBook(int i) {
        Book b = catalogue.search(i);
        if (b != null) {
            history.push(b);
            System.out.println("Borrowed " + b.title);
        } else {
            System.out.println("Book not in catalogue.");
        }
    }

    public void viewLatestHistory() {
        history.show();
    }

    public void runMenu() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Choice: ");
            int choice = sc.nextInt();
            if (choice == 5) break;
            handleChoice(choice, sc);
        }
        sc.close();
    }

    private void printMenu() {
        System.out.println("\n--- SmartLibrary Menu ---");
        System.out.println("1. Add Book");
        System.out.println("2. Search (BST)");
        System.out.println("3. Borrow (Stack)");
        System.out.println("4. History");
        System.out.println("5. Exit");
    }

    private void handleChoice(int choice, Scanner sc) {
        switch (choice) {
            case 1:
                System.out.print("Enter ISBN: ");
                int i = sc.nextInt();
                System.out.print("Enter Title: ");
                String t = sc.next();
                System.out.print("Enter Author: ");
                String a = sc.next();
                addBook(i, t, a);
                break;
            case 2:
                System.out.print("Enter ISBN to search: ");
                searchBook(sc.nextInt());
                break;
            case 3:
                System.out.print("Enter ISBN to borrow: ");
                borrowBook(sc.nextInt());
                break;
            case 4:
                viewLatestHistory();
                break;
            default:
                System.out.println("Invalid option.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        new SmartLibrary().runMenu();
    }
}
```

### Marking Rubric

| Criteria | Updated Focus | Weight |
|---|---|---:|
| Interface Functionality | The console menu runs without crashing and handles invalid inputs gracefully. | 20% |
| BST Search Logic | Recursive search correctly traverses the tree with `O(log n)` complexity. | 20% |
| Stack History | Borrowing correctly pushes to stack; history displays in LIFO order. | 20% |
| Information Hiding | The Library system uses the ADT Interface; internal structures are private. | 20% |
| Input Validation | System handles cases like non-integer ISBNs or empty search results. | 20% |

---

## 5. Smart Parking & Traffic Management System

### Title

**Smart Parking & Traffic Management System**

### Task Instruction

Based on the case study below, you are required to propose data structure techniques that allow quick retrieval and explain why they are efficient.

### Description

In the Smart Parking & Traffic Management System, the system must manage parking locations and vehicle records efficiently. You are required to design a module that stores and updates parking slot and vehicle information dynamically. The system should support adding, removing, and displaying records. Propose suitable data structures and justify your choice.

The system must also handle vehicles entering and exiting parking areas in real time. Vehicles should be processed in the order they arrive, but the system should also support reversing the most recent action in case of errors. Design a solution using appropriate data structures and explain how it works.

In addition, the system must assign parking slots based on priority, such as nearest available slot or shortest waiting time. You are required to design a mechanism that efficiently selects the best parking slot and explain how your approach improves performance compared to a simple search.

The system should also guide drivers to the nearest available parking location. You are required to represent the parking locations and routes, and implement a method to determine the shortest path. Briefly explain your approach and its efficiency.

Furthermore, the system should allow users to quickly search for vehicle records and parking information. Design a solution that enables efficient searching and sorting, and explain why it performs better than a basic list.

Finally, the system should provide fast access to frequently used data such as vehicle details and parking status.

### Marking Rubrics

| Component | Data Structure Technique Proposed | Criteria | Marks |
|---|---|---|---:|
| Parking & Vehicle Management | Array / Linked List | Correct data structure selection, dynamic data handling, add/delete/display, explanation with complexity. | 15 |
| Entry & Exit Processing | Queue + Stack | Correct FIFO processing and undo mechanism using LIFO, working logic and justification. | 15 |
| Parking Slot Assignment | Priority Queue / Min Heap | Efficient slot selection, correct prioritization, comparison with linear search. | 15 |
| Route Navigation | Graph + Dijkstra Algorithm | Correct graph modeling, shortest path implementation, explanation of efficiency. | 20 |
| Search System | BST / AVL Tree | Efficient searching and sorting, correct operations, explanation of `O(log n)`. | 15 |
| Fast Data Retrieval | Hash Table / HashMap | Efficient lookup with `O(1)`, correct key-value usage, explanation. | 10 |
| System Integration | All data structures combined | Modules are integrated and function as one system. | 5 |
| Presentation & Report | Diagrams & Explanation | Clear report, proper structure, relevant data structure diagrams. | 5 |
| **TOTAL** |  |  | **100** |

---

## 6. Smart Food Delivery & Order Management System

### Title

**Smart Food Delivery & Order Management System**

### Task Instruction

Based on the case study below, you are required to propose data structure techniques that allow quick retrieval and explain why they are efficient.

### Description

In a Smart Food Delivery & Order Management System, the company GoodTech needs a better way to manage users, restaurants, orders, and delivery services. As a team, you are required to design and implement a system using suitable data structures.

First, the system should be able to store and manage customer and restaurant information, allowing data to be added and removed easily. You need to choose appropriate data structures and explain why they are suitable.

Next, the system must process orders in real time, where orders are handled in the order they are received. At the same time, customers should be able to undo the last item added before confirming their order. You are required to design this feature using appropriate data structures and explain your choice.

In addition, the system should assign delivery riders based on priority, such as the shortest delivery time or nearest distance. You need to design a method to select the best rider efficiently and explain how your solution is better than a simple search method.

The system must also find the shortest route between the restaurant and the customer. You are required to represent the locations and implement an algorithm to determine the shortest path, including a brief explanation of how it works.

Furthermore, the system should allow users to quickly search for food items and view them in an organized way. You need to design a structure that supports fast searching and sorting, and explain its advantages.

Finally, the system should provide fast access to important data such as user profiles and order details.

### Marking Rubrics

| Component | Data Structure Technique Proposed | Criteria | Marks |
|---|---|---|---:|
| User & Restaurant Management | Array / Linked List | Correct data structure selection, implementation with add/delete/display, and explanation with complexity. | 15 |
| Order Processing System | Queue + Stack | Correct use of FIFO queue and LIFO stack, working order flow plus undo feature, justification. | 15 |
| Delivery Assignment | Priority Queue / Min Heap | Efficient rider selection, correct prioritization logic, performance comparison with linear search. | 15 |
| Route Optimization | Graph + Dijkstra Algorithm | Correct graph representation, shortest path implementation, explanation of algorithm and complexity. | 20 |
| Search & Recommendation | BST / AVL Tree | Correct tree usage, insert/search/traversal operations, efficiency explanation with `O(log n)`. | 15 |
| Data Retrieval Optimization | Hash Table / HashMap | Fast data access implementation, key-value usage, explanation of `O(1)` efficiency. | 10 |
| System Integration | All data structures combined | All modules work together logically and smoothly. | 5 |
| Presentation & Report | Diagrams — Stack, Queue, Graph, Tree | Clear explanation, structured report, relevant diagrams. | 5 |
| **TOTAL** |  |  | **100** |
