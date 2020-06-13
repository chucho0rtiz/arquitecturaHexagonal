package co.com.ias.certification.backend.certification.application.service.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.out.productList.CreateProductListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductListService implements CreateProductListUseCase {
    private final CreateProductListPort createProductListPort;

    @Override
    public ProductListOperation createProductList(CreateProductListCommand command) {
        return createProductListPort.createProductList(command.getProductList());
    }
}
