package co.com.ias.certification.backend.certification.application.service.productList;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.out.productList.CreateProductListPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class CreateProductListService implements CreateProductListUseCase {
    private final CreateProductListPort createProductListPort;

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
    public ProductListOperation createProductList(CreateProductListCommand command) {
        return createProductListPort.createProductList(command.getProductList());
    }
}
