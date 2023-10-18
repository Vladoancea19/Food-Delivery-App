package DbPersistence.Service.UserService;

import User.User;

import java.util.List;
import java.util.function.Predicate;

public interface UserService {
    User create(User user);

    List<User> read();

    User update(User user, Integer id);

    String delete(Integer id);

    User findById(Integer id);

    List<User> findAllBy(Predicate<User> predicate);
}
