package ru.kibia.fileserver.facade.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.kibia.fileserver.mvc.model.user.bean.User;
import ru.kibia.fileserver.mvc.model.user.converter.UserConverter;
import ru.kibia.fileserver.repository.user.UserRepository;

/**
 * Created by kostin on 22.11.2016.
 */
@Component
public class UserFacade {

    @Autowired
    private UserRepository repository;

    @Transactional
    public void save(User user) {
        repository.save(UserConverter.beanToEntity(user));
    }

    @Transactional(readOnly = true)
    public User get(String login) {
        return UserConverter.entityToBean(repository.getByLogin(login));
    }

    public void flush() {
        repository.flush();
    }
}
