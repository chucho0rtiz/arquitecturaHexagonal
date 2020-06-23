package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface DeleteOrderUseCase {
    OrderOperation deleteOneOrder(DeleteOneOrderCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class DeleteOneOrderCommand {
        Long id;
    }
}
