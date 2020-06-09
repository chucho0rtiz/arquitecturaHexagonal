package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.DeleteOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.DeleteOneOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteOrderService implements DeleteOrderUseCase {
    private final DeleteOneOrderPort deleteOneOrderPort;

    @Override
    public OrderOperation deleteOneOrder(DeleteOneOrderCommand command) {
        return deleteOneOrderPort.deleteOneOrder(command.getId());
    }
}
