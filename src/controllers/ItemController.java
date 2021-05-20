package controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Item;

public class ItemController {
    private final String TABLE_NAME = "inventario";
    
    
    public ArrayList<Item> getListItems(Connection cnx, String category) throws SQLException {
        ArrayList<Item> items = new ArrayList();
        try {
            PreparedStatement consulta;
            consulta = !category.isBlank() ? cnx.prepareStatement("SELECT + FROM " + this.TABLE_NAME + " WHERE categoria = ?") : cnx.prepareStatement("SELECT * FROM "+ this.TABLE_NAME);
            if(!category.isBlank()){
                consulta.setString(0, category);
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
    
}
