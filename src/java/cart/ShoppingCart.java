 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cart;

import entity.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ShoppingCart {
    List<ShoppingCartItem> items;
    int cantidad;
    double subtotal;
    double total;
    
    public ShoppingCart(){
        items = new ArrayList<ShoppingCartItem>();
        cantidad = 0;
        subtotal=0.0;
    }

    public synchronized  int getCantidad() {
        return cantidad;
    }

    public synchronized  double getTotal() {
        return total;
    }
    
    
    public synchronized void agregar(Producto producto){
        boolean nuevo = true;
        for (ShoppingCartItem csItem : items){
            if (csItem.getProducto().getIdproducto().intValue() == producto.getIdproducto().intValue()){
                nuevo = false;
                csItem.incrementarCantidad();                
            }

        }
        if (nuevo){
            ShoppingCartItem csItem = new ShoppingCartItem(producto);
             items.add(csItem);
        }
        cantidad++;
        subtotal += producto.getPrecio().doubleValue();
    }
    public synchronized void update(Producto producto, String cantidad){
           short qty = -1;
           qty = Short.parseShort(cantidad);
           if (qty>=0){
               ShoppingCartItem item = null;
               for (ShoppingCartItem scItem: items){
                   if (scItem.getProducto().getIdproducto().intValue()== producto.getIdproducto().intValue()){
                       this.cantidad+= (qty- scItem.getCantidad());
                       subtotal+= (qty- scItem.getCantidad()) * producto.getPrecio().doubleValue();
                       if (qty>0){
                            scItem.setCantidad(qty);
                       }else{
                           item=scItem;
                           break;
                       }
                   }
               }
               if (item != null){
                   items.remove(item);
               }
           }
    }
    public synchronized List<ShoppingCartItem> getItems(){
        return items;
    }
    public synchronized void getTotal(String cargo){
       total= subtotal + Double.parseDouble(cargo);
    }
    public synchronized double getsubtotal(){
        return subtotal;
    }
    public synchronized void clear(){
        items.clear();
        cantidad=0;
        subtotal=0.0;
        total = 0.0;
    }
    
}
