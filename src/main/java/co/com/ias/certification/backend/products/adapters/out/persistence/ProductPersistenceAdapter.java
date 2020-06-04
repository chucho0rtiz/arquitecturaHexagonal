package co.com.ias.certification.backend.products.adapters.out.persistence;

import co.com.ias.certification.backend.exceptions.ProductDoesNotExists;
import co.com.ias.certification.backend.products.application.domain.*;
import co.com.ias.certification.backend.products.application.port.out.*;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProductPersistenceAdapter implements CreateProductPort, FindAllProductPort, FindOneProductPort, DeleteOneProductPort, UpdateProductPort {

    private final JdbcTemplate jdbcTemplate;
//    private final SimpleJdbcInsert simpleJdbcInsert;

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
        String SQL = "INSERT INTO PRODUCTS (name, description, baseprice, taxrate, productstatus, inventoryquantity) VALUES (?,?,?,?,?,?)";
        Object[] objects = {product.getName().getValue(), product.getDescription().getValue(), product.getBasePrice().getValue(),
                product.getTaxRate().getValue(), product.getProductStatus().toString(), product.getInventoryQuantity().getValue()};

        Integer getValue = jdbcTemplate.update(SQL, objects);
        if (getValue == 1) {
            ProductId productId = ProductId.of(getValue.longValue());
            Product producto = Product.of(
                    productId, product.getName(),
                    product.getDescription(), product.getBasePrice(),
                    product.getTaxRate(), product.getProductStatus(),
                    product.getInventoryQuantity()
            );
            return ProductOperationSuccess.of(producto);
        } else {
            return null;
        }
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
        System.out.println("id = " + product);
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
