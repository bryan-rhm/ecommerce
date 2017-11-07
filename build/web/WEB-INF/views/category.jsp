<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<div id="categoryLeftColumn">
    <c:forEach var="categoria" items="${categorias}">
        <c:choose>
            <c:when test="${categoria.idcategoria ==pageContext.request.queryString}">
                <div class="categoryButton" id="selectedCategory">
                    <span class="categoryText">${categoria.nombre}</span>
                </div>
            </c:when>
            <c:otherwise>
                <a href="categoria?${categoria.idcategoria}" class="categoryButton">
                    <div class="categoryText">${categoria.nombre}</div>
                </a> 
            </c:otherwise>
        </c:choose>

    </c:forEach>

</div>

<div id="categoryRightColumn">
    <p id="categoryTitle">${selectedCategory.nombre}</p>

    <table id="productTable">
        <c:forEach var="producto" items="${categoryProducts}" varStatus="iter">
            <tr class="${((iter.index %2)==0)? 'lightBlue':'white'}">
                <td>
                    <img src="${initParam.productoImagePath}${producto.nombre}.png" alt="${producto.nombre}">
                </td>
                <td >
                    ${producto.nombre}
                    <br>
                    <span class="smallText">${producto.descripcion}</span>
                </td>
                <td >${producto.precio}</td>
                <td>
                    <form action="agregarCarrito" method="post">
                        <input type ="hidden" name="productId" value="${producto.idproducto}">
                        <input type="submit" value="Agregar al carrito">
                    </form>
                </td>
            </tr>
        </c:forEach>

       
    </table>
</div>