package co.com.ias.certification.backend.certification.application.port.out.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;

import java.util.List;

public interface FindAllOrderPort {
    List<Order> findAllOrders();
}
