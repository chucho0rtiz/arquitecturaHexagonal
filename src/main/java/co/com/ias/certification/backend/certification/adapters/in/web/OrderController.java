package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.*;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    private final CreateProductListUseCase createProductListUseCase;

    @PostMapping
    public OrderOperation create(@RequestBody OrderOperationRequest order) {
        return createOrderUseCase.createOrder(CreateOrderUseCase.CreateOrderCommand.of(order));
    }
//    private ProductList data = null;
    @PostMapping("/listproduct")
    public ResponseEntity<?> createListProduct(@RequestBody ArrayList<ProductList> productList) {
            Map<String, Object> response = new HashMap<>();
        for (int i = 0; i<productList.size(); i++){
            createProductListUseCase.createProductList(CreateProductListUseCase.CreateProductListCommand.of((ProductList) productList.toArray()[i]));
        }
        response.put("message: los productos fueron registrados correctamente.", productList);
        return new ResponseEntity<>(response, HttpStatus.OK);
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
