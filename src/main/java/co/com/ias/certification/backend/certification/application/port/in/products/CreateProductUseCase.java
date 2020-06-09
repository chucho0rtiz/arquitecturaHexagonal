package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import lombok.Value;

public interface CreateProductUseCase {
    ProductOperation createProduct(CreateProductCommand command);

    @Value(staticConstructor = "of")
    class CreateProductCommand {
        ProductOperationRequest product;
    }
}
