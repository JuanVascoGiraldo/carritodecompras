package Control;
import java.sql.*;
public class Conexion {
    public static Connection getConnection(){
        String url, userName, password;
        
        url = "jdbc:mysql://us-cdbr-east-04.cleardb.com/heroku_0524f9bc974df67";
        userName = "b5d37e78ee502c";
        password = "664b8b2e";
        //mysql://b5d37e78ee502c:664b8b2e@us-cdbr-east-04.cleardb.com/heroku_0524f9bc974df67?
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
