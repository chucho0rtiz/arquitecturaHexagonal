package co.com.ias.certification.backend.products.application.service;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.application.port.in.CreateProductUseCase;
import co.com.ias.certification.backend.products.application.port.out.CreateProductPort;
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
