package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Estado;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.UpdateOrderRequest;
import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

public interface UpdateOrderUseCase {
    OrderOperation updateOrder(UpdateOrderCommand command);

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class UpdateOrderCommand {
        Long id;
        UpdateOrderRequest estado;
    }
}
