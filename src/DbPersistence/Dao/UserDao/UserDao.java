package DbPersistence.Dao.UserDao;

import DbPersistence.Crud;
import User.User;

import java.util.List;
import java.util.function.Predicate;

public interface UserDao extends Crud<User, Integer> {
    List<User> findAllBy(Predicate<User> predicate);
}
