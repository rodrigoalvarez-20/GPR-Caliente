package controllers;

import java.sql.*;
import java.util.ArrayList;
import models.Item;

public class ItemController {
    private final String TABLE_NAME = "inventario";
    
    public ArrayList<Item> getListItems(Connection cnx, String category) throws SQLException {
        ArrayList<Item> items = new ArrayList();
        try {
            PreparedStatement consulta;
            consulta = !category.isEmpty() ? cnx.prepareStatement("SELECT * FROM " + this.TABLE_NAME + " WHERE categoria = ?") : cnx.prepareStatement("SELECT * FROM "+ this.TABLE_NAME);
            if(!category.isEmpty()){
                consulta.setString(1, category);
            }
            
            ResultSet data = consulta.executeQuery();
            while(data.next()){
                items.add(new Item(data.getInt("id"), data.getString("nombre"), data.getString("categoria"), data.getDouble("precio"), data.getInt("stock")));
            }
        }catch(SQLException ex){
            System.out.println("Ha ocurrido un error al obtener los datos");
            System.out.println(ex.getMessage());
        }
        
        return items;
    }
    
    public int save(Connection cnx, Item item) throws SQLException {
        try {
            PreparedStatement query = null;
            if(item.getId() != 0){
                //Actualizacion
                query = cnx.prepareStatement("UPDATE " + this.TABLE_NAME + " SET nombre = ?, categoria = ?, precio = ?, stock = ? where id = ?");
                query.setString(1, item.getNombre());
                query.setString(2, item.getCategoria());
                query.setDouble(3, item.getPrecio());
                query.setInt(4, item.getStock());
                query.setInt(5, item.getId());
            }else {
                //Nuevo elemento
                query = cnx.prepareStatement("INSERT INTO " + this.TABLE_NAME + " VALUES (0,?,?,?,?)");
                query.setString(1, item.getNombre());
                query.setString(2, item.getCategoria());
                query.setDouble(3, item.getPrecio());
                query.setInt(4, item.getStock());
            }
            return query.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Ha ocurrido un error al ejecutar el query");
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
    public int delete(Connection cnx, Item item) throws SQLException {
        try {
            PreparedStatement query = cnx.prepareStatement("DELETE FROM " + this.TABLE_NAME + " WHERE id = ?");
            query.setInt(1, item.getId());
            return query.executeUpdate();
        }catch (SQLException ex){
            System.out.println("Ha ocurrido un error al ejecutar el query");
            System.out.println(ex.getMessage());
            return -1;
        }
    }
    
}
