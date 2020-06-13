package co.com.ias.certification.backend.certification.application.service.imagenes;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.CopiarImageUseCase;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.CopiarImagePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class CopiarImageService implements CopiarImageUseCase {
    private final CopiarImagePort copiarImagePort;

    @Override
    public String copiarImage(CopiarImageCommand command) throws IOException {
        return copiarImagePort.copiarImage(command.getArchivo(), command.getId());
    }
}
