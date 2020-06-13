package co.com.ias.certification.backend.certification.application.domain.productList;

public interface ProductListOperation {
    ProductList value();

    String errorMessage();

    Boolean isValid();
}
