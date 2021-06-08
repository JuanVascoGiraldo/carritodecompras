package Control;

import Modelo.Catalogo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccionesCatalogo {
    
    public static List<Catalogo> Tamano(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from ctamano";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_tamano"));
                c.setDescripcion(rs.getString("tipo_tamano"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        
        return lista;
    }
    
    public static List<Catalogo> Descuento(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from cdescuento";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_descuento"));
                c.setPorcentaje(rs.getInt("porcentaje"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return lista;
    }
    
    public static List<Catalogo> Presentacion(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from cpresentacion";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_presentacion"));
                c.setDescripcion(rs.getString("tipo_presentacion"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return lista;
    }
    
    public static List<Catalogo> Pago(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from cformapago";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_forma"));
                c.setDescripcion(rs.getString("forma_pago"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return lista;
    }
    
    public static List<Catalogo> Tarjeta(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from tipotarjeta";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_tipo"));
                c.setDescripcion(rs.getString("tipo"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return lista;
    }
    
    public static List<Catalogo> Sabores(){
        List<Catalogo> lista = new ArrayList<Catalogo>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from CSabor";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                Catalogo c = new Catalogo();
                c.setId(rs.getInt("id_sabor"));
                c.setDescripcion(rs.getString("tipo_sabor"));
                lista.add(c);
            }
        }catch(Exception e){
            System.out.println("Error al consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return lista;
    }
    
    
    
}
