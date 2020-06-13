package co.com.ias.certification.backend.certification.application.port.in.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import lombok.Value;

import java.util.List;

public interface FindProductListUseCase {
    List<ProductList> findProductList(FindProducListCommand command);

    @Value(staticConstructor = "of")
    class FindProducListCommand {
        Long id;
    }

}
