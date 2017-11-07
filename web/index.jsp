<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : index
    Created on : Oct 4, 2017, 6:32:03 PM
    Author     : Bryan
--%>

<div id="indexLeftColumn">
    <div class="welcomeText">
        <p>Bienvenido</p>
        <p>Tu tienda online de preferencia, selecciona una categoría para empezar a comprar!</p>
    </div>
</div>
<div id="indexRightColumn">
    <c:forEach var="categoria" items="${categorias}">
    <div class="categoryBox">
        <a href="categoria?${categoria.idcategoria}">
            <span class="categoryLabelText">${categoria.nombre}</span>
            <img src="${initParam.categoriaImagePath}${categoria.nombre}.png" alt="${cateogria.nombre}">
        </a>
    </div>        
        
    </c:forEach>
    
</div>
