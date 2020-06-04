package co.com.ias.certification.backend.products.adapters.in.web;

import co.com.ias.certification.backend.products.application.domain.Product;
import co.com.ias.certification.backend.products.application.domain.ProductOperation;
import co.com.ias.certification.backend.products.application.domain.ProductOperationRequest;
import co.com.ias.certification.backend.products.application.port.in.*;
import co.com.ias.certification.backend.products.application.service.CreateProductService;
import co.com.ias.certification.backend.products.application.service.FindOneProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @PostMapping
    public ProductOperation create(@RequestBody ProductOperationRequest product){
        return createProductUseCase.createProduct(CreateProductService.CreateProductCommand.of(product));
    }

    @GetMapping
    public List<Product> findAll(){
        return findAllProductUseCase.findAllProduct();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findOne(@PathVariable Long id) {

        ProductOperation product = findOneProductUseCase.findOneProduct(FindOneProductService.FindOneProductCommand.of(id));
        if (product.isValid()){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ProductOperationRequest productOperationRequest){
        ProductOperation product = updateProductUseCase.updateProduct(UpdateProductUseCase.UpdateProductCommand.of(id, productOperationRequest));
        if (product.isValid()){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        ProductOperation product = deleteOneProductUseCase.deleteOneProduct(DeleteOneProductUseCase.DeleteOneProductCommand.of(id));

        if (product.isValid()){
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(product);
    }
}
