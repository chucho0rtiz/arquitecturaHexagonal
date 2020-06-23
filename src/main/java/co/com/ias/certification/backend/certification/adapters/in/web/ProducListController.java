package co.com.ias.certification.backend.certification.adapters.in.web;

import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.port.in.productList.CreateProductListUseCase;
import co.com.ias.certification.backend.certification.application.port.in.productList.FindProductListUseCase;
import lombok.RequiredArgsConstructor;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
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
    public ResponseEntity<?> createListProduct(@RequestBody ArrayList<ProductList> productList, Authentication authentication) {
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = createProductListUseCase.userHasPermission(authenticationToken);

        Map<String, Object> response = new HashMap<>();
        if (permission) {
            for (int i = 0; i < productList.size(); i++) {
                createProductListUseCase.createProductList(CreateProductListUseCase.CreateProductListCommand.of((ProductList) productList.toArray()[i]));
            }
            response.put("message: los productos fueron registrados correctamente.", productList);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        response.put("message: ", "");
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public List<ProductList> getProductList(@PathVariable Long id, Authentication authentication){
        KeycloakAuthenticationToken authenticationToken = (KeycloakAuthenticationToken) authentication;
        boolean permission = findProductListUseCase.userHasPermission(authenticationToken);
        if (permission) {
            return findProductListUseCase.findProductList(FindProductListUseCase.FindProducListCommand.of(id));
        }
        return null;
    }
}
