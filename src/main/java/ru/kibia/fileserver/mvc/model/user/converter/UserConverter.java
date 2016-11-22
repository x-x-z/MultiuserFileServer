package ru.kibia.fileserver.mvc.model.user.converter;

import ru.kibia.fileserver.mvc.model.user.bean.Role;
import ru.kibia.fileserver.mvc.model.user.bean.User;
import ru.kibia.fileserver.mvc.model.user.entity.UserEntity;

/**
 * Created by kostin on 22.11.2016.
 */
public class UserConverter {

    public static User entityToBean(UserEntity entity) {
        return new User.Builder(entity.getId(), entity.getLogin(), entity.getPassword())
                .setFirstName(entity.getFirstName())
                .setMiddleName(entity.getMiddleName())
                .setLastName(entity.getLastName())
                .setRole(Role.valueOf(entity.getRole()))
                .setLoginCount(entity.getLoginCount())
                .setTrafficLimit(entity.getTrafficLimit())
                .build();
    }

    public static UserEntity beanToEntity(User user) {
        UserEntity entity = new UserEntity(
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
        System.out.println("E0 ---> " + entity.getId());

        return entity;
    }

}
