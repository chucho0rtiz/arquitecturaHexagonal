package co.com.ias.certification.backend.products.application.port.in;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;
import lombok.Value;


public interface UpdateProductUseCase {
    ProductOperation updateProduct(UpdateProductCommand command);

    @Value(staticConstructor = "of")
    class UpdateProductCommand {
        Long id;
        ProductOperationRequest product;
    }
}
