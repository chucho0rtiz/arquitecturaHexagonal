package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import co.com.ias.certification.backend.certification.application.port.in.products.CreateProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.CreateProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateProductService implements CreateProductUseCase {
    private final CreateProductPort createProductPort;

    @Override
    public ProductOperation createProduct(CreateProductCommand command) {
        ProductOperationRequest product = command.getProduct();
        return createProductPort.createProduct(product);
    }
}
