package DbPersistence.Service.UserService;

import DbPersistence.Dao.UserDao.UserDao;
import DbPersistence.Dao.UserDao.UserDaoImp;
import DbPersistence.Mapper.UserMapper.UserEntityMapper;
import DbPersistence.Mapper.UserMapper.UserMapper;
import User.User;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserServiceImp implements UserService{
    private final UserDao userDao = new UserDaoImp();
    private final UserMapper userMapper = new UserEntityMapper();

    @Override
    public User create(User entity) {
        User userEntity = userMapper.modelToEntity(entity);
        User savedUserEntity = this.userDao.create(userEntity);
        return entity;
    }

    @Override
    public List<User> read() {
        List<User> userEntityList = this.userDao.read();
        return userEntityList.stream().map(userMapper::modelToEntity).collect(Collectors.toList());
    }

    @Override
    public User update(User user, Integer id) {
        Optional<User> optionalProductEntity = this.userDao.update(userMapper.modelToEntity(user), id);
        if (optionalProductEntity.isEmpty()) {
            throw new RuntimeException("User with id: " + id + " was not found!");
        }
        return userMapper.modelToEntity(optionalProductEntity.get());
    }

    @Override
    public String delete(Integer id) {
        this.userDao.delete(id);
        return "User has been deleted successfully!";
    }

    @Override
    public User findById(Integer id) {
        Optional<User> entityOptional = this.userDao.findById(id);
        if (entityOptional.isEmpty()) {
            throw new RuntimeException("User with id: " + id + " was not found!");
        }

        User userEntity = entityOptional.get();
        return userMapper.modelToEntity(userEntity);
    }

    @Override
    public List<User> findAllBy(Predicate<User> predicate) {
        return this.userDao.findAllBy(predicate).stream().map(userMapper::modelToEntity).collect(Collectors.toList());
    }
}
