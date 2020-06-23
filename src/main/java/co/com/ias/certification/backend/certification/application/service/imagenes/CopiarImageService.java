package co.com.ias.certification.backend.certification.application.service.imagenes;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.CopiarImageUseCase;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.CopiarImagePort;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

@Component
@RequiredArgsConstructor
public class CopiarImageService implements CopiarImageUseCase {
    private final CopiarImagePort copiarImagePort;

    @Override
    public Boolean userHasPermission(KeycloakAuthenticationToken authenticationToken) {
        AtomicBoolean acceso = new AtomicBoolean(false);
        authenticationToken.getAccount().getRoles().forEach((e) -> {
            if (!e.contains("CLIENTE")){
                acceso.set(true);
            }else {
                acceso.set(false);
            }
        });
        return acceso.get();
    }

    @Override
    public String copiarImage(CopiarImageCommand command) throws IOException {
        return copiarImagePort.copiarImage(command.getArchivo(), command.getId());
    }
}
