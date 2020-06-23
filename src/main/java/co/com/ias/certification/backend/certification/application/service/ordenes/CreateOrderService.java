package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.CreateOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.CreateOrderPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {
    private final CreateOrderPort createOrderPort;

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
    public OrderOperation createOrder(CreateOrderCommand command) {
        return createOrderPort.createOrder(command.getOrder());
    }
}
