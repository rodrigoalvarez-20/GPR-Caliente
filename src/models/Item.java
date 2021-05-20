package models;

public class Item {
    private final int id;
    private String nombre = "", categoria = "";
    private double precio = 0.0;
    private int stock = 0;

    public Item(int id, String nombre, String categoria, double precio, int stock) {
        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Item() {
        this.id = 0;
    }
    
    
    
}
