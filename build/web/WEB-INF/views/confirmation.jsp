
<div id="singleColumn">

    <p id="confirmationText">
        <strong>Tu orden ya fue procesada y será entregada en las siguientes 24 horas.</strong>
        <br><br>
        Guarda tu número de confirmación :
        <strong>${orderRecord.idclienteOrden}</strong>
        <br>
        Si tienes una consulta sientete libre de  <a href="#">contactarnos</a>.
        <br><br>
        Gracias por tu preferencia!!
    </p>

    <div class="summaryColumn" >

        <table id="orderSummaryTable" class="detailsTable">
            <tr class="header">
                <th colspan="3">Orden</th>
            </tr>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="2" id="deliverySurchargeCellLeft"><strong>Cargo de envío:</strong></td>
                <td id="deliverySurchargeCellRight">Q ${initParam.cargoEnvio}</td>
            </tr>

            <tr class="lightBlue">
                <td colspan="2" id="totalCellLeft"><strong>total:</strong></td>
                <td id="totalCellRight">Q ${orderRecord.cantidad}</td>
            </tr>

            <tr class="lightBlue"><td colspan="3" style="padding: 0 20px"><hr></td></tr>

            <tr class="lightBlue">
                <td colspan="3" id="dateProcessedRow"><strong>Fecha procesado:</strong>
                    ${orderRecord.fecha}
                </td>
            </tr>
        </table>

    </div>

    <div class="summaryColumn" >

        <table id="deliveryAddressTable" class="detailsTable">
            <tr class="header">
                <th colspan="3">Dirección Envío</th>
            </tr>

            <tr>
                <td colspan="3" class="lightBlue">
                    ${cliente.nombre}
                    <br>
                    ${cliente.direccion}
                    <br>
                    <hr>
                    <strong>Correo:</strong> ${cliente.correo}
                    <br>
                    <strong>Teléfono:</strong> ${cliente.telefono}
                </td>
            </tr>
        </table>

    </div>
</div>