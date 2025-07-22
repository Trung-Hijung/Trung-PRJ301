/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class UserDTO {
    private int id;
    private String username;
    private String password;
    private String name;
    private String role;

    public UserDTO() { }

    // Dùng khi lấy từ DB
    public UserDTO(int id, String username, String password, String name, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = role != null ? role : "customer";
    }

    // Dùng khi đăng ký (mặc định là customer)
    public UserDTO(String username, String password, String name) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.role = "customer";
    }

    // Getters/Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getRole() { return role; }
    public void setRole(String role) { this.role = role; }
}
