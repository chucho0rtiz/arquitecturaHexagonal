package co.com.ias.certification.backend.products.application.port.in;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import lombok.Value;

public interface DeleteOneProductUseCase {
    ProductOperation deleteOneProduct(DeleteOneProductCommand command);

    @Value(staticConstructor = "of")
    class DeleteOneProductCommand {
        Long id;
    }
}
