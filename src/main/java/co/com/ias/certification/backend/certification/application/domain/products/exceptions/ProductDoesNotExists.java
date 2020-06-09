package co.com.ias.certification.backend.certification.application.domain.products.exceptions;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductDoesNotExists extends ProductException {
    Long id;

    public ProductDoesNotExists(Long id) {
        super(String.format("El producto con el id %s no existe", id));
        this.id = id;
    }
}
