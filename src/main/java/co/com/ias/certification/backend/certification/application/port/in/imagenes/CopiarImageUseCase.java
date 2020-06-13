package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import lombok.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CopiarImageUseCase {
    String copiarImage(CopiarImageCommand command) throws IOException;

    @Value(staticConstructor = "of")
    class CopiarImageCommand {
        MultipartFile archivo;
        Long id;
    }
}
