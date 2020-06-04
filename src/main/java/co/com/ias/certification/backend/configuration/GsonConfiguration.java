package co.com.ias.certification.backend.configuration;

import co.com.ias.certification.backend.exceptions.ProductException;
import co.com.ias.certification.backend.products.application.domain.*;
import co.com.ias.certification.backend.serialization.BigdecimalAdapter;
import co.com.ias.certification.backend.serialization.IntegerAdapter;
import co.com.ias.certification.backend.serialization.LongAdapter;
import co.com.ias.certification.backend.serialization.StringAdapter;
import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Type;

@Configuration
public class GsonConfiguration {

    @Bean
    public Gson gson(){

        return new GsonBuilder()
                .registerTypeAdapter(ProductId.class, new LongAdapter<>(ProductId::of))
                .registerTypeAdapter(Name.class, new StringAdapter<>(Name::of))
                .registerTypeAdapter(Description.class, new StringAdapter<>(Description::of))
                .registerTypeAdapter(BasePrice.class, new BigdecimalAdapter<>(BasePrice::of))
                .registerTypeAdapter(TaxRate.class, new BigdecimalAdapter<>(TaxRate::of))
                .registerTypeAdapter(InventoryQuantity.class, new IntegerAdapter<>(InventoryQuantity::of))
                .registerTypeAdapter(ProductException.class, new JsonSerializer<ProductException>() {
                    @Override
                    public JsonElement serialize(ProductException e, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jsonObject = new JsonObject();
                        String message = e.getMessage();
                        JsonPrimitive errprValue = new JsonPrimitive(message);
                        jsonObject.add("error", errprValue);
                        return jsonObject;
                    }
                })
                .create();
    }
}
