
package Control;

import Modelo.MTarjeta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class AccionesTarjeta {
    
    public static int AgregarTarjeta(MTarjeta t){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "insert into mtarjeta (num_tarjeta, fecha_exp, codigo, id_tipo, id_usu)"
                    + " values (?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, t.getNumero());
            ps.setString(2, t.getFecha());
            ps.setInt(3, t.getCodigo());
            ps.setInt(4, t.getTipo());
            ps.setInt(5, t.getUsuario());
            Estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al Guardar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return Estatus;
    }
    public static int ModificarTarjeta(MTarjeta t){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "update mtarjeta set num_tarjeta = ? , fecha_exp = ? , codigo = ? , id_tipo = ? "
                    + " where id_tarjeta = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, t.getNumero());
            ps.setString(2, t.getFecha());
            ps.setInt(3, t.getCodigo());
            ps.setInt(4, t.getTipo());
            ps.setInt(5,t.getId());
            Estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al Modificar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return Estatus;
    }
    
    public static MTarjeta buscarTargetabyID( int id){
        MTarjeta t = new MTarjeta ();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from mtarjeta where id_tarjeta = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                t.setId(id);
                t.setCodigo(rs.getInt("codigo"));
                t.setFecha(rs.getString("fecha_exp"));
                t.setNumero(rs.getInt("num_tarjeta"));
                t.setTipo(rs.getInt("id_tipo"));
            }
        }catch(Exception e){
            System.out.println("Error al Buscar");
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
        return t;
    }
    
    public static List<MTarjeta> buscartarjetasusuario(int id){
        List<MTarjeta> lista = new ArrayList<MTarjeta>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select * from mtarjeta "
                    + "INNER JOIN TipoTarjeta ON TipoTarjeta.id_tipo = mtarjeta.id_tipo "
                    + "where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                MTarjeta t = new MTarjeta ();
                t.setId(rs.getInt("id_tarjeta"));
                t.setCodigo(rs.getInt("codigo"));
                t.setFecha(rs.getString("fecha_exp"));
                t.setNumero(rs.getInt("num_tarjeta"));
                t.setTipo_des(rs.getString("tipo"));
                lista.add(t);
            }
        }catch(Exception e){
            System.out.println("Error al Consultar");
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
    
    public static int eliminarTarjeta(int id){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "delete from mtarjeta where id_tarjeta = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            Estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al Borrar");
            System.out.println(e.getMessage());
        }finally{
            try{
                con.close();
                ps.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return Estatus;
    }
}
