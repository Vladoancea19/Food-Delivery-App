package DbPersistence.Dao.UserDao;

import DbPersistence.DbConnectionManagement;
import User.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDaoImp implements UserDao {
    private static final DbConnectionManagement CONNECTION_MANAGEMENT = DbConnectionManagement.getInstance();
    private static final Connection connection = CONNECTION_MANAGEMENT.getConnection();

    @Override
    public User create(User entity) {
        final String SQL = "insert into users (user_id, user_name_surname, user_email, user_password, user_phone_number) value (?, ?, ?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, entity.getUserId());
            preparedStatement.setString(2, entity.getUserNameSurname());
            preparedStatement.setString(3, entity.getUserEmail());
            preparedStatement.setString(4, entity.getUserPassword());
            preparedStatement.setString(5, entity.getUserPhoneNumber());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return entity;
    }

    @Override
    public List<User> read() {
        List<User> userEntityList = new ArrayList<>();
        final String SQL = "SELECT * FROM users;";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    int userId = resultSet.getInt(1);
                    String userNameSurname = resultSet.getString(2);
                    String userEmail = resultSet.getString(3);
                    String userPassword = resultSet.getString(4);
                    String userPhoneNumber = resultSet.getString(5);
                    User userEntity = new User(userId, userNameSurname, userEmail, userPassword, userPhoneNumber);
                    userEntityList.add(userEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return userEntityList;
    }

    @Override
    public Optional<User> update(User entity, Integer id) {
        final String SQL = "UPDATE users SET user_password = ? WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, entity.getUserPassword());
            preparedStatement.setInt(2, id);
            int n = preparedStatement.executeUpdate();
            if (n > 0) {
                return Optional.of(entity);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public void delete(Integer id) {
        final String SQL = "DELETE FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<User> findById(Integer id) {
        final String SQL = "SELECT * FROM users WHERE user_id = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    String userNameSurname = resultSet.getString(1);
                    String userEmail = resultSet.getString(2);
                    String userPassword = resultSet.getString(3);
                    String userPhoneNumber = resultSet.getString(4);
                    int userId = resultSet.getInt(5);
                    User userEntity = new User(userId, userNameSurname, userEmail, userPassword, userPhoneNumber);
                    return Optional.of(userEntity);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllBy(Predicate<User> predicate) {
        List<User> userEntityList = read();
        userEntityList = userEntityList.stream().filter(predicate).collect(Collectors.toList());
        return userEntityList;
    }
}
