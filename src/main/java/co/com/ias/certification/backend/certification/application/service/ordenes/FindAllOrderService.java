package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.FindAllOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.FindAllOrderPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class FindAllOrderService implements FindAllOrderUseCase {
    private final FindAllOrderPort findAllOrderPort;

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
    public List<Order> findAllOrders() {
        return findAllOrderPort.findAllOrders();
    }
}
