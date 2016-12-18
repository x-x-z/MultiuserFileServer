package ru.kibia.fileserver.rest.controller.file;

import java.io.File;
import java.nio.file.Path;
import java.util.List;

/**
 * Created by kostin on 14.11.2016.
 */
public class FileWalkerShareFilter extends FileWalkerFilter {

    private List<String> sharePaths;

    public FileWalkerShareFilter(String[] fileExt, List<String> sharePaths) {
        super(fileExt);
        this.sharePaths = sharePaths;
    }

    @Override
    public boolean accept(File pathname) {
        Path fPath = pathname.toPath();

        for(String sPath : sharePaths) {
            if(fPath.startsWith(sPath) && super.accept(pathname)){
                return true;
            }
        }

        return false;
    }
}
