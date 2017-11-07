/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class ProductoOrdenado {
    private int idorden;
    private int idproducto; 
    private int cantidad;
    public void setClienteId(int idclienteOrden) {
        idorden =idclienteOrden;
    }

    public void setProductoId(int id) {
        idproducto = id;
    }

    public void setCantidad(int cantidad) {
       this.cantidad = cantidad;
    }
    public int getIdproducto(){
        return idproducto;
    }
    @Override
    public void finalize() throws SQLException, ClassNotFoundException {
       Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce","root","abc123**")) {
            Statement statement = con.createStatement();
            statement.executeUpdate("INSERT INTO orden_tiene_producto VALUES ('"+idorden+"', '"+idproducto+"' , '"+cantidad+"')");
        }
     }

    public List<ProductoOrdenado> findByOrderId(int orderId) throws ClassNotFoundException {
        List<ProductoOrdenado> List = new ArrayList<ProductoOrdenado>();
              String Query = "select *  orden_tiene_productoby where idorden="+orderId;
            Class.forName("com.mysql.jdbc.Driver");
        try (Connection con = DriverManager.getConnection("jdbc:mysql://localhost/ecommerce","root","abc123**")) {
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(Query);
            while (rs.next()){
               ProductoOrdenado producto = new ProductoOrdenado();
               producto.setCantidad( rs.getInt("cantidad"));
               producto.setClienteId(orderId);
               producto.setProductoId(rs.getInt("idproducto"));
               List.add(producto);
            }
           
        }catch (Exception e){
            System.out.println(e);
        }  
        return List;
    }

}
