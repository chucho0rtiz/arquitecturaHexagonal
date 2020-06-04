package co.com.ias.certification.backend.products.application.port.out;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;

public interface FindOneProductPort {
    ProductOperation findOneProduct(Long id);
}
