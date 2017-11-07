/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;
import entity.Producto;

/**
 *
 * @author admin
 */
public class ShoppingCartItem {
    Producto producto;
    int cantidad;

    public ShoppingCartItem(Producto producto) {
        this.producto = producto;
        this.cantidad = 1;
    }
    
    public Producto getProducto() {
        return producto;
    }
    public int getIdproducto(){
        return producto.getIdproducto();
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public void incrementarCantidad(){
        cantidad++;
    }
    public void decrementarCantidad(){
        cantidad--;
    }
    public double getTotal(){
        double c =0;
        c=(this.getCantidad())*producto.getPrecio().doubleValue();
        return c;
    }
}
