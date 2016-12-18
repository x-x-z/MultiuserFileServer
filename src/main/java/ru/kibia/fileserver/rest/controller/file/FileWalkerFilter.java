package ru.kibia.fileserver.rest.controller.file;

import java.io.File;
import java.io.FileFilter;

/**
 * Created by kostin on 10.11.2016.
 */
public class FileWalkerFilter implements FileFilter {

    private String fileExt[];

    public FileWalkerFilter(String fileExt[]) {
        this.fileExt = fileExt;
    }

    @Override
    public boolean accept(File pathname) {
        if(pathname.isDirectory())
            return true;

        for(String ext : fileExt) {
            if(pathname.getName().endsWith("." + ext)) {
                return true;
            }
        }

        return false;
    }

}
