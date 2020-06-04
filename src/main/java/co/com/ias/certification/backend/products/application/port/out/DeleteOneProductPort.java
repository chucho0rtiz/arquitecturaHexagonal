package co.com.ias.certification.backend.products.application.port.out;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;

public interface DeleteOneProductPort {
    ProductOperation deleteOneProduct(Long id);
}
