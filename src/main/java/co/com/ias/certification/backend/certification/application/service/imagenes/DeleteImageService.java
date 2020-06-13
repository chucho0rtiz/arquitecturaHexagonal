package co.com.ias.certification.backend.certification.application.service.imagenes;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.DeleteImagesUseCase;
import co.com.ias.certification.backend.certification.application.port.in.imagenes.FindImagesUseCase;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.DeleteImagesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DeleteImageService implements DeleteImagesUseCase {
    private final DeleteImagesPort deleteImagesPort;

    @Override
    public boolean deleteImages(DeleteImagesCommand command) throws IOException {
        return deleteImagesPort.deleteImages(command.getId());
    }
}
