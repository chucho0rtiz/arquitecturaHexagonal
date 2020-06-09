package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import co.com.ias.certification.backend.certification.application.domain.imagenes.ImagesOperationRequest;
import lombok.Value;

import java.net.MalformedURLException;

public interface CreateImagesUseCase {
    void createImage(CreateImageCommand command) throws MalformedURLException;

    @Value(staticConstructor = "of")
    class CreateImageCommand {
        ImagesOperationRequest images;
    }
}
