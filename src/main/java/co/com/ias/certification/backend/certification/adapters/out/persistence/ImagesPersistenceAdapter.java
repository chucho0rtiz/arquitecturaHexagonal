package co.com.ias.certification.backend.certification.adapters.out.persistence;

import co.com.ias.certification.backend.certification.application.domain.images.ImagesNombre;
import co.com.ias.certification.backend.certification.application.port.out.imagenes.*;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ImagesPersistenceAdapter implements FindImagesPort, GetPathPort, CopiarImagePort, DeleteImagesPort {
    private final static String DIRECTORIO_UPLOAD = "uploads";
    private final JdbcTemplate jdbcTemplate;

    public final RowMapper<ImagesNombre> rowMapper =(resultSet, i)->{
//        Long id = resultSet.getLong("id");
        String nombre = resultSet.getString("nombre");
//        Integer productid = resultSet.getInt("productid");
        return ImagesNombre.of(nombre);
    };

    @Override
    public Path getPath(String nombreFoto) {
        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
    }

    @Override
    public String copiarImage(MultipartFile archivo, Long id) throws IOException {
        if (!archivo.isEmpty()){
            String nombreArchivo = UUID.randomUUID().toString() + "_" + archivo.getOriginalFilename().replace(" ", "");
            Path rutaArchivo = getPath(nombreArchivo);
//            System.out.println(rutaArchivo.toString());
            Files.copy(archivo.getInputStream(), rutaArchivo);

            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                    .withTableName("IMAGENES")
                    .usingGeneratedKeyColumns("id");
            Map<String, Object> parameters = new HashMap<>();
            parameters.put("nombre",nombreArchivo);
            parameters.put("productid",id);
            simpleJdbcInsert.executeAndReturnKey(parameters);
            return nombreArchivo;
        }else {
            return "La imagen no ha podido cargarse correctamente";
        }
    }

    @Override
    public List<Resource> findImages(Long id) throws IOException {
        String SQL = String.format("SELECT nombre FROM IMAGENES WHERE productid=%s",id);
        List<ImagesNombre> data = jdbcTemplate.query(SQL, rowMapper);
        List<Resource> listaImages = new ArrayList<>();
        if (data.size()>0){
            for (int i = 0; i < data.size(); i++) {
                String nombreFoto = data.get(i).getNombre();
//            System.out.println("Nombre foto ==> " + nombreFoto);
                Path rutaArchivo = getPath(nombreFoto);
//            System.out.println("rutaImagen ==> "+rutaArchivo.toString());
                Resource recurso = new UrlResource(rutaArchivo.toUri());
                listaImages.add(recurso);
            }
            return listaImages;
        }else {
            Path rutaArchivo = Paths.get("src/main/resources/static/images").resolve("noImage.png").toAbsolutePath();
            Resource recurso = new UrlResource(rutaArchivo.toUri());
            listaImages.add(recurso);
            return listaImages;
        }
    }

    @Override
    public boolean deleteImages(Long id) throws IOException {
        // buscar la imagen y eliminarla en la carpeta uploads
        String SQL = String.format("SELECT nombre FROM IMAGENES WHERE productid=%s",id);
        List<ImagesNombre> data = jdbcTemplate.query(SQL, rowMapper);
        if (data.size()>0) {
            for (int i = 0; i < data.size(); i++) {
                String nombreFoto = data.get(i).getNombre();
                Path rutaArchivo = getPath(nombreFoto);
                File archivoFotoAnterior = rutaArchivo.toFile();
                if (archivoFotoAnterior.exists() && archivoFotoAnterior.canRead()) {
                    archivoFotoAnterior.delete();
                    String SQLDeleteImagenes = "DELETE FROM IMAGENES WHERE productid = ?";
                    Object[] objects = {id};
                    jdbcTemplate.update(SQLDeleteImagenes, objects);
                }
            }
            return true;
        }else {
            return false;
        }
    }
}
