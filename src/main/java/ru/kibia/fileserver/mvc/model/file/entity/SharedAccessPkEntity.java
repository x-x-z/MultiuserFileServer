package ru.kibia.fileserver.mvc.model.file.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

/**
 * Created by kostin on 22.11.2016.
 */
@Embeddable
public class SharedAccessPkEntity implements Serializable {

    private long fileId;
    private long userId;

    public SharedAccessPkEntity() {
    }

    public SharedAccessPkEntity(long fileId, long userId) {
        this.fileId = fileId;
        this.userId = userId;
    }

    public long getFileId() {
        return fileId;
    }

    public long getUserId() {
        return userId;
    }

    @Override
    public String toString() {
        return "SharedAccessPkEntity{" +
                "fileId=" + fileId +
                ", userId=" + userId +
                '}';
    }
}
