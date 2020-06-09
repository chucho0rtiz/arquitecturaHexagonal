package co.com.ias.certification.backend.certification.application.domain.products;

public interface ProductOperation {
    Product value();

    String errorMessage();

    Boolean isValid();
}
