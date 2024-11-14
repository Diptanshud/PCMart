public class Product {
    private String components;
    private String brand;
    private String model;
    private String specs;
    private String price;
    private String stocks;
    private String dimensions;

    // Constructor
    public Product(String components, String brand, String model, String specs, String price, String stocks, String dimensions) {
        this.components = components;
        this.brand = brand;
        this.model = model;
        this.specs = specs;
        this.price = price;
        this.stocks = stocks;
        this.dimensions = dimensions;
    }

    // Getters and Setters
    public String getComponents() {
        return components;
    }

    public void setComponents(String components) {
        this.components = components;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getSpecs() {
        return specs;
    }

    public void setSpecs(String specs) {
        this.specs = specs;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getStocks() {
        return stocks;
    }

    public void setStocks(String stocks) {
        this.stocks = stocks;
    }

    public String getDimensions() {
        return dimensions;
    }

    public void setDimensions(String dimensions) {
        this.dimensions = dimensions;
    }

    @Override
    public String toString() {
        return "Product{" +
                "components='" + components + '\'' +
                ", brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                ", specs='" + specs + '\'' +
                ", price='" + price + '\'' +
                ", stocks='" + stocks + '\'' +
                ", dimensions='" + dimensions + '\'' +
                '}';
    }
}
