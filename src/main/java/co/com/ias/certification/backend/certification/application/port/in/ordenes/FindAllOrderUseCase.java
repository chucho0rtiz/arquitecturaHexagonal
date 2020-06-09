package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;

import java.util.List;

public interface FindAllOrderUseCase {
    List<Order> findAllOrders();
}
