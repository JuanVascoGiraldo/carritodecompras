
package Modelo;

public class DCompra {
    
    int id, cantidad, compra;
    double total;
    MProducto productos;

    public DCompra() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getCompra() {
        return compra;
    }

    public void setCompra(int compra) {
        this.compra = compra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public MProducto getProductos() {
        return productos;
    }

    public void setProductos(MProducto productos) {
        this.productos = productos;
    }
    
    
    
}
