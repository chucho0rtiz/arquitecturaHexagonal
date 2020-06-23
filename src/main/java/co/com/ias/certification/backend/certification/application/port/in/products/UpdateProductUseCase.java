package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;


public interface UpdateProductUseCase {
    ProductOperation updateProduct(UpdateProductCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class UpdateProductCommand {
        Long id;
        ProductOperationRequest product;
    }
}
