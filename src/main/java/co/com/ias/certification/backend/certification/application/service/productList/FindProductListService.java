package co.com.ias.certification.backend.certification.application.service.productList;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.FindOneOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.in.productList.FindProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.FindOneOrderPort;
import co.com.ias.certification.backend.certification.application.port.out.productList.FindProductListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindProductListService implements FindProductListUseCase {
    private final FindProductListPort findProductListPort;

    @Override
    public List<ProductList> findProductList(FindProducListCommand command) {
        return findProductListPort.findProdutcList(command.getId());
    }
}
