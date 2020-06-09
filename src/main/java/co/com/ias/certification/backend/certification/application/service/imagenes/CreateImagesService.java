//package co.com.ias.certification.backend.certification.application.service.imagenes;
//
//import co.com.ias.certification.backend.certification.application.port.in.imagenes.CreateImagesUseCase;
//import co.com.ias.certification.backend.certification.application.port.out.imagenes.CreateImagesPort;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Component;
//
//import java.net.MalformedURLException;
//
//@Component
//@RequiredArgsConstructor
//public class CreateImagesService implements CreateImagesUseCase {
//    private final CreateImagesPort createImagesPort;
//
//    @Override
//    public void createImage(CreateImageCommand command) throws MalformedURLException {
//        createImagesPort.upload(command.getImages());
//    }
//}
