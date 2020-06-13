package co.com.ias.certification.backend.certification.application.port.out.imagenes;


import java.io.IOException;

public interface DeleteImagesPort {
    boolean deleteImages(Long id) throws IOException;
}
