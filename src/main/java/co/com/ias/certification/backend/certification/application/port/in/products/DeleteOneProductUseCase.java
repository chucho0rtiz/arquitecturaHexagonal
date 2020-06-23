package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface DeleteOneProductUseCase {
    ProductOperation deleteOneProduct(DeleteOneProductCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class DeleteOneProductCommand {
        Long id;
    }
}
