package ru.kibia.fileserver.rest.controller.file;

import org.springframework.stereotype.Component;

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


