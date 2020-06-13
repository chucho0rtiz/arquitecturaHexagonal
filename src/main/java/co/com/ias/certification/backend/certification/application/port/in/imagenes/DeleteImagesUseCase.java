package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import lombok.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.util.List;

public interface DeleteImagesUseCase {
    boolean deleteImages(DeleteImagesCommand command) throws IOException;

    @Value(staticConstructor = "of")
    class DeleteImagesCommand {
        Long id;
    }

}
