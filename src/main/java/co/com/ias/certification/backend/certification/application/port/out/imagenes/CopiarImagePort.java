package co.com.ias.certification.backend.certification.application.port.out.imagenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperationRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CopiarImagePort {
    String copiarImage(MultipartFile order, Long id) throws IOException;
}
