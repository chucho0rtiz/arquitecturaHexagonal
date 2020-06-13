package co.com.ias.certification.backend.certification.application.domain.productList;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductListOperationSuccess implements ProductListOperation {
    ProductList productList;

    @Override
    public ProductList value() {
        return productList;
    }

    @Override
    public String errorMessage() {
        return "La lista de productos se registro exitosamente!!";
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
