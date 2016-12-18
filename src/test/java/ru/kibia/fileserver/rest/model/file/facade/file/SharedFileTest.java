package ru.kibia.fileserver.rest.model.file.facade.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import ru.kibia.fileserver.facade.file.SharedFileFacade;
import ru.kibia.fileserver.facade.user.UserFacade;
import ru.kibia.fileserver.rest.model.file.bean.SharedAccess;
import ru.kibia.fileserver.rest.model.file.bean.SharedAccessRight;
import ru.kibia.fileserver.rest.model.file.bean.SharedFile;
import ru.kibia.fileserver.rest.model.user.bean.Role;
import ru.kibia.fileserver.rest.model.user.bean.User;
import java.io.File;
import java.util.*;

/**
 * Created by D.Kostin on 26.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SharedFileTest {

    @Autowired
    private SharedFileFacade fileFacade;

    @Autowired
    private UserFacade userFacade;

    @Test
    public void testSaveAndGet() {
        User user = createUser(-63);
        User user2 = createUser(-62);
        userFacade.save(user);
        userFacade.save(user2);
        user = userFacade.get(user.getLogin());
        user2 = userFacade.get(user2.getLogin());

        Set<SharedAccess> accesses = new HashSet<>();
        Set<SharedAccess> emptyAccesses = new HashSet<>();
        accesses.add(new SharedAccess(user2.getId(), SharedAccessRight.READ));

        for(int i=1; i<11; i++) {
            if(i < 4) {
                fileFacade.save(createFile(i + ".txt", user.getId(), accesses));
            } else {
                fileFacade.save(createFile(i + ".txt", user.getId(), emptyAccesses));
            }
        }
        fileFacade.flush();

        List<SharedFile> fileList1 = fileFacade.getAllUserFiles(user.getId());
        List<SharedFile> fileList2 = fileFacade.getAllAvailableFiles(user2.getId());

        Assert.assertEquals("Wrong count of files", 10, fileList1.size());
        Assert.assertEquals("Wrong count of files", 3, fileList2.size());
    }

    private User createUser(int id) {
        return new User.Builder("test=test-0x1c2QLf" + id, "123")
                .setFirstName("Вася")
                .setMiddleName("Давыдович")
                .setLastName("Пупкин")
                .setRole(Role.USER)
                .build();
    }

    private SharedFile createFile(String filename, long userId, Set<SharedAccess> accesses) {
        return new SharedFile(new File(filename), userId, accesses);
    }
}
