package ru.kibia.fileserver.mvc.model.file.entity;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "SHARED_FILES", uniqueConstraints = { @UniqueConstraint( columnNames = { "path", "userOwnerId" } ) })
public class SharedFileEntity implements Serializable {

    private static final long serialVersionUID = 8999225423023905892L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="shared_file_id_seq")
    @SequenceGenerator(name="shared_file_id_seq", sequenceName="shared_files_sequence")
    @Column(updatable = false)
    private long id;

    @Column(updatable = false)
    private long userOwnerId;

    @Column(nullable = false)
    private String path;

    public SharedFileEntity() {
    }

    public SharedFileEntity(File file, long userOwnerId) {
        this.path = file.getAbsolutePath();
        this.userOwnerId = userOwnerId;
    }

    public long getId() {
        return id;
    }

    public long getUserOwnerId() {
        return userOwnerId;
    }

    public String getPath() {
        return path;
    }

    @Override
    public String toString() {
        return String.format(
                "SharedFileEntity[id=%d, userOwnerId='%s', path='%s']",
                id, userOwnerId, path);
    }
}