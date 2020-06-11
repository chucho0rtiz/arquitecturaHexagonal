package co.com.ias.certification.backend.certification.application.port.out.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperationRequest;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;

import java.util.ArrayList;

public interface CreateOrderPort {
    OrderOperation createOrder(OrderOperationRequest order);
}
