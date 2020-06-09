package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.CreateOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.CreateOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CreateOrderService implements CreateOrderUseCase {
    private final CreateOrderPort createOrderPort;

    @Override
    public OrderOperation createOrder(CreateOrderCommand command) {
        return createOrderPort.createOrder(command.getOrder());
    }
}
