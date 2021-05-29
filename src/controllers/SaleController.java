package controllers;

import java.sql.*;
import java.util.ArrayList;
import models.Sale;

public class SaleController {
    private final String TABLE_NAME = "ventas", INV_TABLE = "inventario";
    
    public ArrayList<Sale> getAllSales(Connection cnx, String type) throws SQLException {
        ArrayList<Sale> ventas = new ArrayList();
        try {
            PreparedStatement consulta;
            consulta = !type.isEmpty() ? 
                    cnx.prepareStatement("SELECT " + this.INV_TABLE + ".*, " + this.TABLE_NAME + ".cantidad, " + this.TABLE_NAME + ".total, " + this.TABLE_NAME + ".fecha FROM " + 
                    this.TABLE_NAME + " INNER JOIN " + this.INV_TABLE + " on " + this.TABLE_NAME + ".id_item = " + this.INV_TABLE + ".id WHERE categoria = ?") : 
                    cnx.prepareStatement("SELECT " + this.INV_TABLE + ".*, " + this.TABLE_NAME + ".cantidad, " + this.TABLE_NAME + ".total, " + this.TABLE_NAME + ".fecha FROM " + 
                    this.TABLE_NAME + " INNER JOIN " + this.INV_TABLE + " on " + this.TABLE_NAME + ".id_item = " + this.INV_TABLE + ".id");
            if(!type.isEmpty()){
                consulta.setString(1, type);
            }
            
            ResultSet data = consulta.executeQuery();
            while(data.next()){
                ventas.add(new Sale(data.getTimestamp("fecha").toString(), data.getDouble("total"), data.getInt("cantidad"), data.getInt("id"), data.getString("nombre"), data.getString("categoria"), data.getFloat("precio"), data.getInt("stock")));
            }
        }catch(SQLException ex){
            System.out.println("Ha ocurrido un error al obtener los datos");
            System.out.println(ex.getMessage());
        }
        return ventas;
    }
    
    public int newSale(Connection cnx, int p_id, int no_items) throws SQLException {
        try {
            PreparedStatement query = cnx.prepareStatement("INSERT INTO " + this.TABLE_NAME + " VALUES(0,?,?,(SELECT precio FROM "+ this.INV_TABLE +" WHERE id = ?) * ?, default)");
            query.setInt(1, p_id);
            query.setInt(2, no_items);
            query.setInt(3, p_id);
            query.setInt(4, no_items);
            return query.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Ha ocurrido un error al ejecutar el query");
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
}
