package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

import java.util.ArrayList;

@Value(staticConstructor = "of")
public class OrderOperationRequest {
    Cliente cliente;
    Descuento descuento;
    Total total;
    Estado estado;

    public OrderOperationRequest(Cliente cliente, Descuento descuento, Total total, Estado estado) {
        Preconditions.checkNotNull(cliente);
        Preconditions.checkNotNull(descuento);
        Preconditions.checkNotNull(total);
        Preconditions.checkNotNull(estado);

        this.cliente = cliente;
        this.descuento = descuento;
        this.total = total;
        this.estado = estado;
    }
}
