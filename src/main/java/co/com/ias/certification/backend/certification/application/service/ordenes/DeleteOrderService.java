package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.DeleteOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.DeleteOneOrderPort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class DeleteOrderService implements DeleteOrderUseCase {
    private final DeleteOneOrderPort deleteOneOrderPort;

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
    public OrderOperation deleteOneOrder(DeleteOneOrderCommand command) {
        return deleteOneOrderPort.deleteOneOrder(command.getId());
    }
}
