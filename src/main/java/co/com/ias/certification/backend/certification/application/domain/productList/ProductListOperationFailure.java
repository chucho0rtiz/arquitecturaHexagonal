package co.com.ias.certification.backend.certification.application.domain.productList;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Order;
import co.com.ias.certification.backend.certification.application.domain.ordenes.OrderOperation;
import co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions.OrderException;
import co.com.ias.certification.backend.certification.application.domain.productList.exceptions.ProductListException;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductListOperationFailure implements ProductListOperation {
    ProductListException exception;

    @Override
    public ProductList value() {
        return null;
    }

    @Override
    public String errorMessage() {
        return String.format("Ha ocurriodo un error: %s " + exception.getMessage());
    }

    @Override
    public Boolean isValid() {
        return false;
    }
}
