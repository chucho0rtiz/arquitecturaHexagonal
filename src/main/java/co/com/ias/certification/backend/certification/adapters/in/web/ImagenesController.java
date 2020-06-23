package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.CopiarImageUseCase;
import co.com.ias.certification.backend.certification.application.port.in.imagenes.DeleteImagesUseCase;
import co.com.ias.certification.backend.certification.application.port.in.imagenes.FindImagesUseCase;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImagenesController {

    private final CopiarImageUseCase copiarImageUseCase;
    private final FindImagesUseCase findImagesUseCase;
    private final DeleteImagesUseCase deleteImagesUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<?> copiarImage(@PathVariable Long id, @RequestParam("archivo") ArrayList<MultipartFile> archivo, Authentication authentication) throws IOException {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = copiarImageUseCase.userHasPermission(authenticationToken);
        Map<String, Object> response = new HashMap<>();
        if (permission) {
            if (!archivo.isEmpty()) {
                for (int i = 0; i < archivo.size(); i++) {
                    copiarImageUseCase.copiarImage(CopiarImageUseCase.CopiarImageCommand.of((MultipartFile) archivo.toArray()[i], id));
                }
                response.put("message: ", "Imagen o conjunto de imagen registrada exitosamentre!!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message: ", "No has enviado imagen adjunta");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        response.put("message: ", "Imagen o conjunto de imagen registrada exitosamentre!!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("archivo") ArrayList<MultipartFile> archivo, Authentication authentication) throws IOException {
//        Valido el permiso para actualizar en caso de que sea cliente
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        AtomicBoolean permission = new AtomicBoolean(false);
        authenticationToken.getAccount().getRoles().forEach((e) -> {
            if (!e.contains("CLIENTE")){
                permission.set(true);
            }else {
                permission.set(false);
            }
        });

//        aplicar el permiso para la actualización
        Map<String, Object> response = new HashMap<>();
        if (permission.get()) {
            boolean elimanar = deleteImagesUseCase.deleteImages(DeleteImagesUseCase.DeleteImagesCommand.of(id));
            if (true) {
                for (int i = 0; i < archivo.size(); i++) {
                    copiarImageUseCase.copiarImage(CopiarImageUseCase.CopiarImageCommand.of((MultipartFile) archivo.toArray()[i], id));
                }
                response.put("message: ", "Imagenes actualizadas exitosamentre!!");
                return new ResponseEntity<>(response, HttpStatus.OK);
            } else {
                response.put("message: ", "No se pudo actualizar las imagenes");
                return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
            }
        }
        response.put("message: ", "Usted no tiene permisos para hacer este proceso.");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public List<Resource> findImages(@PathVariable Long id) throws IOException {
        return findImagesUseCase.findImages(FindImagesUseCase.FindImagesCommand.of(id));
    }

    @DeleteMapping("/{id}")
    public boolean deleteImages(@PathVariable Long id, Authentication authentication) throws IOException {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = deleteImagesUseCase.userHasPermission(authenticationToken);
        if (permission) {
            return deleteImagesUseCase.deleteImages(DeleteImagesUseCase.DeleteImagesCommand.of(id));
        }
        return false;
    }
    
    
}
