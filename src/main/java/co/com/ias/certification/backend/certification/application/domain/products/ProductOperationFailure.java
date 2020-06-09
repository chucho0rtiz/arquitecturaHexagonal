package co.com.ias.certification.backend.certification.application.domain.products;

import co.com.ias.certification.backend.certification.application.domain.products.exceptions.ProductException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationFailure implements ProductOperation {
    ProductException exception;

    @Override
    public Product value() {
        return null;
    }

    @Override
    public String errorMessage() {
        String message = String.format("Ha ocurrido un error: %s", exception.getMessage());
        return message;
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
