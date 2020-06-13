package co.com.ias.certification.backend.certification.application.port.out.imagenes;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface FindImagesPort {
    List<Resource> findImages(Long id) throws IOException;
}
