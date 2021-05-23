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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    
    
    
}
