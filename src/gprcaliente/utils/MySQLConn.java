package gprcaliente.utils;

import java.sql.*;

public class MySQLConn {

    private static Connection cnx = null;

    public static Connection getConn() throws SQLException, ClassNotFoundException {
        if (cnx == null) {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                cnx = DriverManager.getConnection("jdbc:mysql://gpr-caliente.cynjhpjrvukf.us-east-2.rds.amazonaws.com/gprcaliente", "admin", "hmskWRJ36sNpflr3");
            } catch (SQLException sqlEx) {

            } catch (ClassNotFoundException cnfEx) {

            }
        }
    }

}
