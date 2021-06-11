
package Control;

import Modelo.MUsuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccionesUsuario {
    
    public static boolean yagregado(String email, Connection con, int id){
        boolean confirmar = true;
        ResultSet rs= null;
        PreparedStatement ps = null;
        try{
            String p = "select email_usu as correo, id_usu as usuario from musuario";
            ps = con.prepareStatement(p);
            rs = ps.executeQuery();
            while(rs.next()){
                if(email.equals(rs.getString("correo")) && id != rs.getInt("usuario") && id!=0){
                    confirmar= false;
                    break;
                }
                if(email.equals(rs.getString("correo")) && id == 0){
                    confirmar= false;
                    break;
                }
            }
        }
        catch(Exception e){
            System.out.println("error al comprobar");
            System.out.println(e.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();}
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return confirmar;
    }
    
    public static int AgregarUsuario(MUsuario u){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            boolean estatica = yagregado(u.getEmail(), con, 0);
            if(estatica == true){
                String q= "insert into musuario (nom_usu, appat_usu, apmat_usu, fecnac_usu, tel_usu, cel_usu, email_usu, pass_usu, rol )"
                        + " values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
                ps = con.prepareStatement(q);
                ps.setString(1, u.getNombre() );
                ps.setString(2, u.getAppat() );
                ps.setString(3, u.getApmat() );
                ps.setString(4, u.getFecha() );
                ps.setLong(5, u.getTelefono());
                ps.setLong(6, u.getCelular());
                ps.setString(7, u.getEmail() );
                ps.setString(8, u.getPassword() );
                ps.setInt(9, u.getRol());
                Estatus = ps.executeUpdate();
            }
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
    
    public static int ModificarUsuario(MUsuario u){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            boolean estatica = yagregado(u.getEmail(), con, u.getId());
            if(estatica == true){
                String q= "update musuario set nom_usu = ?, appat_usu = ?, apmat_usu = ?, fecnac_usu = ?, "
                        + "tel_usu = ? , cel_usu = ? , email_usu = ?, pass_usu = ?  where id_usu = ?";
                ps = con.prepareStatement(q);
                ps.setString(1, u.getNombre() );
                ps.setString(2, u.getAppat() );
                ps.setString(3, u.getApmat() );
                ps.setString(4, u.getFecha() );
                ps.setLong(5, u.getTelefono());
                ps.setLong(6, u.getCelular());
                ps.setString(7, u.getEmail() );
                ps.setString(8, u.getPassword() );
                ps.setInt(9, u.getId());
                Estatus = ps.executeUpdate();
            }
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
    
    public static MUsuario ConsultarUsuario(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MUsuario u = new MUsuario();
        try{
            con = Conexion.getConnection();
            String q= "select * from musuario where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                u.setId(id);
                u.setNombre(rs.getString("nom_usu"));
                u.setAppat(rs.getString("appat_usu"));
                u.setApmat(rs.getString("apmat_usu"));
                u.setFecha(rs.getString("fecnac_usu"));
                u.setCelular(rs.getLong("cel_usu"));
                u.setTelefono(rs.getLong("tel_usu"));
                u.setEmail(rs.getString("email_usu"));
                u.setPassword(rs.getString("pass_usu"));
                u.setRol(rs.getInt("rol"));
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
        return u;
    }
    
    public static int eliminarUsuario(int id){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select id_compra from mcompra where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()){
                String p = "delete from dcompra where id_compra = ?";
                ps = con.prepareStatement(p);
                ps.setInt(1, rs.getInt("id_compra"));
                Estatus = ps.executeUpdate();
            }
            q = "delete from mcompra where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            Estatus = ps.executeUpdate();
            q = "delete from mtarjeta where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            Estatus = ps.executeUpdate();
            q = "delete from musuario where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            Estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al Eliminar");
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
        return Estatus;
    }
    
    public static List<Integer> iniciarsesion(String correo, String contrasena){
        List<Integer> respuesta = new ArrayList<Integer>();
        int permiso = 1;
        int id = 0;
        int confirmar=0;
        Connection con = null;
        ResultSet rs= null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q = "select pass_usu, id_usu, rol from musuario where email_usu = ?";
            ps = con.prepareStatement(q);
            ps.setString(1, correo);
            rs = ps.executeQuery();
            while(rs.next()){
                if(contrasena.equals(rs.getString("pass_usu"))){
                    confirmar = 1;
                    id = rs.getInt("id_usu");
                    permiso = rs.getInt("rol");
                    break;
                }
            }
            respuesta.add(confirmar);
            respuesta.add(permiso);
            respuesta.add(id);
        }catch(Exception e){
            System.out.println("No se ha podido verificar al usuario");
            System.out.println(e);
        }finally{
            try{
            con.close();
            ps.close();
            rs.close();}
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return respuesta;
    }
}
