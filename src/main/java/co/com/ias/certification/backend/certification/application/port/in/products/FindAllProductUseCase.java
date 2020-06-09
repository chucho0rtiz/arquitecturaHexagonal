package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.Product;

import java.util.List;

public interface FindAllProductUseCase {
    List<Product> findAllProduct();
}
