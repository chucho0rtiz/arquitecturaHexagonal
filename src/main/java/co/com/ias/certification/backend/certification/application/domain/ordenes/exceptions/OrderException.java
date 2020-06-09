package co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions;

public abstract class OrderException extends RuntimeException {
    public OrderException(String message){ super(message); }
}
