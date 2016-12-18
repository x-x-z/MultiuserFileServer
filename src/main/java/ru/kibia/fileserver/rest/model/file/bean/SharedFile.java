package ru.kibia.fileserver.rest.model.file.bean;

import java.io.File;
import java.util.Set;

/**
 * Created by kostin on 22.11.2016.
 */
public class SharedFile {

    private final long id;
    private final File file;
    private final long ownerUserId;
    private Set<SharedAccess> accesses;

    public SharedFile(long id, File file, long ownerUserId, Set<SharedAccess> accesses) {
        this.id = id;
        this.file = file;
        this.ownerUserId = ownerUserId;
        this.accesses = accesses;
    }

    public SharedFile(File file, long ownerUserId, Set<SharedAccess> accesses) {
        this(0, file, ownerUserId, accesses);
    }

    public long getId() {
        return id;
    }

    public File getFile() {
        return file;
    }

    public long getOwnerUserId() {
        return ownerUserId;
    }

    public Set<SharedAccess> getAccesses() {
        return accesses;
    }
}
