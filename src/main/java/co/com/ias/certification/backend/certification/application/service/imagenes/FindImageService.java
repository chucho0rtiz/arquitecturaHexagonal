package co.com.ias.certification.backend.certification.application.service.imagenes;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.FindImagesUseCase;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.FindImagesPort;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class FindImageService implements FindImagesUseCase {
    private final FindImagesPort findImagesPort;

    @Override
    public List<Resource> findImages(FindImagesCommand command) throws IOException {
        return findImagesPort.findImages(command.getId());
    }
}
