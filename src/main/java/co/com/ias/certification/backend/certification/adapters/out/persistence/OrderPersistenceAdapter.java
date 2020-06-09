package co.com.ias.certification.backend.certification.adapters.out.persistence;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions.OrderDoesNotExists;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.port.out.ordenes.*;
import co.com.ias.certification.backend.certification.application.port.out.productList.CreateProductListPort;
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
public class OrderPersistenceAdapter implements CreateOrderPort, FindAllOrderPort, FindOneOrderPort, DeleteOneOrderPort, UpdateOrderPort, CreateProductListPort {

    private final JdbcTemplate jdbcTemplate;

    // solo se esta usando en los metodos que realizan busqueda
    public final RowMapper<Order> rowMapper =(resultSet, i)->{
        Long id = resultSet.getLong("id");
        Cliente cliente = Cliente.of(resultSet.getString("cliente"));
        Descuento descuento = Descuento.of(resultSet.getBigDecimal("descuento"));
        Total total = Total.of(resultSet.getBigDecimal("total"));
        Estado estado = Estado.of(resultSet.getString("estado"));
        return Order.of(id,  cliente, descuento, total, estado);
    };

    @Override
    public OrderOperation createOrder(OrderOperationRequest order) {
        SimpleJdbcInsert simpleJdbcInsert = new SimpleJdbcInsert(jdbcTemplate)
                .withTableName("LISTORDERS")
                .usingGeneratedKeyColumns("id");

        Map<String, Object> parameters = new HashMap<>();
        parameters.put("cliente",order.getCliente().getValue());
        parameters.put("descuento",order.getDescuento().getValue());
        parameters.put("total",order.getTotal().getValue());
        parameters.put("estado",order.getEstado().getValue());

        Number number = simpleJdbcInsert.executeAndReturnKey(parameters);
        Long id =number.longValue();
        Order data = Order.of(
            id,
            order.getCliente(),
            order.getDescuento(),
            order.getTotal(),
            order.getEstado()
        );
        return OrderOperationSuccess.of(data);
    }
    @Override
    public ProductList createProductList(ProductList productList) {
        String SQL = "INSERT INTO LIST_PRODUCTS (productid, orderid, cantidadProduct) VALUES (?,?,?)";
        Object[] objects = {
                productList.getProductid(),
                productList.getOrderid(),
                productList.getCantidadProduct()
        };

        Integer getValue = jdbcTemplate.update(SQL, objects);
        if (getValue == 1) {
            Long id = getValue.longValue();
            ProductList data = ProductList.of(
                    id,
                    productList.getProductid(),
                    productList.getOrderid(),
                    productList.getCantidadProduct()
            );
            return data;
        } else {
            System.out.println("ENTRE EN EL PINCHURRIENTO ERROR");
            return null;
        }
    }


    @Override
    public List<Order> findAllOrders() {
        String SQL = "SELECT * FROM LISTORDERS";
        return jdbcTemplate.query(SQL, rowMapper);
    }

    @Override
    public OrderOperation findOneOrder(Long id) {
        String SQL = "SELECT * FROM LISTORDERS WHERE id = ?";
        Object[] objects = {id};
        try{
            Order order = jdbcTemplate.queryForObject(SQL, objects, rowMapper);
            return OrderOperationSuccess.of(order);
        }catch (EmptyResultDataAccessException e){
            return OrderOperationFailure.of(OrderDoesNotExists.of(id));
        }
    }

    @Override
    public OrderOperation deleteOneOrder(Long id) {
        String SQL = "DELETE FROM LISTORDERS WHERE id = ?";
        Object[] objects = {id};
        Order order = findOneOrder(id).value();

        Integer getValue = jdbcTemplate.update(SQL, objects);
        if (getValue == 1){
            return OrderOperationSuccess.of(order);
        }else{
            return OrderOperationFailure.of(OrderDoesNotExists.of(id));
        }
    }

    @Override
    public OrderOperation updateOrder(Long id, UpdateOrderRequest estado) {
        String SQL = "UPDATE LISTORDERS SET estado=? WHERE id = ?";
        Object[] objects = {estado.getEstado().getValue(), id };
//        String SQL = "UPDATE PRODUCTS SET cliente=?, descuento=?, total=?, estado=? WHERE id = ?";
//        Object[] objects = { order.getCliente().getValue(), order.getDescuento().getValue(),
//            order.getTotal().getValue(), order.getEstado().getValue(), id };


        Integer getValue = jdbcTemplate.update(SQL, objects);
        if(getValue == 1){
            OrderOperation dataOrder = findOneOrder(id);
            return dataOrder;
        }else{
            return OrderOperationFailure.of(OrderDoesNotExists.of(id));
        }
    }
}
