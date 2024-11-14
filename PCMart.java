import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PCMart {
    public final String DB_URL = "jdbc:mysql://localhost:3306/PCMart";
    public final String USERNAME = "root";
    public final String PASSWORD = "945920";
    Scanner sc = new Scanner(System.in);
    static Connection conn = null;

    public static void main(String[] args) {
        PCMart pcMart = new PCMart();
        ConnectionUtil connectionUtil = new ConnectionUtil();
        pcMart.conn = connectionUtil.getConn(pcMart.DB_URL, pcMart.USERNAME, pcMart.PASSWORD);
        pcMart.showMenu();

        while (true) {
            String answer = pcMart.getInput("Enter your choice: ");
            if (answer.equalsIgnoreCase("a")) {
                pcMart.connectionDetails();
            } else if (answer.equalsIgnoreCase("s")) {
                String componentToSearch = pcMart.getInput("Enter the Name of the Component you want to search: ");
                pcMart.searchContact(componentToSearch);  // Call search method
            } else if (answer.equalsIgnoreCase("q")) {
                System.out.println("Thanks for using PCMart.");
                break;
            }
        }
    }

    public void showMenu() {
        System.out.println("Enter 'a' for adding a product.");
        System.out.println("Enter 's' for searching a product.");
        System.out.println("Enter 'q' for quitting the app.");
    }

    public String getInput(String message) {
        System.out.println("Enter " + message);
        return sc.nextLine();
    }

    // Method to add a new product to the database
    public void connectionDetails() {
        String components = getInput("components");
        String brand = getInput("brand");
        String model = getInput("model");
        String specs = getInput("specs");
        String price = getInput("price");
        String stocks = getInput("stocks");
        String dimensions = getInput("dimensions");

        insertData(conn, components, brand, model, specs, price, stocks, dimensions);
    }

    // Insert data into the Products table
    public void insertData(Connection conn, String components, String brand, String model, String specs, String price, String stocks, String dimensions) {
        String sql = "INSERT INTO Products (components, brand, model, specs, price, stocks, dimensions) VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, components);
            pst.setString(2, brand);
            pst.setString(3, model);
            pst.setString(4, specs);
            pst.setString(5, price);
            pst.setString(6, stocks);
            pst.setString(7, dimensions);
            System.err.println("Insertion successful");
            pst.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Insertion unsuccessful");
            e.printStackTrace();
        }
    }
    // Search for products by components
    public void searchContact(String scomponents) {
        System.out.println("Searching for products with component: " + scomponents);
        System.out.println("-----------------------------");

        String searchQuery = "SELECT * FROM Products WHERE components LIKE ?";
        List<Product> products = new ArrayList<>();

        try (PreparedStatement pst = conn.prepareStatement(searchQuery)) {
            pst.setString(1, "%" + scomponents + "%"); // Use LIKE to search for partial matches
            ResultSet results = pst.executeQuery();

            while (results.next()) {
                String component = results.getString("components");
                String brand = results.getString("brand");
                String model = results.getString("model");
                String specs = results.getString("specs");
                String price = results.getString("price");
                String stocks = results.getString("stocks");
                String dimensions = results.getString("dimensions");


                Product product = new Product(component, brand, model, specs, price, stocks, dimensions);
                products.add(product);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }


        if (products.isEmpty()) {
            System.out.println("No products found matching: " + scomponents);
        }
        else {
            System.out.println("Products matching '" + scomponents + "':");
            for (int i = 0; i < products.size(); i++) {
                Product product = products.get(i);  // Corrected here
                // Here you can simply check if the component contains the search term without any complex logic
                if (product.getComponents().toLowerCase().contains(scomponents.toLowerCase())) {
                    System.out.println("-------------------------------------------------");
                    System.out.println("Product " + (i + 1) + ": " + product); // Print each product
                    System.out.println("-------------------------------------------------");
                }
            }
        }
    }
}
