package co.com.ias.certification.backend.certification.application.domain.productList.exceptions;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductListDoesNotExists extends ProductListException {
    Long id;

    public ProductListDoesNotExists(Long id) {
        super(String.format("No hay productos pertenecientes a la orden con id %s ", id));
        this.id = id;
    }
}
