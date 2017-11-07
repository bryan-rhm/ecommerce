package controller;

import cart.ShoppingCart;
import entity.Categoria;
import entity.Producto;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import session.CategoriaFacade;
import session.OrderManager;
import session.ProductoFacade;

/**
 *
 * URL ACCEPTADOS EN NUESTRA PAGINA WEB
 */
@WebServlet(name = "ControllerServlet",
        loadOnStartup = 1,
        urlPatterns = {"/categoria",
            "/agregarCarrito",
            "/verCarrito",
            "/actualizarCarrito",
            "/checkout",
            "/comprar",
            "/vaciarCarrito"})
public class ControllerServlet extends HttpServlet {

    private String cargo;
    @EJB
    private CategoriaFacade categoriaFacade;
    @EJB
    private ProductoFacade productoFacade;
    @EJB
    private OrderManager orderManager;
    @Override
    public void init(ServletConfig servletConfig) throws ServletException {
        super.init(servletConfig);
        cargo = servletConfig.getServletContext().getInitParameter("cargoEnvio");
        getServletContext().setAttribute("categorias", categoriaFacade.findAll());

    }
    /**
     * URL LOS CUALES SE MANEJAN A TRAVEZ DEL METODO GET
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userPath = request.getServletPath();
        HttpSession session = request.getSession();
        Categoria selectedCategory;
        Collection<Producto> categoryProducts;
        if (userPath.equals("/categoria")) {
            userPath = "/category";
            String idcategoria = request.getQueryString();
            if (idcategoria != null) {
                selectedCategory = categoriaFacade.find(Integer.parseInt(idcategoria));
                session.setAttribute("selectedCategory", selectedCategory);
                categoryProducts = selectedCategory.getProductoCollection();
                session.setAttribute("categoryProducts", categoryProducts);
            }
        } else if (userPath.equals("/verCarrito")) {
            userPath = "/cart";
        } else if (userPath.equals("/checkout")) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            cart.getTotal(cargo);

        } else if (userPath.equals("/vaciarCarrito")) {
            ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
            cart.clear();
            userPath = "/cart";
        }
        String url = "/WEB-INF/views" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            ex.printStackTrace();
        }

    }

    /**
     * URL LOS CUALES SE MANEJAN A TRAVEZ DEL METODO POST
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userPath = request.getServletPath();
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (userPath.equals("/agregarCarrito")) {
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("cart", cart);
            }
            String productId = request.getParameter("productId");
            if (!productId.isEmpty()) {
                Producto producto = productoFacade.find(Integer.parseInt(productId));
                cart.agregar(producto);
            }
            userPath = "/category";
        } else if (userPath.equals("/actualizarCarrito")) {
            String productId = request.getParameter("idproducto");
            String cantidad = request.getParameter("cantidad");

            Producto producto = productoFacade.find(Integer.parseInt(productId));
            cart.update(producto, cantidad);

            userPath = "/cart";
        } else if (userPath.equals("/comprar")) {

            if (cart != null) {
                String nombre = request.getParameter("nombre");
                String direccion = request.getParameter("direccion");
                String telefono = request.getParameter("telefono");
                String correo = request.getParameter("correo");
                String creditcard = request.getParameter("creditcard");

                try {
                    int orderID = orderManager.placeOrder(nombre, direccion, telefono, correo, creditcard, cart);
                    if (orderID != 0) {
                        //Eliminamos el contenido del carrito de compras
                        cart = null;
                        //Terminamos la sesión
                        session.invalidate();
                        //Obtenemos los datos de la compra
                        Map orderMap = orderManager.getOrderDetails(orderID);
                        // Colocamos los datos como atributos globales
                        request.setAttribute("cliente", orderMap.get("cliente"));
                        request.setAttribute("productos", orderMap.get("productos"));
                        request.setAttribute("orderRecord", orderMap.get("orderRecord"));
                        request.setAttribute("productosOrdenados", orderMap.get("productosOrdenados"));

                    }
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(ControllerServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            userPath = "/confirmation";
        }
        String url = "/WEB-INF/views" + userPath + ".jsp";
        try {
            request.getRequestDispatcher(url).forward(request, response);
        } catch (IOException | ServletException ex) {
            System.out.println(ex);
        }
    }
}
