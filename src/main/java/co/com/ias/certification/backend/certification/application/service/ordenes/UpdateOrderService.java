package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.UpdateOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.UpdateOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UpdateOrderService implements UpdateOrderUseCase {
    private final UpdateOrderPort updateOrderPort;

    @Override
    public OrderOperation updateOrder(UpdateOrderCommand command) {
        return updateOrderPort.updateOrder(command.getId(), command.getEstado());
    }
}
