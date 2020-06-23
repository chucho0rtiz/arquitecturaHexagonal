package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import co.com.ias.certification.backend.certification.application.port.in.products.UpdateProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.UpdateProductPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class UpdateProductService implements UpdateProductUseCase {
    private final UpdateProductPort updateProductPort;

    @Override
    public Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken) {
        AtomicBoolean acceso = new AtomicBoolean(false);
        authenticationToken.getAccount().getRoles().forEach((e) -> {
            if (!e.contains("CLIENTE")){
                acceso.set(true);
            }else {
                acceso.set(false);
            }
        });
        return acceso.get();
    }

    @Override
    public ProductOperation updateProduct(UpdateProductCommand command) {
        ProductOperationRequest product = command.getProduct();
        Long productId = command.getId();
        return updateProductPort.updateProduct(productId, product);
    }
}
