package co.com.ias.certification.backend.certification.application.port.in.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import lombok.Value;

public interface CreateProductListUseCase {
    ProductListOperation createProductList(CreateProductListCommand command);

    @Value(staticConstructor = "of")
    class CreateProductListCommand {
        ProductList productList;
    }
}
