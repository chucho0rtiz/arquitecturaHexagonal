package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.in.productList.FindProductListUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/productList")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProducListController {
    private final CreateProductListUseCase createProductListUseCase;
    private final FindProductListUseCase findProductListUseCase;

    @PostMapping
    public ResponseEntity<?> createListProduct(@RequestBody ArrayList<ProductList> productList) {
        Map<String, Object> response = new HashMap<>();
        for (int i = 0; i<productList.size(); i++){
            createProductListUseCase.createProductList(CreateProductListUseCase.CreateProductListCommand.of((ProductList) productList.toArray()[i]));
        }
        response.put("message: los productos fueron registrados correctamente.", productList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public List<ProductList> getProductList(@PathVariable Long id){
        return findProductListUseCase.findProductList(FindProductListUseCase.FindProducListCommand.of(id));
    }
}
