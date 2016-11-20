package ru.kibia.fileserver.mvc.model.file;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kibia.fileserver.service.ConfigLoaderService;

import java.io.File;

/**
 * Created by kostin on 08.11.2016.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileWalkerTest {

    @Autowired
    private FileWalker fileWalker;

    @Autowired
    private ConfigLoaderService configLoader;

    @Test
    public void testWalk() throws Exception {

        FileWalkerFilter filter1 = new FileWalkerFilter(configLoader.getFileVisibleTypes());
        FileWalkerFilter filter2 = new FileWalkerFilter(new String[]{"xml"});

        File[] files = fileWalker.getFiles(".\\", filter1);
        Assert.assertEquals(0, files.length);

        files = fileWalker.getFiles(".\\", filter2);
        Assert.assertEquals(1, files.length);
    }
    
}
