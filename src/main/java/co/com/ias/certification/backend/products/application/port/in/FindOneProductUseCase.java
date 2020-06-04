package co.com.ias.certification.backend.products.application.port.in;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import lombok.Value;


public interface FindOneProductUseCase {
    ProductOperation findOneProduct(FindOneProductCommand command);

    @Value(staticConstructor = "of")
    class FindOneProductCommand {
        Long id;
    }
}
