package ru.kibia.fileserver.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kibia.fileserver.rest.model.user.entity.UserEntity;

/**
 * Created by D.Kostin on 19.11.2016.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity getByLogin(String login);

}
