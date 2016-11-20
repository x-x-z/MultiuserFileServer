package ru.kibia.fileserver.mvc.model.file;

import java.io.File;
import java.nio.file.Path;

/**
 * Created by kostin on 14.11.2016.
 */
public class FileWalkerShareFilter extends FileWalkerFilter {

    private String[] sharePath;

    public FileWalkerShareFilter(String basePath, String[] fileExt, String[] sharePath) {
        super(fileExt);
        this.sharePath = sharePath;
    }

    @Override
    public boolean accept(File pathname) {
        Path fPath = pathname.toPath();

        for(String sPath : sharePath) {
            if(fPath.startsWith(sPath) && super.accept(pathname)){
                return true;
            }
        }

        return false;
    }
}
