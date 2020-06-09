package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.FindOneOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.FindOneOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindOneOrderService implements FindOneOrderUseCase {
    private final FindOneOrderPort findOneOrderPort;

    @Override
    public OrderOperation findOneOrder(FindOneOrderCommand command) {
        return findOneOrderPort.findOneOrder(command.getId());
    }
}
