package co.com.ias.certification.backend.certification.application.domain.ordenes;

import co.com.ias.certification.backend.common.Preconditions;
import co.com.ias.certification.backend.serialization.BigdecimalSerializable;
import co.com.ias.certification.backend.serialization.LongSerializable;
import lombok.Value;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

@Value(staticConstructor = "of")
public class Descuento implements BigdecimalSerializable {
    BigDecimal value;

    public Descuento(BigDecimal value) {
        Preconditions.checkNotNull(value);
        Preconditions.checkArgument(value.intValue()>0);

        this.value = value;
    }

    @Override
    public BigDecimal valeuOf() {
        return value;
    }
}
