package ru.kibia.fileserver.mvc.model.file.facade.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kibia.fileserver.facade.file.SharedFileFacade;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.mvc.model.file.bean.SharedFile;
import ru.kibia.fileserver.mvc.model.user.bean.Role;
import ru.kibia.fileserver.mvc.model.user.bean.User;

import java.io.File;
import java.util.*;

/**
 * Created by D.Kostin on 26.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SharedFileTest {

    @Autowired
    private SharedFileFacade fileFacade;

    @Autowired
    private UserFacade userFacade;

    @Test
    public void testSave() {
        User user = createUser(-63);
        User user2 = createUser(-62);
        userFacade.save(user);
        userFacade.save(user2);
        user = userFacade.get(user.getLogin());
        user2 = userFacade.get(user2.getLogin());

        Set<Long> userIds = new HashSet<>(Arrays.asList(user2.getId()));
        for(int i=1; i<11; i++) {
            fileFacade.save(createFile(i + ".txt", user.getId(), userIds));
        }
        fileFacade.flush();

        List<SharedFile> fileList = fileFacade.getAllUserFiles(user.getId());
        Assert.assertEquals("Wrong count of files", 10, fileList.size());
    }

    private User createUser(int id) {
        return new User.Builder("test=test-0x1c2QLf" + id, "123")
                .setFirstName("Вася")
                .setMiddleName("Давыдович")
                .setLastName("Пупкин")
                .setRole(Role.USER)
                .build();
    }

    private SharedFile createFile(String filename, long userId, Set<Long> foreignUsersId) {
        return new SharedFile(new File(filename), userId, foreignUsersId);
    }
}
