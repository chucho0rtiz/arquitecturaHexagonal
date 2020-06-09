package co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderDoesNotExists extends OrderException {
    Long id;

    public OrderDoesNotExists(Long id) {
        super(String.format("La Orden con el id %s no existe", id));
        this.id = id;
    }
}
