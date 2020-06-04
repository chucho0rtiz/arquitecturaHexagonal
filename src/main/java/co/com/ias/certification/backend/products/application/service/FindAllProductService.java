package co.com.ias.certification.backend.products.application.service;

import co.com.ias.certification.backend.products.application.domain.Product;
import co.com.ias.certification.backend.products.application.port.in.FindAllProductUseCase;
import co.com.ias.certification.backend.products.application.port.out.FindAllProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class FindAllProductService implements FindAllProductUseCase {
    private final FindAllProductPort findAllProductPort;

    @Override
    public List<Product> findAllProduct() {
        return findAllProductPort.findAllProduct();
    }
}
