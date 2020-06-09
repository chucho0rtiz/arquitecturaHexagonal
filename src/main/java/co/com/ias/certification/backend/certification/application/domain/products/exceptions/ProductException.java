package co.com.ias.certification.backend.certification.application.domain.products.exceptions;

public abstract class ProductException extends RuntimeException {
    public ProductException(String message){ super(message); }
}
