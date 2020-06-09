package co.com.ias.certification.backend.certification.application.port.in.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import lombok.Value;

public interface DeleteOrderUseCase {
    OrderOperation deleteOneOrder(DeleteOneOrderCommand command);

    @Value(staticConstructor = "of")
    class DeleteOneOrderCommand {
        Long id;
    }
}
