package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import lombok.Value;


public interface FindOneProductUseCase {
    ProductOperation findOneProduct(FindOneProductCommand command);

    @Value(staticConstructor = "of")
    class FindOneProductCommand {
        Long id;
    }
}
