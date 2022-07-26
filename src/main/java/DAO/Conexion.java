
package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
   
    protected final String URL = "jdbc:mysql://localhost:3306/Base_Caso";
    protected final String USER="root";
    protected Connection conexión;
    
    public void conectar() throws ClassNotFoundException,SQLException{
        
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.conexión=DriverManager.getConnection(URL,USER,"dolape2014");
    }
}
