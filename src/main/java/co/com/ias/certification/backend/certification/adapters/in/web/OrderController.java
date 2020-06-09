package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public OrderOperation create(@RequestBody OrderOperationRequest order) {
        return createOrderUseCase.createOrder(CreateOrderUseCase.CreateOrderCommand.of(order));
    }

    @GetMapping
    public List<Order> findAll() {
        return findAllOrderUseCase.findAllOrders();
    }

    @GetMapping("/{id}")
    public OrderOperation findOne(@PathVariable Long id) {
        return findOneOrderUseCase.findOneOrder(FindOneOrderUseCase.FindOneOrderCommand.of(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody UpdateOrderRequest estado){
        OrderOperation order = updateOrderUseCase.updateOrder(UpdateOrderUseCase.UpdateOrderCommand.of(id, estado));
        if (order.isValid()){
            return ResponseEntity.ok(order);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(order);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        OrderOperation Order = deleteOrderUseCase.deleteOneOrder(DeleteOrderUseCase.DeleteOneOrderCommand.of(id));

        if (Order.isValid()){
            return ResponseEntity.ok(Order);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Order);
    }

}
