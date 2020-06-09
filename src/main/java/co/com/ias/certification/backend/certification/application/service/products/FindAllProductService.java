package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.Product;
import co.com.ias.certification.backend.certification.application.port.in.products.FindAllProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.FindAllProductPort;
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
