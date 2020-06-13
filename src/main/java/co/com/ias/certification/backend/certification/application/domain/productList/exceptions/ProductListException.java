package co.com.ias.certification.backend.certification.application.domain.productList.exceptions;

public abstract class ProductListException extends RuntimeException {
    public ProductListException(String message){ super(message); }
}
