package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class UpdateOrderRequest {
    Estado estado;

    public UpdateOrderRequest(Estado estado) {
        Preconditions.checkNotNull(estado);
        this.estado = estado;
    }
}
