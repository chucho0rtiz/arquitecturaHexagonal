package co.com.ias.certification.backend.certification.application.domain.images;

import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class ImagesNombre {
//    Long id;
    String nombre;
//    Integer productid;

    public ImagesNombre(String nombre) {
//        Preconditions.checkNotNull(id);
        Preconditions.checkNotNull(nombre);
//        Preconditions.checkNotNull(productid);

//        this.id = id;
        this.nombre = nombre;
//        this.productid = productid;
    }
}
