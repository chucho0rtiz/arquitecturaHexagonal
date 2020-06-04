package co.com.ias.certification.backend.products.application.service;

import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.port.in.DeleteOneProductUseCase;
import co.com.ias.certification.backend.products.application.port.out.DeleteOneProductPort;
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
