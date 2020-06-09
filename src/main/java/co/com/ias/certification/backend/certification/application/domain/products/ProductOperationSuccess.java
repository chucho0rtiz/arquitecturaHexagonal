package co.com.ias.certification.backend.certification.application.domain.products;

import lombok.Value;

@Value(staticConstructor = "of")
public class ProductOperationSuccess implements ProductOperation {
    Product product;

    @Override
    public Product value() {
        return product;
    }

    @Override
    public String errorMessage() {
        String message = "El product se registro exitosamente!!";
        return message;
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
