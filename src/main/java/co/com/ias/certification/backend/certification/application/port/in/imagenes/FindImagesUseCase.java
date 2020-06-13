package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import lombok.Value;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FindImagesUseCase {
    List<Resource> findImages(FindImagesCommand command) throws IOException;

    @Value(staticConstructor = "of")
    class FindImagesCommand {
        Long id;
    }

}
