package co.com.ias.certification.backend.certification.application.domain.productList;

import co.com.ias.certification.backend.common.Preconditions;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductListOperationRequest {
    Integer productid;
    Integer orderid;
    Integer cantidadProduct;

    public ProductListOperationRequest(Integer productid, Integer orderid, Integer cantidadProduct) {
        Preconditions.checkNotNull(productid);
        Preconditions.checkNotNull(orderid);
        Preconditions.checkNotNull(cantidadProduct);

        this.productid = productid;
        this.orderid = orderid;
        this.cantidadProduct = cantidadProduct;
    }
}
