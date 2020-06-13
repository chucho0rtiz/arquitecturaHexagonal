package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.port.in.imagenes.CopiarImageUseCase;
import co.com.ias.certification.backend.certification.application.port.in.imagenes.DeleteImagesUseCase;
import co.com.ias.certification.backend.certification.application.port.in.imagenes.FindImagesUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/image")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ImagenesController {

    private final CopiarImageUseCase copiarImageUseCase;
    private final FindImagesUseCase findImagesUseCase;
    private final DeleteImagesUseCase deleteImagesUseCase;

    @PostMapping("/{id}")
    public ResponseEntity<?> copiarImage(@PathVariable Long id, @RequestParam("archivo") ArrayList<MultipartFile> archivo) throws IOException {
        Map<String, Object> response = new HashMap<>();
        for (int i = 0; i<archivo.size(); i++){
            copiarImageUseCase.copiarImage(CopiarImageUseCase.CopiarImageCommand.of((MultipartFile) archivo.toArray()[i], id));
        }
        response.put("message: ", "Imagen o conjunto de imagen registrada exitosamentre!!");
        return new ResponseEntity<>(response, HttpStatus.OK);

    }
    @PutMapping("{id}")
    public ResponseEntity<?> updateImage(@PathVariable Long id, @RequestParam("archivo") ArrayList<MultipartFile> archivo) throws IOException {
        Map<String, Object> response = new HashMap<>();
        boolean elimanar = deleteImagesUseCase.deleteImages(DeleteImagesUseCase.DeleteImagesCommand.of(id));
        if (true){
            for (int i = 0; i<archivo.size(); i++){
                copiarImageUseCase.copiarImage(CopiarImageUseCase.CopiarImageCommand.of((MultipartFile) archivo.toArray()[i], id));
            }
            response.put("message: ", "Imagenes actualizadas exitosamentre!!");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }else {
            response.put("message: ", "Imagenes actualizadas exitosamentre!!");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public List<Resource> findImages(@PathVariable Long id) throws IOException {
        return findImagesUseCase.findImages(FindImagesUseCase.FindImagesCommand.of(id));
    }

    @DeleteMapping("/{id}")
    public boolean deleteImages(@PathVariable Long id) throws IOException {
        return deleteImagesUseCase.deleteImages(DeleteImagesUseCase.DeleteImagesCommand.of(id));
    }
    
    
}
