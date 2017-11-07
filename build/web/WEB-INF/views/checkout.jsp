<div id="singleColumn">

    <h2>checkout</h2>

    <p>Para continuar con la compra, por favor proveenos esta información:</p>

    <form action="comprar" method="post">
        <table id="checkoutTable">
            <tr>
                <td><label for="name">Nombre:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="name"
                           name="nombre"
                           value="${param.name}">
                </td>
            </tr>
            <tr>
                <td><label for="email">Correo:</label></td>
                <td class="inputField">
                    <input type="email"
                           size="31"
                           maxlength="45"
                           id="email"
                           name="correo"
                           value="${param.email}">
                </td>
            </tr>
            <tr>
                <td><label for="phone">Teléfono:</label></td>
                <td class="inputField">
                    <input type="number"
                           size="31"
                           maxlength="8"
                           min="0"
                           id="phone"
                           name="telefono"
                           value="${param.phone}">
                </td>
            </tr>
            <tr>
                <td><label for="address">Dirección:</label></td>
                <td class="inputField">
                    <input type="text"
                           size="31"
                           maxlength="45"
                           id="address"
                           name="direccion"
                           value="${param.address}">

                    <br>
                   
                </td>
            </tr>
            <tr>
                <td><label for="creditcard">Tarjeta de credito:</label></td>
                <td class="inputField">
                    <input type="number"
                           size="31"
                           min="0"
                           maxlength="19"
                           id="creditcard"
                           name="creditcard"
                           value="${param.creditcard}">
                </td>
            </tr>
            <tr>
                <td colspan="2">
                    <c:if test="${cart.subtotal>0}"> <input type="submit" value="Comprar"></c:if>
                    
                </td>
            </tr>
        </table>
    </form>

    <div id="infoBox">

        <ul>
            <li>Se garantiza el envío al siguiente día </li>
            <li> Q ${initParam.cargoEnvio}
                 de cargo de envío se aplica a todas las compras</li>
        </ul>

        <table id="priceBox">
            <tr>
                <td>subtotal:</td>
                <td class="checkoutPriceColumn">
                    Q ${cart.subtotal}</td>
            </tr>
            <tr>
                <td>Cargo de envío:</td>
                <td class="checkoutPriceColumn">
                    Q ${initParam.cargoEnvio}</td>
            </tr>
            <tr>
                <td class="total">total:</td>
                <td class="total checkoutPriceColumn">
                    Q ${cart.total}</td>
            </tr>
        </table>
    </div>
</div>