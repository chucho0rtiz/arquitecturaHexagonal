package co.com.ias.certification.backend.products.application.service;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.port.in.FindOneProductUseCase;
import co.com.ias.certification.backend.products.application.port.out.FindOneProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOneProductService implements FindOneProductUseCase {
    private final FindOneProductPort findOneProductPort;

    @Override
    public ProductOperation findOneProduct(FindOneProductCommand command) {
        return findOneProductPort.findOneProduct(command.getId());
    }
}
