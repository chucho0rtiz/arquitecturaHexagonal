package co.com.ias.certification.backend.certification.adapters.out.persistence;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperationSuccess;
import co.com.ias.certification.backend.certification.application.port.out.productList.CreateProductListPort;
import co.com.ias.certification.backend.certification.application.port.out.productList.FindProductListPort;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class ProducListPersistenceAdapter implements CreateProductListPort, FindProductListPort {
    private final JdbcTemplate jdbcTemplate;

    public final RowMapper<ProductList> rowMapper =(resultSet, i)->{
        Long id = resultSet.getLong("id");
        Integer productid = resultSet.getInt("productid");
        Integer orderid = resultSet.getInt("orderid");
        Integer cantidadProduct = resultSet.getInt("cantidadProduct");
        return ProductList.of(id, productid, orderid, cantidadProduct);
    };

    @Override
    public ProductListOperation createProductList(ProductList productList) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("LIST_PRODUCTS")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("productid",productList.getProductid());
        parameters.put("orderid",productList.getOrderid());
        parameters.put("cantidadProduct",productList.getCantidadProduct());

        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        Long id =number.longValue();
        ProductList data = ProductList.of(
                id,
                productList.getProductid(),
                productList.getOrderid(),
                productList.getCantidadProduct()
        );

        return ProductListOperationSuccess.of(data);
    }

    @Override
    public List<ProductList> findProdutcList(Long id) {
        String SQL = "SELECT * FROM LIST_PRODUCTS WHERE orderid = ?";
        Object[] objects = {id};
        return jdbcTemplate.query(SQL, objects, rowMapper);
    }
}
