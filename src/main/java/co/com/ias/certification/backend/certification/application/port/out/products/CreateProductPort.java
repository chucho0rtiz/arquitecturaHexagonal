package co.com.ias.certification.backend.certification.application.port.out.products;


import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationSuccess;
import io.vavr.control.Try;

public interface CreateProductPort {
    ProductOperation createProduct(ProductOperationRequest product);
}
