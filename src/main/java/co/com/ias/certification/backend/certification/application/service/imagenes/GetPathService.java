package co.com.ias.certification.backend.certification.application.service.imagenes;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.GetPathUseCase;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.FindImagesPort;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.GetPathPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.nio.file.Path;

@Component
@RequiredArgsConstructor
public class GetPathService implements GetPathUseCase {
    private final GetPathPort getPathPort;

    @Override
    public Path getPath(GetPathCommand command) throws MalformedURLException {
        return getPathPort.getPath(command.getNombre());
    }
}
