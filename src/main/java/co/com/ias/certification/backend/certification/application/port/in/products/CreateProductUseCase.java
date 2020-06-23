package co.com.ias.certification.backend.certification.application.port.in.products;

import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import io.vavr.control.Try;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface CreateProductUseCase {
    ProductOperation createProduct(CreateProductCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class CreateProductCommand {
        ProductOperationRequest product;
    }
}
