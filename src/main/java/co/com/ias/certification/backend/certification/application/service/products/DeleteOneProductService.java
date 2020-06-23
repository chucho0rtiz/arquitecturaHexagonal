package co.com.ias.certification.backend.certification.application.service.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.port.in.products.DeleteOneProductUseCase;
import co.com.ias.certification.backend.certification.application.port.out.products.DeleteOneProductPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;


@Component
@RequiredArgsConstructor
public class DeleteOneProductService implements DeleteOneProductUseCase {
    private final DeleteOneProductPort findOneProductPort;

    @Override
    public Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken) {
        AtomicBoolean acceso = new AtomicBoolean(false);
        authenticationToken.getAccount().getRoles().forEach((e) -> {
            if (e.contains("ADMIN")){
                acceso.set(true);
            }else {
                acceso.set(false);
            }
        });
        return acceso.get();
    }

    @Override
    public ProductOperation deleteOneProduct(DeleteOneProductCommand command) {
        return findOneProductPort.deleteOneProduct(command.getId());
    }
}
