package co.com.ias.certification.backend.certification.adapters.out.persistence;

import co.com.ias.certification.backend.certification.application.domain.ordenes.*;
import co.com.ias.certification.backend.certification.application.domain.ordenes.exceptions.OrderDoesNotExists;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductList;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperation;
import co.com.ias.certification.backend.certification.application.domain.productList.ProductListOperationSuccess;
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
public class OrderPersistenceAdapter implements CreateOrderPort, DeleteOneOrderPort, UpdateOrderPort, FindAllOrderPort, FindOneOrderPort {

    private final JdbcTemplate jdbcTemplate;

    // solo se esta usando en los metodos que realizan busqueda
    public final RowMapper<Order> rowMapper =(resultSet, i)->{
        Long id = resultSet.getLong("id");
        Cliente cliente = Cliente.of(resultSet.getString("cliente"));
        Descuento descuento = Descuento.of(resultSet.getBigDecimal("descuento"));
        Total total = Total.of(resultSet.getBigDecimal("total"));
        Estado estado = Estado.of(resultSet.getString("estado"));
        return Order.of(id, cliente, descuento, total, estado);
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

    // Este metodo elimina tanto la orden como los productos
    @Override
    public OrderOperation deleteOneOrder(Long id) {
        String SQLOrder = "DELETE FROM LISTORDERS WHERE id = ?";
        String SQLProductList = "DELETE FROM LIST_PRODUCTS WHERE orderid = ?";
        Object[] objects = {id};
        Order order = findOneOrder(id).value();

        Integer getValueProductList = jdbcTemplate.update(SQLProductList, objects);
        Integer getValue = jdbcTemplate.update(SQLOrder, objects);
        System.out.println("getValue = " + getValue +" : "+ getValueProductList);
        if (getValue == 1 && getValueProductList >= 1){
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
