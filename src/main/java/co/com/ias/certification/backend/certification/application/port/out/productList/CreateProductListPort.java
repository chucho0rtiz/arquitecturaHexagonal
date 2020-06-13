package co.com.ias.certification.backend.certification.application.port.out.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;

public interface CreateProductListPort {
    ProductListOperation createProductList(ProductList productList);
}
