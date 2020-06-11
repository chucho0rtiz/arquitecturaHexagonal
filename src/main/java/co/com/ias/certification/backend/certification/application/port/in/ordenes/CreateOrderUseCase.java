package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperationRequest;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import lombok.Value;

import java.util.ArrayList;

public interface CreateOrderUseCase {
    OrderOperation createOrder(CreateOrderCommand command);

    @Value(staticConstructor = "of")
    class CreateOrderCommand {
        OrderOperationRequest order;
    }
}
