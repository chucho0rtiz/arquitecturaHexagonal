package co.com.ias.certification.backend.configuration;

import co.com.ias.certification.backend.certification.application.domain.ordenes.Cliente;
import co.com.ias.certification.backend.certification.application.domain.ordenes.Descuento;
import co.com.ias.certification.backend.certification.application.domain.ordenes.Estado;
import co.com.ias.certification.backend.certification.application.domain.ordenes.Total;
import co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions.OrderException;
import co.com.ias.certification.backend.certification.application.domain.products.*;
import co.com.ias.certification.backend.certification.application.domain.products.exceptions.ProductException;
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
                // products
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
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                // orders
                .registerTypeAdapter(Cliente.class, new StringAdapter<>(Cliente::of))
                .registerTypeAdapter(Descuento.class, new BigdecimalAdapter<>(Descuento::of))
                .registerTypeAdapter(Total.class, new BigdecimalAdapter<>(Total::of))
                .registerTypeAdapter(Estado.class, new StringAdapter<>(Estado::of))
                .registerTypeAdapter(OrderException.class, new JsonSerializer<OrderException>() {
                    @Override
                    public JsonElement serialize(OrderException e, Type type, JsonSerializationContext jsonSerializationContext) {
                        JsonObject jsonObject = new JsonObject();
                        String message = e.getMessage();
                        JsonPrimitive errorValue = new JsonPrimitive(message);
                        jsonObject.add("error", errorValue);
                        return jsonObject;
                    }
                })
                .create();
    }
}
