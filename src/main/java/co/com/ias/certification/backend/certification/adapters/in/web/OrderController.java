package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.*;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {
    private final CreateOrderUseCase createOrderUseCase;
    private final FindAllOrderUseCase findAllOrderUseCase;
    private final FindOneOrderUseCase findOneOrderUseCase;
    private final UpdateOrderUseCase updateOrderUseCase;
    private final DeleteOrderUseCase deleteOrderUseCase;


    @PostMapping
    public OrderOperation create(@RequestBody OrderOperationRequest order, Authentication authentication) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = createOrderUseCase.userHasPermission(authenticationToken);
        if (permission) {
            return createOrderUseCase.createOrder(CreateOrderUseCase.CreateOrderCommand.of(order));
        }
        return null;
    }

    @GetMapping
    public List<Order> findAll(Authentication authentication) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = findAllOrderUseCase.userHasPermission(authenticationToken);
        if (permission) {
            return findAllOrderUseCase.findAllOrders();
        }
        return null;
    }

    @GetMapping("/{id}")
    public OrderOperation findOne(@PathVariable Long id, Authentication authentication) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = findOneOrderUseCase.userHasPermission(authenticationToken);
        if (permission) {
            return findOneOrderUseCase.findOneOrder(FindOneOrderUseCase.FindOneOrderCommand.of(id));
        }
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateOrderRequest estado, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = updateOrderUseCase.userHasPermission(authenticationToken);
        if (permission) {
            OrderOperation order = updateOrderUseCase.updateOrder(UpdateOrderUseCase.UpdateOrderCommand.of(id, estado));
            if (order.isValid()) {
                return ResponseEntity.ok(order);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(order);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = deleteOrderUseCase.userHasPermission(authenticationToken);
        if (permission) {
            OrderOperation Order = deleteOrderUseCase.deleteOneOrder(DeleteOrderUseCase.DeleteOneOrderCommand.of(id));
            if (Order.isValid()) {
                return ResponseEntity.ok(Order);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Order);
        }
        return null;
    }

}
