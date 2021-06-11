
package Modelo;

public class MProducto {
    int id, sabor, presentacion, descuento, tamano, cantidad,des_descuento ;
    double precio;
    String des_sabor, des_presentacion, des_tamano;

    public MProducto() {
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSabor() {
        return sabor;
    }

    public void setSabor(int sabor) {
        this.sabor = sabor;
    }

    public int getPresentacion() {
        return presentacion;
    }

    public void setPresentacion(int presentacion) {
        this.presentacion = presentacion;
    }

    public int getDescuento() {
        return descuento;
    }

    public void setDescuento(int descuento) {
        this.descuento = descuento;
    }

    public int getTamano() {
        return tamano;
    }

    public void setTamano(int tamano) {
        this.tamano = tamano;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDes_sabor() {
        return des_sabor;
    }

    public void setDes_sabor(String des_sabor) {
        this.des_sabor = des_sabor;
    }

    public String getDes_presentacion() {
        return des_presentacion;
    }

    public void setDes_presentacion(String des_presentacion) {
        this.des_presentacion = des_presentacion;
    }

    public int getDes_descuento() {
        return des_descuento;
    }

    public void setDes_descuento(int des_descuento) {
        this.des_descuento = des_descuento;
    }

    

    public String getDes_tamano() {
        return des_tamano;
    }

    public void setDes_tamano(String des_tamano) {
        this.des_tamano = des_tamano;
    }
    
    
}
