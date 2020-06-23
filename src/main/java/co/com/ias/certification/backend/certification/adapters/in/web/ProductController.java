package co.com.ias.certification.backend.certification.adapters.in.web;
import co.com.ias.certification.backend.certification.application.domain.products.Product;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperation;
import co.com.ias.certification.backend.certification.application.domain.products.ProductOperationRequest;
import co.com.ias.certification.backend.certification.application.port.in.products.*;
import co.com.ias.certification.backend.certification.application.service.products.CreateProductService;
import co.com.ias.certification.backend.certification.application.service.products.FindOneProductService;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {
    private final CreateProductUseCase createProductUseCase;
    private final FindAllProductUseCase findAllProductUseCase;
    private final FindOneProductUseCase findOneProductUseCase;
    private final DeleteOneProductUseCase deleteOneProductUseCase;
    private final UpdateProductUseCase updateProductUseCase;

//    @PostMapping
//    public Try<Product> createProduct(@RequestBody CreateProductUseCase.CreateProductCommand command, Authentication authentication) {
//        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
//        return createProductUseCase.userHasPermission(authenticationToken)
//                .flatMap(permission -> createProductUseCase.createProduct(command));
//    }

    @PostMapping
    public ProductOperation create(@RequestBody ProductOperationRequest product, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = createProductUseCase.userHasPermission(authenticationToken);
        if (permission){
            return createProductUseCase.createProduct(CreateProductUseCase.CreateProductCommand.of(product));
        }
        return null;
    }

    @GetMapping
    public List<Product> findAll(){
        return findAllProductUseCase.findAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id, Authentication authentication) {
        ProductOperation product = findOneProductUseCase.findOneProduct(FindOneProductUseCase.FindOneProductCommand.of(id));
        if (product.isValid()) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductOperationRequest productOperationRequest, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = updateProductUseCase.userHasPermission(authenticationToken);
        if (permission) {
            ProductOperation product = updateProductUseCase.updateProduct(UpdateProductUseCase.UpdateProductCommand.of(id, productOperationRequest));
            if (product.isValid()) {
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(permission);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = deleteOneProductUseCase.userHasPermission(authenticationToken);
        if (permission) {
            ProductOperation product = deleteOneProductUseCase.deleteOneProduct(DeleteOneProductUseCase.DeleteOneProductCommand.of(id));
            if (product.isValid()) {
                return ResponseEntity.ok(product);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(permission);
    }
}
