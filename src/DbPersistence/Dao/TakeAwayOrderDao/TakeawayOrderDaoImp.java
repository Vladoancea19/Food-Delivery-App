package DbPersistence.Dao.TakeAwayOrderDao;

import DbPersistence.DbConnectionManagement;
import DbPersistence.Entities.TakeawayOrderEntity.TakeawayOrderEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TakeawayOrderDaoImp implements TakeawayOrderDao {

    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();

    @Override
    public TakeawayOrderEntity create(TakeawayOrderEntity takeawayOrderEntity) {
        final String SQL = "insert into takeaway_orders (takeaway_order_id, products_ordered, price, payment_method, takeaway_discount) value (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, takeawayOrderEntity.getTakeawayOrderId());
            preparedStatement.setString(2, takeawayOrderEntity.getProductsOrdered());
            preparedStatement.setFloat(3, takeawayOrderEntity.getProductsPrice());
            preparedStatement.setString(4, takeawayOrderEntity.getPaymentMethod());
            preparedStatement.setInt(5, takeawayOrderEntity.getTakeawayDiscount());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return takeawayOrderEntity;
    }

    @Override
    public List<TakeawayOrderEntity> read() {
        List<TakeawayOrderEntity> takeawayOrderEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM takeaway_orders;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int takeawayOrderId = resultSet.getInt(1);
                    String productsOrdered = resultSet.getString(2);
                    Float productsPrice = resultSet.getFloat(3);
                    String paymentMethod = resultSet.getString(4);
                    int takeawayDiscount = resultSet.getInt(5);
                    TakeawayOrderEntity takeAwayOrderEntity = new TakeawayOrderEntity(takeawayOrderId, productsOrdered, productsPrice, paymentMethod, takeawayDiscount);
                    takeawayOrderEntityList.add(takeAwayOrderEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return takeawayOrderEntityList;
    }

    @Override
    public Optional<TakeawayOrderEntity> update(TakeawayOrderEntity entity, Integer id) {
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
    }

    @Override
    public Optional<TakeawayOrderEntity> findById(Integer id) {
        final String SQL = "SELECT * FROM takeaway_orders WHERE takeaway_order_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int takeAwayOrderId = resultSet.getInt(1);
                    String productsOrdered = resultSet.getString(2);
                    float productsPrice = resultSet.getFloat(3);
                    String paymentMethod = resultSet.getString(4);
                    int takeAwayDiscount = resultSet.getInt(5);
                    TakeawayOrderEntity takeAwayOrderEntity = new TakeawayOrderEntity(takeAwayOrderId, productsOrdered, productsPrice, paymentMethod, takeAwayDiscount);
                    return Optional.of(takeAwayOrderEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<TakeawayOrderEntity> findAllBy(Predicate<TakeawayOrderEntity> predicate) {
        List<TakeawayOrderEntity> takeawayOrderEntityList = read();
        takeawayOrderEntityList = takeawayOrderEntityList.stream().filter(predicate).collect(Collectors.toList());
        return takeawayOrderEntityList;
    }
}
