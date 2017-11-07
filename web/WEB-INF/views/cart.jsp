</br>
</br>
</br>
<div id="centerColumn">

    <c:choose>
        <c:when test="${cart.cantidad > 1}">
            <p>Tu carrito de compras contiene ${cart.cantidad} productos.</p>
        </c:when>
        <c:when test="${cart.cantidad == 1}">
            <p>Tu carrito de compras contiene 1 producto.</p>
        </c:when>
        <c:otherwise>
            <p>Tu carrito de compras esta vacío.</p>
        </c:otherwise>
    </c:choose>

    <div id="actionBar">
        <a href="vaciarCarrito" class="bubble hMargin">Limpiar Carrito</a>
        <a href="categoria" class="bubble hMargin">Continuar comprando</a>
        <a href="checkout" class="bubble hMargin">proceder a chequear</a>

        <c:if test="${!empty cart && cart.cantidad != 0}">

            <h4 id="subtotal">subtotal: Q ${cart.subtotal}</h4>

            <table id="cartTable">

                <tr class="header">
                    <th>producto</th>
                    <th>nombre</th>
                    <th>precio</th>
                    <th>cantidad</th>
                </tr>

                <c:forEach var="cartItem" items="${cart.items}" varStatus="iter">

                    <c:set var="product" value="${cartItem.producto}"/>

                    <tr class="${((iter.index % 2) == 0) ? 'lightBlue' : 'white'}">
                        <td>
                            <img src="${initParam.productoImagePath}${product.nombre}.png"
                                 alt="${product.nombre}">
                        </td>

                        <td>${product.nombre}</td>

                        <td>
                            Q ${cartItem.total}
                            <br>
                            <span class="smallText">( Q ${product.precio} / unidad)</span>
                        </td>
                        <td>
                            <form action="<c:url value='actualizarCarrito'/>" method="post">
                                <input type="hidden"
                                       name="idproducto"
                                       value="${product.idproducto}">
                                <input type="text"
                                       maxlength="2"
                                       size="2"
                                       value="${cartItem.cantidad}"
                                       name="cantidad"
                                       style="margin:5px">
                                <input type="submit"
                                       name="submit"
                                       value="Actualizar">
                            </form>
                        </td>
                    </tr>

                </c:forEach>

            </table>

        </c:if>


    </div>