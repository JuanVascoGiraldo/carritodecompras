
package Control;

import Modelo.MProducto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AccionesProducto {
    
    public static int agregarProducto(MProducto p){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "insert into mproducto (id_sabor, id_tamano, id_descuento, id_presentacion, cantidad_unidades, precio )"
                    + " values (?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setInt(1, p.getSabor());
            ps.setInt(2, p.getTamano());
            ps.setInt(3, p.getDescuento());
            ps.setInt(4, p.getPresentacion());
            ps.setInt(5, p.getCantidad() );
            ps.setDouble(6, p.getPrecio());
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
    
    public static int modificarProducto(MProducto p){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "update mproducto set id_sabor = ? , id_tamano = ? , id_descuento = ? , id_presentacion = ? , "
                    + " cantidad_unidades = ? , precio = ? where id_producto = ? ";
            ps = con.prepareStatement(q);
            ps.setInt(1, p.getSabor());
            ps.setInt(2, p.getTamano());
            ps.setInt(3, p.getDescuento());
            ps.setInt(4, p.getPresentacion());
            ps.setInt(5, p.getCantidad() );
            ps.setDouble(6, p.getPrecio());
            ps.setInt(7, p.getId());
            Estatus = ps.executeUpdate();
            
        }catch(Exception e){
            System.out.println("Error al Actualizar");
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
    
    public static MProducto buscarProducto(int id){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        MProducto p = new MProducto();
        try{
            con = Conexion.getConnection();
            String q= "select * from mproducto "
                    + "where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if(rs.next()){
                p.setId(id);
                p.setPresentacion(rs.getInt("id_presentacion"));
                p.setSabor(rs.getInt("id_sabor"));
                p.setDescuento(rs.getInt("id_descuento"));
                p.setTamano(rs.getInt("id_tamano"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad_unidades"));
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
        return p;
    }
    
    public static List<MProducto> buscarAllProducto(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MProducto> lista = new ArrayList<MProducto>();
        try{
            con = Conexion.getConnection();
            String q= "select csabor.tipo_sabor, cpresentacion.tipo_presentacion, cdescuento.porcentaje,"
                    + " ctamano.tipo_tamano, mproducto.cantidad_unidades, mproducto.precio, mproducto.id_producto from "
                    + "mproducto "
                    + "INNER JOIN csabor ON csabor.id_sabor = mproducto.id_sabor "
                    + "INNER JOIN cpresentacion ON cpresentacion.id_presentacion = mproducto.id_presentacion "
                    + "INNER JOIN cdescuento ON cdescuento.id_descuento = mproducto.id_descuento "
                    + "INNER JOIN ctamano ON ctamano.id_tamano = mproducto.id_tamano ";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                MProducto p = new MProducto();
                p.setId(rs.getInt("id_producto"));
                p.setDes_sabor(rs.getString("tipo_sabor"));
                p.setDes_presentacion(rs.getString("tipo_presentacion"));
                p.setDes_descuento(rs.getInt("porcentaje"));
                p.setDes_tamano(rs.getString("tipo_tamano"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad_unidades"));
                lista.add(p);
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
    
    public static int EliminarProducto(int id){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q= "delete from  dcompra where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            Estatus = ps.executeUpdate();
            q= "delete from  mproducto where id_producto = ?";
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
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return Estatus;
    }
    
    
    public static List<MProducto> buscarAllProductoconStock(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<MProducto> lista = new ArrayList<MProducto>();
        try{
            con = Conexion.getConnection();
            String q= "select * from mproducto "
                    + "INNER JOIN csabor ON csabor.id_sabor = mproducto.id_sabor "
                    + "INNER JOIN cpresentacion ON cpresentacion.id_presentacion = mproducto.id_presentacion "
                    + "INNER JOIN cdescuento ON cdescuento.id_descuento = mproducto.id_descuento "
                    + "INNER JOIN ctamano ON ctamano.id_tamano = mproducto.id_tamano ";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                MProducto p = new MProducto();
                p.setCantidad(rs.getInt("cantidad_unidades"));
                if(p.getCantidad() > 0){
                    p.setId(rs.getInt("id_producto"));
                    p.setDes_sabor(rs.getString("tipo_sabor"));
                    p.setDes_presentacion(rs.getString("tipo_presentacion"));
                    p.setDes_descuento(rs.getInt("porcentaje"));
                    p.setDes_tamano(rs.getString("tipo_tamano"));
                    p.setPrecio(rs.getDouble("precio"));
                    p.setPresentacion(rs.getInt("id_presentacion"));
                    p.setSabor(rs.getInt("id_sabor"));
                    p.setDescuento(rs.getInt("id_descuento"));
                    p.setTamano(rs.getInt("id_tamano"));
                    lista.add(p);
                }
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
    
    public static int actualizarStock(int id, int stock){
        int Estatus = 0;
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q= "select cantidad_unidades from mproducto "
                    + "where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            int stock1 = 0;
            if(rs.next()){
                stock1 = rs.getInt("cantidad_unidades");
            }
            stock1 -= stock;
            q = "update mproducto set cantidad_unidades = ? where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, stock1);
            ps.setInt(2, id);
            Estatus = ps.executeUpdate();
        }catch(Exception e){
            System.out.println("Error al Actualizar el stock");
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
    
    public static MProducto buscarallProducto(int id, Connection con){
        PreparedStatement ps = null;
        ResultSet rs = null;
        MProducto p = new MProducto();
        try{
            con = Conexion.getConnection();
            String q= "select csabor.tipo_sabor, cpresentacion.tipo_presentacion, cdescuento.porcentaje,"
                    + " ctamano.tipo_tamano, mproducto.cantidad_unidades, mproducto.precio, mproducto.id_producto from "
                    + "mproducto "
                    + "INNER JOIN csabor ON csabor.id_sabor = mproducto.id_sabor "
                    + "INNER JOIN cpresentacion ON cpresentacion.id_presentacion = mproducto.id_presentacion "
                    + "INNER JOIN cdescuento ON cdescuento.id_descuento = mproducto.id_descuento "
                    + "INNER JOIN ctamano ON ctamano.id_tamano = mproducto.id_tamano "
                    + "where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id_producto"));
                p.setDes_sabor(rs.getString("tipo_sabor"));
                p.setDes_presentacion(rs.getString("tipo_presentacion"));
                p.setDes_descuento(rs.getInt("porcentaje"));
                p.setDes_tamano(rs.getString("tipo_tamano"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad_unidades"));
            }
        }catch(Exception e){
            System.out.println("Error al Consultar");
            System.out.println(e.getMessage());
        }finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception error){
                System.out.println("Error a cerrar la conexion");
                System.out.println(error);
            }
        }
        return p;
    }
    
    public static MProducto buscarallProducto(int id ){
        PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = null;
        MProducto p = new MProducto();
        try{
            con = Conexion.getConnection();
            String q= "select csabor.tipo_sabor, cpresentacion.tipo_presentacion, cdescuento.porcentaje,"
                    + " ctamano.tipo_tamano, mproducto.cantidad_unidades, mproducto.precio, mproducto.id_producto from "
                    + "mproducto "
                    + "INNER JOIN csabor ON csabor.id_sabor = mproducto.id_sabor "
                    + "INNER JOIN cpresentacion ON cpresentacion.id_presentacion = mproducto.id_presentacion "
                    + "INNER JOIN cdescuento ON cdescuento.id_descuento = mproducto.id_descuento "
                    + "INNER JOIN ctamano ON ctamano.id_tamano = mproducto.id_tamano "
                    + "where id_producto = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                p.setId(rs.getInt("id_producto"));
                p.setDes_sabor(rs.getString("tipo_sabor"));
                p.setDes_presentacion(rs.getString("tipo_presentacion"));
                p.setDes_descuento(rs.getInt("porcentaje"));
                p.setDes_tamano(rs.getString("tipo_tamano"));
                p.setPrecio(rs.getDouble("precio"));
                p.setCantidad(rs.getInt("cantidad_unidades"));
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
        return p;
    }
    
}
