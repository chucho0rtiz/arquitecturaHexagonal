package co.com.ias.certification.backend.certification.application.service.productList;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.FindOneOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.in.productList.FindProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.FindOneOrderPort;
import co.com.ias.certification.backend.certification.application.port.out.productList.FindProductListPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class FindProductListService implements FindProductListUseCase {
    private final FindProductListPort findProductListPort;

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
    public List<ProductList> findProductList(FindProducListCommand command) {
        return findProductListPort.findProdutcList(command.getId());
    }
}
