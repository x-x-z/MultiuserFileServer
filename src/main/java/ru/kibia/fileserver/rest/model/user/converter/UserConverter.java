package ru.kibia.fileserver.rest.model.user.converter;

import ru.kibia.fileserver.rest.model.user.bean.Role;
import ru.kibia.fileserver.rest.model.user.bean.User;
import ru.kibia.fileserver.rest.model.user.entity.UserEntity;

/**
 * Created by kostin on 22.11.2016.
 */
public class UserConverter {

    public static User entityToBean(UserEntity entity) {
        if(entity != null) {
            return new User.Builder(entity.getId(), entity.getLogin(), entity.getPassword())
                    .setFirstName(entity.getFirstName())
                    .setMiddleName(entity.getMiddleName())
                    .setLastName(entity.getLastName())
                    .setRole(Role.valueOf(entity.getRole()))
                    .setLoginCount(entity.getLoginCount())
                    .setTrafficLimit(entity.getTrafficLimit())
                    .build();
        }

        return null;
    }

    public static UserEntity beanToEntity(User user) {
        if(user != null) {
            return new UserEntity(
                    user.getId(),
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getMiddleName(),
                    user.getLastName(),
                    user.getRole().toString(),
                    user.getLoginCount(),
                    user.getTrafficLimit()
            );
        }

        return null;
    }
}
