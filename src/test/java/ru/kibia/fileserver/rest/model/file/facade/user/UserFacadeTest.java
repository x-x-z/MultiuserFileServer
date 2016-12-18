package ru.kibia.fileserver.rest.model.file.facade.user;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.rest.model.user.bean.Role;
import ru.kibia.fileserver.rest.model.user.bean.User;

/**
 * Created by kostin on 22.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;

    @Test
    @Transactional
    public void testInsertAndGet() throws Exception {
        saveSingleUser(-91);
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception {
        int id = -74;
        String login = "test=test-0x1c2QLf"+id;

        saveSingleUser(id);
        User user = userFacade.get(login);
        long uid = user.getId();

        Assert.assertNotNull(user);

        user.setPassword("test-pass");
        userFacade.save(user);
        userFacade.flush();

        user = userFacade.get(login);

        Assert.assertEquals("test-pass", user.getPassword());
        Assert.assertEquals(uid, user.getId());
    }

    @Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
    @Transactional
    public void testDublicateInsert() throws Exception {
        saveSingleUser(-45);
        saveSingleUser(-45);
    }

    private void saveSingleUser(int id) {
        String login = "test=test-0x1c2QLf"+id;

        userFacade.save(createUser(id));
        userFacade.flush();
        User user = userFacade.get(login);

        Assert.assertNotNull(user);
        Assert.assertEquals(login, user.getLogin());
    }

    private User createUser(int id) {
        return new User.Builder("test=test-0x1c2QLf" + id, "123")
                .setFirstName("Вася")
                .setMiddleName("Давыдович")
                .setLastName("Пупкин")
                .setRole(Role.USER)
                .build();
    }

}
