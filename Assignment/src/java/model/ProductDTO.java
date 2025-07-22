/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private double price;
    private int stock;
    private String image;
    private int categoryId;

    public ProductDTO(int id, String name, String description, double price, int stock, String image, int categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.categoryId = categoryId;
    }

    public ProductDTO(int id, String name, String description, double price, int stock, int categoryId) {
        this(id, name, description, price, stock, null, categoryId);
    }

    public ProductDTO(String name, String description, double price, int stock, int categoryId) {
        this(0, name, description, price, stock, null, categoryId);
    }

    // Getters & Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getPrice() { return price; }
    public int getStock() { return stock; }
    public String getImage() { return image; }
    public int getCategoryId() { return categoryId; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setDescription(String description) { this.description = description; }
    public void setPrice(double price) { this.price = price; }
    public void setStock(int stock) { this.stock = stock; }
    public void setImage(String image) { this.image = image; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
}
