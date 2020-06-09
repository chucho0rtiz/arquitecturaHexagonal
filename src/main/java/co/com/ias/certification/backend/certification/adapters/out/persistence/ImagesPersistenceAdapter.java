//package co.com.ias.certification.backend.certification.adapters.out.persistence;
//
//import co.com.ias.certification.backend.certification.application.domain.imagen.Images;
//import co.com.ias.certification.backend.certification.application.domain.imagen.ImagesOperationRequest;
//import co.com.ias.certification.backend.certification.application.port.out.imagenes.CreateImagesPort;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.UrlResource;
//
//import java.net.MalformedURLException;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//
//public class ImagesPersistenceAdapter implements CreateImagesPort {
//
//    private final static String DIRECTORIO_UPLOAD = "uploads";
//
//    @Override
//    public Images upload(ImagesOperationRequest images) throws MalformedURLException {
//
//        Path rutaArchivo = getPath(images.getNombre().getValue());
//        System.out.println(rutaArchivo.toString());
//
//        Resource recurso = new UrlResource(rutaArchivo.toUri());
//
//        if(!recurso.exists() && !recurso.isReadable()) {
//            rutaArchivo = Paths.get("src/main/resources/static/images").resolve("notUser.png").toAbsolutePath();
//
//            try {
//                recurso = new UrlResource(rutaArchivo.toUri());
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            }
//
//            System.out.println("No se pudo cargar la imagen");
//        }
//        return recurso;
//    }
//
//    public Path getPath(String nombreFoto) {
//        return Paths.get(DIRECTORIO_UPLOAD).resolve(nombreFoto).toAbsolutePath();
//    }
//}
