package co.com.ias.certification.backend.certification.application.port.in.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import lombok.Value;

public interface CreateProductListUseCase {
    ProductList createProductList(CreateProductListCommand command);

    @Value(staticConstructor = "of")
    class CreateProductListCommand {
        ProductList productList;
    }
}
