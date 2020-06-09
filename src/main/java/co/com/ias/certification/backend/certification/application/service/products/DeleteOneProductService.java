package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.port.in.products.DeleteOneProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.DeleteOneProductPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class DeleteOneProductService implements DeleteOneProductUseCase {
    private final DeleteOneProductPort findOneProductPort;

    @Override
    public ProductOperation deleteOneProduct(DeleteOneProductCommand command) {
        return findOneProductPort.deleteOneProduct(command.getId());
    }
}
