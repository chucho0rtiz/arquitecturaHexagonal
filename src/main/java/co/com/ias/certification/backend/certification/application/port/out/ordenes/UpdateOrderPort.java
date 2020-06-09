package co.com.ias.certification.backend.certification.application.port.out.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.UpdateOrderRequest;

public interface UpdateOrderPort {
    OrderOperation updateOrder(Long id, UpdateOrderRequest estado);
}
