package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import lombok.Value;

public interface DeleteOneProductUseCase {
    ProductOperation deleteOneProduct(DeleteOneProductCommand command);

    @Value(staticConstructor = "of")
    class DeleteOneProductCommand {
        Long id;
    }
}
