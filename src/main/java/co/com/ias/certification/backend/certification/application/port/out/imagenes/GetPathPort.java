package co.com.ias.certification.backend.certification.application.port.out.imagenes;

import java.net.MalformedURLException;
import java.nio.file.Path;

public interface GetPathPort {
    Path getPath(String nombre) throws MalformedURLException;
}
