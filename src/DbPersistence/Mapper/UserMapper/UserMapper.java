package DbPersistence.Mapper.UserMapper;

import User.User;

public interface UserMapper {
    User entityToModel(User user);
    User modelToEntity(User user);
}
