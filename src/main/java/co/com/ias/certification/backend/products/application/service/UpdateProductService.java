package co.com.ias.certification.backend.products.application.service;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.application.port.in.UpdateProductUseCase;
import co.com.ias.certification.backend.products.application.port.out.UpdateProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final UpdateProductPort updateProductPort;

    @Override
    public ProductOperation updateProduct(UpdateProductCommand command) {
        ProductOperationRequest product = command.getProduct();
        Long productId = command.getId();
        return updateProductPort.updateProduct(productId, product);
    }
}
