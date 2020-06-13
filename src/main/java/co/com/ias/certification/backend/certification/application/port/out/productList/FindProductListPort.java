package co.com.ias.certification.backend.certification.application.port.out.productList;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;

import java.util.List;

public interface FindProductListPort {
    List<ProductList> findProdutcList(Long id);
}
