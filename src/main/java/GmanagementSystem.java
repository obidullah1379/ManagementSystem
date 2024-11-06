import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
class Garment {
    private String id;
    private String name;
    private String description;
    private String size;
    private String color;
    private double price;
    private int stockQuantity;
    public Garment(String id, String name, String description, String size, String color, double price, int stockQuantity) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.size = size;
        this.color = color;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }
    public void updateStock(int quantity) {
        this.stockQuantity = quantity;
    }
    public double calculateDiscountPrice(double discountPercentage) {
        return price - (price * (discountPercentage / 100));
    }
    public String getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public void displayDetails() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Description: " + description);
        System.out.println("Size: " + size);
        System.out.println("Color: " + color);
        System.out.println("Price: " + price);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("--------------------------");
    }
}
class Fabric {
    private String id;
    private String type;
    private String color;
    private double pricePerMeter;
    public Fabric(String id, String type, String color, double pricePerMeter) {
        this.id = id;
        this.type = type;
        this.color = color;
        this.pricePerMeter = pricePerMeter;
    }
    public double calculateCost(double meters) {
        return pricePerMeter * meters;
    }
    public String getType() { return type; }
}
class Supplier {
    private String id;
    private String name;
    private String contactInfo;
    private List<Fabric> suppliedFabric = new ArrayList<>();
    public Supplier(String id, String name, String contactInfo) {
        this.id = id;
        this.name = name;
        this.contactInfo = contactInfo;
    }
    public void addFabric(Fabric fabric) {
        suppliedFabric.add(fabric);
    }
    public List<Fabric> getSuppliedFabrics() {
        return suppliedFabric;
    }
    public void displaySupplierInfo() {
        System.out.println("Supplier ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Contact Info: " + contactInfo);
    }
}
class Order {
    private String orderId;
    private Date orderDate;
    private List<Garment> garments = new ArrayList<>();
    private double totalAmount;
    public Order(String orderId, Date orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
    }
    public void addGarment(Garment garment) {
        garments.add(garment);
    }
    public double calculateTotalAmount() {
        totalAmount = 0;
        for (Garment g : garments) {
            totalAmount += g.getPrice();
        }
        return totalAmount;
    }
    public void printOrderDetails() {
        System.out.println("Order ID: " + orderId);
        System.out.println("Order Date: " + orderDate);
        System.out.println("Garments in Order:");
        for (Garment g : garments) {
            System.out.println("- " + g.getName() + ": $" + g.getPrice());
        }
        System.out.println("Total Amount: $" + calculateTotalAmount());
        System.out.println("--------------------------");
    }
}
class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phone;
    public Customer(String customerId, String name, String email, String phone) {
        this.customerId = customerId;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }
    public void placeOrder(Order order) {
        System.out.println("Order placed by " + name);
        order.printOrderDetails();
    }
}
class Inventory {
    private List<Garment> garments = new ArrayList<>();
    public void addGarment(Garment garment) {
        garments.add(garment);
    }
    public void removeGarment(String id) {
        garments.removeIf(g -> g.getId().equals(id));
    }
    public Garment findGarment(String id) {
        for (Garment g : garments) {
            if (g.getId().equals(id)) {
                return g;
            }
        }
        return null;
    }
    public void displayInventory() {
        for (Garment g : garments) {
            g.displayDetails();
        }
    }
}
public class GmanagementSystem {
    public static void main(String[] args) {
        Inventory inventory = new Inventory();
        Scanner scanner = new Scanner(System.in);
        Garment g1 = new Garment("G001", "Silk Shirt", "High-quality silk shirt", "L", "Red", 1200, 20);
        Garment g2 = new Garment("G002", "Cotton T-shirt", "Comfortable cotton t-shirt", "M", "Blue", 500, 50);
        inventory.addGarment(g1);
        inventory.addGarment(g2);
        while (true) {
            System.out.println("\n--- Garment Management System Menu ---");
            System.out.println("1. View Inventory");
            System.out.println("2. Add Garment");
            System.out.println("3. Remove Garment");
            System.out.println("4. Find Garment by ID");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("\nInventory:");
                    inventory.displayInventory();
                    break;
                case 2:
                    System.out.print("Enter Garment ID: ");
                    String id = scanner.next();
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Description: ");
                    String description = scanner.next();
                    System.out.print("Enter Size: ");
                    String size = scanner.next();
                    System.out.print("Enter Color: ");
                    String color = scanner.next();
                    System.out.print("Enter Price: ");
                    double price = scanner.nextDouble();
                    System.out.print("Enter Stock Quantity: ");
                    int quantity = scanner.nextInt();
                    Garment newGarment = new Garment(id, name, description, size, color, price, quantity);
                    inventory.addGarment(newGarment);
                    System.out.println("Garment added to inventory.");
                    break;
                case 3:
                    System.out.print("Enter Garment ID to remove: ");
                    String removeId = scanner.next();
                    inventory.removeGarment(removeId);
                    System.out.println("Garment removed from inventory.");
                    break;
                case 4:
                    System.out.print("Enter Garment ID to search: ");
                    String searchId = scanner.next();
                    Garment foundGarment = inventory.findGarment(searchId);
                    if (foundGarment != null) {
                        foundGarment.displayDetails();
                    } else {
                        System.out.println("Garment not found.");
                    }
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}