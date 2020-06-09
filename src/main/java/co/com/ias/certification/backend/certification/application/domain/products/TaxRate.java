package co.com.ias.certification.backend.certification.application.domain.products;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.BigdecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class TaxRate implements BigdecimalSerializable {
    BigDecimal value;

    public TaxRate(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.intValue()<=1 && value.intValue()>=0);

        this.value = value;
    }

    @Override
    public BigDecimal valeuOf() {
        return value;
    }
}
