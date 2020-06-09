package co.com.ias.certification.backend.certification.application.domain.products;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.IntegerSerializable;
import lombok.Value;

@Value(staticConstructor = "of")
public class InventoryQuantity implements IntegerSerializable {
    Integer value;

    public InventoryQuantity(Integer value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value>0);

        this.value = value;
    }

    @Override
    public Integer valueOf() {
        return value;
    }
}
