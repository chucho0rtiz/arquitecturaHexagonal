package co.com.ias.certification.backend.certification.application.port.out.imagenes;

import co.com.ias.certification.backend.certification.application.domain.imagenes.ImagesOperationRequest;
import org.springframework.core.io.Resource;

import java.net.MalformedURLException;

public interface CreateImagesPort {
    Resource upload(ImagesOperationRequest images) throws MalformedURLException;
}
