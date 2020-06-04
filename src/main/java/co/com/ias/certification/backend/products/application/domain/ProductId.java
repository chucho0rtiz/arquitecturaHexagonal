package co.com.ias.certification.backend.products.application.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.LongSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class ProductId implements LongSerializable {
    Long value;

    public ProductId(Long value){
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value>0);

        this.value = value;
    }

    @Override
    public Long valueOf() {
        return value;
    }
}
