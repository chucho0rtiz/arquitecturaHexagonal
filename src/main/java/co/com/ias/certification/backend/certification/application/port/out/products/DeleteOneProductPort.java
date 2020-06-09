package co.com.ias.certification.backend.certification.application.port.out.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;

public interface DeleteOneProductPort {
    ProductOperation deleteOneProduct(Long id);
}
