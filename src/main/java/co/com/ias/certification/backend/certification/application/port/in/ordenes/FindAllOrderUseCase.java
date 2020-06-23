package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;

import java.util.List;

public interface FindAllOrderUseCase {
    List<Order> findAllOrders();
    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);
}
