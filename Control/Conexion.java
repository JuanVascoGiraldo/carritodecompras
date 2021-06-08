package Control;
import java.sql.*;
public class Conexion {
    public static Connection getConnection(){
        String url, userName, password;
        
        url = "jdbc:mysql://localhost/carritodecompras";
        userName = "root";
        password = "03042021";
        
        Connection con = null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            
            con = DriverManager.getConnection(url, userName, password);
            
            System.out.println("Conexion Exitosa con la BD");
        
        }catch(Exception e){
            System.out.println("Error al conectar la BD");
            System.out.println(e.getMessage());
        }
        return con;
    }
    
}
