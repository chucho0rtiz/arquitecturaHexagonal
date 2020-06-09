package co.com.ias.certification.backend.certification.application.port.out.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;

public interface DeleteOneOrderPort {
    OrderOperation deleteOneOrder(Long id);
}
