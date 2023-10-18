package DbPersistence.Mapper.UserMapper;

import User.User;

public class UserEntityMapper implements UserMapper{
    @Override
    public User entityToModel(User user) {
        return null;
    }

    @Override
    public User modelToEntity(User entity) {
        User user = new User();
        user.setUserId(entity.getUserId());
        user.setUserNameSurname(entity.getUserNameSurname());
        user.setUserEmail(entity.getUserEmail());
        user.setUserPassword(entity.getUserPassword());
        user.setUserPhoneNumber(entity.getUserPhoneNumber());
        return user;
    }
}
