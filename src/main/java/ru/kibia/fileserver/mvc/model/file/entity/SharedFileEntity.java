package ru.kibia.fileserver.mvc.model.file.entity;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;

@Entity
@Table(name = "SHARED_FILES", uniqueConstraints = { @UniqueConstraint( columnNames = { "path", "userId" } ) })
public class SharedFileEntity implements Serializable {

    private static final long serialVersionUID = 8999225423023905892L;

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO, generator="shared_file_id_seq")
    @SequenceGenerator(name="shared_file_id_seq", sequenceName="shared_files_sequence")
    @Column(updatable = false)
    private long id;

    @Column(updatable = false)
    private long userId;

    @Column(nullable = false)
    private String path;

    public SharedFileEntity() {
    }

    public SharedFileEntity(File file, long ownerUserId) {
        this.path = file.getAbsolutePath();
        this.userId = ownerUserId;
    }

    @Override
    public String toString() {
        return String.format(
                "SharedFileEntity[id=%d, userId='%s', path='%s']",
                id, userId, path);
    }
}