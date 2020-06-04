package co.com.ias.certification.backend.products.application.domain;

public interface ProductOperation {
    Product value();

    String errorMessage();

    Boolean isValid();
}
