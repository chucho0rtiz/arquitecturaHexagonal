package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import lombok.Value;

import java.net.MalformedURLException;
import java.nio.file.Path;

public interface GetPathUseCase {
    Path getPath(GetPathCommand command) throws MalformedURLException;

    @Value(staticConstructor = "of")
    class GetPathCommand {
        String nombre;
    }

}
