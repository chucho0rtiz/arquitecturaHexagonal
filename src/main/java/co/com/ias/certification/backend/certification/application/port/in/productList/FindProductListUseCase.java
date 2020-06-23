package co.com.ias.certification.backend.certification.application.port.in.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import java.util.List;

public interface FindProductListUseCase {
    List<ProductList> findProductList(FindProducListCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class FindProducListCommand {
        Long id;
    }

}
