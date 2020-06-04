package co.com.ias.certification.backend.products.application.port.in;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;
import lombok.Value;


public interface CreateProductUseCase {
    ProductOperation createProduct(CreateProductCommand command);

    @Value(staticConstructor = "of")
    class CreateProductCommand {
        ProductOperationRequest product;
    }
}
