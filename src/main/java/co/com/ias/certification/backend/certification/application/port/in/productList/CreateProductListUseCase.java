package co.com.ias.certification.backend.certification.application.port.in.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface CreateProductListUseCase {
    ProductListOperation createProductList(CreateProductListCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class CreateProductListCommand {
        ProductList productList;
    }
}
