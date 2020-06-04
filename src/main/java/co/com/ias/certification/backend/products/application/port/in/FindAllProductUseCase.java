package co.com.ias.certification.backend.products.application.port.in;

import co.com.ias.certification.backend.products.application.domain.Product;

import java.util.List;

public interface FindAllProductUseCase {
    List<Product> findAllProduct();
}
