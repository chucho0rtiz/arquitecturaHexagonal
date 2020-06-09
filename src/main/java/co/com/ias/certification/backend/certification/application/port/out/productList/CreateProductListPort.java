package co.com.ias.certification.backend.certification.application.port.out.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;

public interface CreateProductListPort {
    ProductList createProductList(ProductList productList);
}
