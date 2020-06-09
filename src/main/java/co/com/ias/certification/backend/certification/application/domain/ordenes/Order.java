package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class Order {
    Long id;
    Cliente cliente;
    Descuento descuento;
    Total total;
    Estado estado;

    public Order(Long id, Cliente cliente, Descuento descuento, Total total, Estado estado) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(cliente);
        Preconditions.checkNotNull(descuento);
        Preconditions.checkNotNull(total);
        Preconditions.checkNotNull(estado);

        this.id = id;
        this.cliente = cliente;
        this.descuento = descuento;
        this.total = total;
        this.estado = estado;
    }
}
