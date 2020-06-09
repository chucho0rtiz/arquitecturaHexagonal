package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.port.in.products.FindOneProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.FindOneProductPort;
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
