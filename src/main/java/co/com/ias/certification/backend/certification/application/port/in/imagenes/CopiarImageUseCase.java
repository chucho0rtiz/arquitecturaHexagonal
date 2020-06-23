package co.com.ias.certification.backend.certification.application.port.in.imagenes;

import lombok.Value;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface CopiarImageUseCase {
    String copiarImage(CopiarImageCommand command) throws IOException;

    Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken);

    @Value(staticConstructor = "of")
    class CopiarImageCommand {
        MultipartFile archivo;
        Long id;
    }
}
