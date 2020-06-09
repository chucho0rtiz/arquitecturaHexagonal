package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import lombok.Value;

public interface FindOneOrderUseCase {
    OrderOperation findOneOrder(FindOneOrderCommand command);

    @Value(staticConstructor = "of")
    class FindOneOrderCommand {
        Long id;
    }

}
