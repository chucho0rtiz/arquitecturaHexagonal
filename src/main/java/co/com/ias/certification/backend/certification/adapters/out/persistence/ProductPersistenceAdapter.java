package co.com.ias.certification.backend.certification.adapters.out.persistence;

import co.com.ias.certification.backend.certification.application.domain.products.*;
import co.com.ias.certification.backend.certification.application.port.out.products.*;
import co.com.ias.certification.backend.certification.application.domain.products.exceptions.ProductDoesNotExists;
import io.vavr.control.Try;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements CreateProductPort, FindAllProductPort, FindOneProductPort, DeleteOneProductPort, UpdateProductPort {

    private final JdbcTemplate jdbcTemplate;

    public final RowMapper<Product> rowMapper =(resultSet, i)->{
        ProductId id = ProductId.of(resultSet.getLong("productid"));
        Name name = Name.of(resultSet.getString("name"));
        Description description = Description.of(resultSet.getString("description"));
        BasePrice basePrice = BasePrice.of(resultSet.getBigDecimal("baseprice"));
        TaxRate taxRate = TaxRate.of(resultSet.getBigDecimal("taxrate"));
        ProductStatus productStatus = ProductStatus.valueOf(resultSet.getString("productstatus"));
        InventoryQuantity inventoryQuantity = InventoryQuantity.of(resultSet.getInt("inventoryquantity"));
        return Product.of(id, name, description, basePrice, taxRate, productStatus, inventoryQuantity);
    };

    @Override
    public ProductOperation createProduct(ProductOperationRequest product) {

//        return Try.of(() -> {
            SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                            .withTableName("PRODUCTS")
                            .usingGeneratedKeyColumns("productId");


            Map<String, Object> parameters = new HashMap<>();
            parameters.put("name",product.getName().getValue());
            parameters.put("description",product.getDescription().getValue());
            parameters.put("basePrice",product.getBasePrice().getValue());
            parameters.put("taxRate",product.getTaxRate().getValue());
            parameters.put("productStatus",product.getProductStatus());
            parameters.put("inventoryQuantity",product.getInventoryQuantity().getValue());
            Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
            ProductId id = ProductId.of(number.longValue());

            Product producto = Product.of(
                    id,
                    product.getName(),
                    product.getDescription(),
                    product.getBasePrice(),
                    product.getTaxRate(),
                    product.getProductStatus(),
                    product.getInventoryQuantity()
            );
            return ProductOperationSuccess.of(producto);
//        });
//
    }

    @Override
    public List<Product> findAllProduct() {
        String SQL = "SELECT * FROM PRODUCTS";
        return jdbcTemplate.query(SQL, rowMapper);
    }

    @Override
    public ProductOperation findOneProduct(Long id) {
        String SQL = "SELECT * FROM PRODUCTS WHERE productid = ?";
        Object[] objects = {id};
        try{
            Product product = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return ProductOperationSuccess.of(product);
        }catch (EmptyResultDataAccessException e){
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }

    @Override
    public ProductOperation deleteOneProduct(Long id) {
        String SQL = "DELETE FROM PRODUCTS WHERE productid = ?";
        Object[] objects = {id};
        Product product = findOneProduct(id).value();

        Integer getValue = jdbcTemplate.update(SQL, objects);
        if (getValue == 1){
            return ProductOperationSuccess.of(product);
        }else{
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }

    @Override
    public ProductOperation updateProduct(Long id, ProductOperationRequest product) {
        String SQL = "UPDATE PRODUCTS SET name=?, description=?, baseprice=?, taxrate=?, productstatus=?, inventoryquantity=? WHERE productid = ?";
        Object[] objects = { product.getName().getValue(), product.getDescription().getValue(), product.getBasePrice().getValue(),
                product.getTaxRate().getValue(), product.getProductStatus().toString(), product.getInventoryQuantity().getValue(), id };

        Integer getValue = jdbcTemplate.update(SQL, objects);

        if(getValue == 1){
            ProductId productId = ProductId.of(id);
            Product producto = Product.of(
                    productId,
                    product.getName(),
                    product.getDescription(),
                    product.getBasePrice(),
                    product.getTaxRate(),
                    product.getProductStatus(),
                    product.getInventoryQuantity()
            );
            return ProductOperationSuccess.of(producto);
        }else{
            return ProductOperationFailure.of(ProductDoesNotExists.of(id));
        }
    }
}
