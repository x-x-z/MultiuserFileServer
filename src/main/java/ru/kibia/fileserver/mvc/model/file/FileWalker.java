package ru.kibia.fileserver.mvc.model.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.kibia.fileserver.service.ConfigLoaderService;

import javax.inject.Singleton;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;

/**
 * Created by kostin on 08.11.2016.
 */

@Component
public class FileWalker {

    public FileWalker() {
    }

    public File[] getFiles(String directory) throws FileNotFoundException {
        return getFiles(directory, null);
    }

    public File[] getFiles(String directory, FileFilter filter) throws FileNotFoundException {
        File dir = new File(directory);

        if(!dir.exists()) {
            throw new FileNotFoundException(directory + " is not exists");
        }

        return dir.listFiles(filter);
    }
}


