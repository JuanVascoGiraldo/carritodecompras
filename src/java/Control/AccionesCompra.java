
package Control;
    import Modelo.DCompra;
    import Modelo.MCompra;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.util.ArrayList;
    import java.util.List;
public class AccionesCompra {
    
    public static boolean RegistrarCompra(MCompra mc, List<DCompra> lista){
        boolean Estatus = false;
        Connection con = null;
        PreparedStatement ps = null;
        try{
            con = Conexion.getConnection();
            String q = "insert into MCompra (fecha_compra, total_compra, id_forma, id_usu) "
                    + "values ( ?, ?, ?, ?)";
            ps = con.prepareStatement(q);
            ps.setString(1, mc.getFecha());
            ps.setDouble(2, mc.getTotal());
            ps.setInt(3, mc.getForma());
            ps.setInt(4, mc.getUsuario());
           if( ps.executeUpdate()>0 ){
               Estatus = true;
               int codigo = ultimoCodigoInsertoCompra(con);
               Estatus = registrarDetalleCompra(codigo, lista, con);
           }
            
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
    
    private static int ultimoCodigoInsertoCompra(Connection con){
        int codigo = 0;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            String q = "select max(id_compra) as Codigo from MCompra";
            ps = con.prepareStatement(q);
            rs = ps.executeQuery();
            while(rs.next()){
                codigo = rs.getInt("Codigo");
                break;
            }
        
        }catch(Exception e){
            System.out.println("Error al consultar la ultima compra");
            System.out.println(e.getMessage());
        
        }finally{
            try{
                rs.close();
                ps.close();
            
            }catch(Exception e){
                System.out.println("Error al conectar ");
                System.out.println(e.getMessage());
            }
        }
        return codigo;
    }
    
    private static boolean registrarDetalleCompra(int codigo, List<DCompra> lista, Connection con) {
        boolean registro = false;
        PreparedStatement ps = null;
        try{
            for(DCompra dc : lista){
                String q = "insert into DCompra (id_compra, id_producto, cantidad_dcompra, subtotal_dcompra) values(?,?,?,?)";
                ps = con.prepareStatement(q);
                ps.setInt(1, codigo);
                ps.setInt(2, dc.getProductos().getId());
                ps.setInt(3, dc.getCantidad());
                ps.setDouble(4, dc.getTotal());
                if(ps.executeUpdate() == 1){
                    registro = true;
                }else{
                    registro = false;
                    break;
                }
            }
        }catch(Exception sq){
            System.out.println("Error al registrar el dcompra");
            System.out.println(sq.getMessage());
            registro = false;
        }finally{
            try{
                ps.close();
            }catch(Exception e){
                System.out.println("Error al conectar");
                System.out.println(e.getMessage());
            }
        }
        return registro;
    }
    
    public static List<MCompra> consultarcomprasUsu(int id){
        List<MCompra> lista = new ArrayList<MCompra>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q = "select * from mcompra "
                    + "INNER JOIN CFormaPago ON CFormaPago.id_forma =  mcompra.id_forma "
                    + "where id_usu = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                MCompra mc = new MCompra();
                mc.setId(rs.getInt("id_compra"));
                mc.setFecha(rs.getString("fecha_compra"));
                mc.setTotal(rs.getDouble("total_compra"));
                mc.setForma_des(rs.getString("forma_pago"));
                lista.add(mc);
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
        return lista;
    }
    
    public static List<DCompra> consultardetallecompra(int id){
        List<DCompra> lista = new ArrayList<DCompra>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q = "select * from DCompra "
                    + "where id_compra = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                DCompra c = new DCompra();
                c.setId(rs.getInt("id_dcompra"));
                c.setProductos(AccionesProducto.buscarallProducto(rs.getInt("id_producto"), con));
                c.setCantidad(rs.getInt("cantidad_dcompra"));
                c.setTotal(rs.getDouble("subtotal_dcompra"));
                lista.add(c);
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
        return lista;
    }
    
    public static MCompra consultarcomprasUsu(int id, int compra){
        MCompra mc = new MCompra();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try{
            con = Conexion.getConnection();
            String q = "select * from mcompra "
                    + "INNER JOIN CFormaPago ON CFormaPago.id_forma =  mcompra.id_forma "
                    + "where id_usu = ? and id_compra = ?";
            ps = con.prepareStatement(q);
            ps.setInt(1, id);
            ps.setInt(2, compra);
            rs = ps.executeQuery();
            while(rs.next()){
                mc.setId(rs.getInt("id_compra"));
                mc.setFecha(rs.getString("fecha_compra"));
                mc.setTotal(rs.getDouble("total_compra"));
                mc.setForma_des(rs.getString("forma_pago"));
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
        return mc;
    }
    
}
