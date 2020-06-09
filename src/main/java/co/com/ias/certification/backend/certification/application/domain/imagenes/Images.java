package co.com.ias.certification.backend.certification.application.domain.imagenes;

import co.com.ias.certification.backend.certification.application.domain.products.Name;
import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class Images {
    Long id;
    Name nombre;
    Integer productId;

    public Images(Long id, Name nombre, Integer productId) {
        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(nombre);
        Preconditions.checkNotNull(productId);

        this.id = id;
        this.nombre = nombre;
        this.productId = productId;
    }
}
