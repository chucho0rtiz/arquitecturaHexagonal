package co.com.ias.certification.backend.certification.application.domain.ordenes;

import lombok.Value;

@Value(staticConstructor = "of")
public class OrderOperationSuccess implements OrderOperation {
    Order order;

    @Override
    public Order value() {
        return order;
    }

    @Override
    public String errorMessage() {
        return "El producto se registro exitosamente!!";
    }

    @Override
    public Boolean isValid() {
        return true;
    }
}
