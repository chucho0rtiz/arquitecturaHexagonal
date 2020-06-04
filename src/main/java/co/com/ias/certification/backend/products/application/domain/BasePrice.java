package co.com.ias.certification.backend.products.application.domain;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.BigdecimalSerializable;
import lombok.Value;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class BasePrice implements BigdecimalSerializable {
    BigDecimal value;

    public BasePrice(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.intValue()>0);

        this.value = value;
    }

    @Override
    public BigDecimal valeuOf() {
        return value;
    }
}
