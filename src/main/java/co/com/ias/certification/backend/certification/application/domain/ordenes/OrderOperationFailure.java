package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions.OrderException;
import lombok.Value;

@Value(staticConstructor = "of")
public class OrderOperationFailure implements OrderOperation {
    OrderException exception;

    @Override
    public Order value() {
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
