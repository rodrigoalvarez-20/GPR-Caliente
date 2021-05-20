package utils;

import java.sql.*;

public class MySQLConn {

    private static Connection cnx = null;

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://gpr-caliente.cynjhpjrvukf.us-east-2.rds.amazonaws.com/gprcaliente?characterEncoding=utf8", "admin", "hmskWRJ36sNpflr3");
            } catch (Exception ex){
                System.out.println("Ha ocurrido un error");
                System.out.println(ex.getMessage());
            }
        }
        return cnx;
    }
    
    public static void close() throws SQLException{
        if(cnx != null){
            cnx.close();
        }
    }

}
