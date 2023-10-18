package DbPersistence.Dao.DeliveryOrderDao;

import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.DeliveryOrderEntity.DeliveryOrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class DeliveryOrderDaoImp implements DeliveryOrderDao {

    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();

    @Override
    public DeliveryOrderEntity create(DeliveryOrderEntity entity) {
        final String SQL = "insert into delivery_orders (delivery_order_id, products_ordered, products_price, payment_method, delivery_price, delivery_address) value (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, entity.getDeliveryOrderId());
            preparedStatement.setString(2, entity.getProductsOrdered());
            preparedStatement.setFloat(3, entity.getProductsPrice());
            preparedStatement.setString(4, entity.getPaymentMethod());
            preparedStatement.setInt(5, entity.getDeliveryPrice());
            preparedStatement.setString(6, entity.getDeliveryAddress());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<DeliveryOrderEntity> read() {
        return null;
    }

    @Override
    public Optional<DeliveryOrderEntity> update(DeliveryOrderEntity entity, Integer integer) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer integer) {
    }

    @Override
    public Optional<DeliveryOrderEntity> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<DeliveryOrderEntity> findAllBy(Predicate<DeliveryOrderEntity> predicate) {
        return null;
    }
}
