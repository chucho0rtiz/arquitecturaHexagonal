package co.com.ias.certification.backend.certification.application.port.out.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;

public interface UpdateProductPort {
    ProductOperation updateProduct(Long id, ProductOperationRequest product);
}
