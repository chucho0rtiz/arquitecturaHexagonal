package co.com.ias.certification.backend.certification.application.port.out.products;

import co.com.ias.certification.backend.certification.application.domain.products.Product;

import java.util.List;

public interface FindAllProductPort {
    List<Product> findAllProduct();
}
