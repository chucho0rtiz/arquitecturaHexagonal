package co.com.ias.certification.backend.products.application.port.out;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;

public interface UpdateProductPort {
    ProductOperation updateProduct(Long id, ProductOperationRequest product);
}
