package co.com.ias.certification.backend.certification.application.service.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;
import co.com.ias.certification.backend.certification.application.port.in.ordenes.FindAllOrderUseCase;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.FindAllOrderPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllOrderService implements FindAllOrderUseCase {
    private final FindAllOrderPort findAllOrderPort;

    @Override
    public List<Order> findAllOrders() {
        return findAllOrderPort.findAllOrders();
    }
}
