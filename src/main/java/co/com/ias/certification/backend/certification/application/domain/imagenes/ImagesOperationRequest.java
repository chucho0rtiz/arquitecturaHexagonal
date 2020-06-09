package co.com.ias.certification.backend.certification.application.domain.imagenes;

import co.com.ias.certification.backend.certification.application.domain.products.*;
import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "from")
public class ImagesOperationRequest {
    Name nombre;
    Integer productId;

    public ImagesOperationRequest(Name nombre, Integer productId) {
        Preconditions.checkNotNull(nombre);
        Preconditions.checkNotNull(productId);

        this.productId = productId;
        this.nombre = nombre;
    }
}
