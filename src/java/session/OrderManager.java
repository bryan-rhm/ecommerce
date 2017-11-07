package session;

import cart.ShoppingCart;
import cart.ShoppingCartItem;
import entity.Cliente;
import entity.ClienteOrden;
import entity.Producto;
import entity.ProductoOrdenado;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Bryan
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class OrderManager {

    @PersistenceContext(unitName = "webtest2PU")
    private EntityManager em;
    @Resource
    private SessionContext context;
    @EJB
    private ProductoFacade productoFacade;
    @EJB
    private ClienteOrdenFacade clienteOrdenFacade;
    @EJB
    private ClienteFacade clienteFacade;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer placeOrder(String nombre, String direccion, String telefono, String correo, String creditcard, ShoppingCart cart) throws ClassNotFoundException, SQLException {
        try {
            Cliente cliente = agregarCliente(nombre, direccion, telefono, correo, creditcard);
            ClienteOrden orden = agregarOrden(cliente, cart);
            agregarProductosOrdenados(orden, cart);
            return orden.getIdclienteOrden();
        } catch (ClassNotFoundException | SQLException ex) {
            context.setRollbackOnly();
            return 0;

        }

    }

    private Cliente agregarCliente(String nombre, String direccion, String telefono, String correo, String creditcard) throws ClassNotFoundException, SQLException {
        Cliente cliente = new Cliente();
        cliente.setNombre(nombre);
        cliente.setDireccion(direccion);
        cliente.setTelefono(telefono);
        cliente.setCorreo(correo);
        cliente.setTarjetaCredito(creditcard);
        Random rand = new Random();
        em.persist(cliente);
        return cliente;
    }

    private ClienteOrden agregarOrden(Cliente cliente, ShoppingCart cart) throws ClassNotFoundException {
        em.flush();
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        ClienteOrden orden = new ClienteOrden();
        orden.setCliente(cliente.getIdclliente());
        orden.setFecha(date);
        orden.setCantidad(BigDecimal.valueOf(cart.getTotal()));
        em.persist(orden);
        return orden;
    }

    private void agregarProductosOrdenados(ClienteOrden orden, ShoppingCart cart) {
        //em.flush();
        List<ShoppingCartItem> items = cart.getItems();
        // Se recorre cada uno de los elementos del carrito de compras
        for (ShoppingCartItem item : items) {
            int id = item.getIdproducto();
            ProductoOrdenado productoOrdenado = new ProductoOrdenado();
            productoOrdenado.setClienteId(orden.getIdclienteOrden());
            productoOrdenado.setProductoId(id);
            productoOrdenado.setCantidad(item.getCantidad());
        }
    }
    public Map getOrderDetails(int orderId) throws ClassNotFoundException {

        Map orderMap = new HashMap();
        ProductoOrdenado producto = new ProductoOrdenado();

        // obtener la orden
        ClienteOrden orden = clienteOrdenFacade.find(orderId);
        // obtener datos del cliente
        Cliente cliente = clienteFacade.find(orden.getCliente());
        // obtener la lista de los productos
        List<ProductoOrdenado> productosOrdenados = producto.findByOrderId(orderId);
        // get product details for ordered items
        List<Producto> products = new ArrayList<Producto>();
        for (ProductoOrdenado op : productosOrdenados) {
            Producto p = (Producto) productoFacade.find(op.getIdproducto());
            products.add(p);
        }

        // add each item to orderMap
        orderMap.put("orderRecord", orden);
        orderMap.put("cliente", cliente);
        orderMap.put("productosOrdenados", productosOrdenados);
        orderMap.put("productos", products);
        return orderMap;
    }

}
