package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.certification.application.domain.products.Product;

public interface OrderOperation {
    Order value();

    String errorMessage();

    Boolean isValid();
}
