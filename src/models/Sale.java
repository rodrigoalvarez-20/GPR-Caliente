package models;

public class Sale extends Item {
    private int cantidad = 0;
    private double total = 0.0;
    private String fecha;

    public Sale(String fecha, double total, int cantidad, int id, String nombre, String categoria, float precio, int stock) {
        super(id, nombre, categoria, precio, stock);
        this.fecha = fecha;
        this.cantidad = cantidad;
        this.total = total;
    }
    
    
    
    
}
